package com.organizedcars.springboot.VEHICULORECORDATORIOS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.organizedcars.springboot.HELPERS.FechaHelper;
import com.organizedcars.springboot.HELPERS.UsefulValues;
import com.organizedcars.springboot.RECORDATORIO.Recordatorio;
import com.organizedcars.springboot.RECORDATORIO.RecordatorioCustom;
import com.organizedcars.springboot.RECORDATORIO.RecordatorioDAO;
import com.organizedcars.springboot.RECORDATORIO.MAIL_DEVELOPMENT.EmailServiceImple;
import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.USUARIOVEHICULOS.UsuarioVehiculo;
import com.organizedcars.springboot.USUARIOVEHICULOS.UsuarioVehiculoDAO;
import com.organizedcars.springboot.VEHICULO.Vehiculo;

@Service
@Transactional(readOnly = false)
public class VehiculoRecordatorioServiceImpl implements VehiculoRecordatorioService {
	
	@Autowired
	VehiculoRecordatorioDAO vehiculoRecordatorioDAO;
	
	@Autowired
	RecordatorioDAO recordatorioDAO;
	
	@Autowired
	RecordatorioDAO recordatorioCustomDAO;
	
	@Autowired
	UsuarioVehiculoDAO usuarioVehiculoDAO;
	
	@Autowired
	private EmailServiceImple emailServiceImple;
	
	private static final String MENSAJE_ALTA = "Organized Cars te informa que el recordatorio -%s- para el auto con dominio -%s- ha sido creado y vence el %s. \n\n\n\nEl equipo de Organized Cars." ;
	private static final String MENSAJE_EDICION = "Organized Cars te informa que la fecha del recordatorio -%s- para el auto con dominio -%s- ha sido modificada y vence el %s. \n\n\n\nEl equipo de Organized Cars." ;
	private static final String SUBJECT = "ALERTA DE RECORDATORIO!!!";
	
	@Transactional(readOnly = true)
	public Optional<VehiculoRecordatorio> findById(Long id) {
		return vehiculoRecordatorioDAO.findById(id);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public VehiculoRecordatorio save(String nombreRecordatorio,String fechaRecordatorio,Vehiculo vehiculo, String mail) {
		
		Recordatorio rc = new RecordatorioCustom();
		rc.setNombre(nombreRecordatorio);
		rc.setDescripcion(nombreRecordatorio);
		
		Recordatorio recordatorioGuardado = recordatorioDAO.save(rc);
		
		VehiculoRecordatorio vr = new VehiculoRecordatorio();
		vr.setVehiculo(vehiculo);
		vr.setFechaRecordatorio(fechaRecordatorio);
		vr.setRecordatorio(recordatorioGuardado);
		vr.setHabilitado(true);
		
		VehiculoRecordatorio vehiculoRecordatorioGuardado = vehiculoRecordatorioDAO.save(vr); 
		this.mandarMail(Optional.of(vehiculoRecordatorioGuardado), mail, "Alta");
		
		return vehiculoRecordatorioGuardado;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public Void delete(VehiculoRecordatorio vrecordatorio) {
		vehiculoRecordatorioDAO.delete(vrecordatorio);
		recordatorioCustomDAO.deleteById(vrecordatorio.getRecordatorio().getIdRecordatorio());
		return null;
	}

	@Override
	public List<VehiculoRecordatorio> findByVehiculo(Vehiculo vehiculo) {
		return vehiculoRecordatorioDAO.findByVehiculo(vehiculo);
	}
	
	@Override
	public List<VehiculoRecordatorio> enviarNotificaciones(Usuario usuario) throws Exception {
		
		Date fechaActual = new Date();
		Date fechaAdvertencia = new Date();
		String fechaAdvertenciaJapones;
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(fechaActual);
		calendar.add(Calendar.DAY_OF_WEEK, Integer.valueOf(UsefulValues.PROPERTIES.get("dias_aviso_recordatorio")) * -1);
		
		fechaAdvertencia = calendar.getTime();
		fechaAdvertenciaJapones = FechaHelper.convertirFechaAFormatoJapones(fechaAdvertencia);
		
		List<UsuarioVehiculo> usuarioVehiculos = usuarioVehiculoDAO.findByUsuario(usuario);
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		List<VehiculoRecordatorio> vehiculosRecordatorios = new ArrayList<VehiculoRecordatorio>();
		
		for (UsuarioVehiculo usuarioVehiculo : usuarioVehiculos) {
			vehiculos.add(usuarioVehiculo.getVehiculo());
		}
		
		for (Vehiculo vehiculo : vehiculos) {
			vehiculosRecordatorios.addAll(vehiculoRecordatorioDAO.findByVehiculoAndHabilitadoAndFechaRecordatorioNotNull(vehiculo,true));
		}
		
		vehiculosRecordatorios.stream()
				.filter(vr -> vr.getFechaRecordatorio().compareTo(fechaAdvertenciaJapones) >= 0)
				.collect(Collectors.toList());
		
		for (VehiculoRecordatorio vehiculoRecordatorio : vehiculosRecordatorios) {
			Date fechaRecordatorio = new SimpleDateFormat("dd/MM/yyyy").parse(FechaHelper.convertirFechaAFormatoddMMyyyy(vehiculoRecordatorio.getFechaRecordatorio()));
			vehiculoRecordatorio.setDiasRestantesVtoRecordatorio((int)TimeUnit.DAYS.convert(fechaRecordatorio.getTime() - fechaAdvertencia.getTime(), TimeUnit.MILLISECONDS));
		}

		return vehiculosRecordatorios;
	}
	
	@Override
	public VehiculoRecordatorio update(VehiculoRecordatorio vehiculoRecordatorioExistente, VehiculoRecordatorio vehiculoRecordatorioModificado, String mail) {
		vehiculoRecordatorioExistente.setFechaRecordatorio(vehiculoRecordatorioModificado.getFechaRecordatorio().trim());
		
		VehiculoRecordatorio vehiculoRecordatorioGuardado = vehiculoRecordatorioDAO.save(vehiculoRecordatorioExistente); 
		this.mandarMail(Optional.of(vehiculoRecordatorioGuardado), mail, "Edicion");
		
		return vehiculoRecordatorioGuardado;
	}
	
	@Override
	public VehiculoRecordatorio updateEstado(VehiculoRecordatorio vehiculoRecordatorio, Boolean habilitado) {
		vehiculoRecordatorio.setHabilitado(habilitado);
		return vehiculoRecordatorioDAO.save(vehiculoRecordatorio);
	}
	
	@Override
	public void mandarMail(Optional<VehiculoRecordatorio> vehiculoRecordatorio, String to, String accion) throws MailException{

		try{
			String mensaje = "";

			if (accion == "Alta") {
				mensaje = MENSAJE_ALTA;
			}
			
			if (accion == "Edicion") {
				mensaje = MENSAJE_EDICION;
			}
			
			if(vehiculoRecordatorio.get() != null) {
				String mensajeCompleto = String.format(mensaje, vehiculoRecordatorio.get().getRecordatorio().getDescripcion(), 
						vehiculoRecordatorio.get().getVehiculo().getDominio(), 
											FechaHelper.convertirFechaAFormatoddMMyyyy(vehiculoRecordatorio.get().getFechaRecordatorio())); 
				emailServiceImple.sendOnly(to, SUBJECT, mensajeCompleto, new java.util.Date());
			}else{
				System.out.println("El vehiculoRecordatorio es null");
			}
		}catch (NullPointerException e){
			System.out.println("Error"+ e.getLocalizedMessage());
			System.out.println("Causa"+ e.getCause());
			e.printStackTrace();
		}
		catch (MailException e){
			System.out.println("Error en el mail"+ e.getMessage());
		}
	}
}

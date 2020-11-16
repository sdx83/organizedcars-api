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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.organizedcars.springboot.HELPERS.FechaHelper;
import com.organizedcars.springboot.RECORDATORIO.MailRecordatorioHelper;
import com.organizedcars.springboot.RECORDATORIO.Recordatorio;
import com.organizedcars.springboot.RECORDATORIO.RecordatorioCustom;
import com.organizedcars.springboot.RECORDATORIO.RecordatorioDAO;
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
	
	@Transactional(readOnly = true)
	public Optional<VehiculoRecordatorio> findById(Long id) {
		return vehiculoRecordatorioDAO.findById(id);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public VehiculoRecordatorio save(String nombreRecordatorio,String fechaRecordatorio,Vehiculo vehiculo) {
		
		Recordatorio rc = new RecordatorioCustom();
		rc.setNombre(nombreRecordatorio);
		rc.setDescripcion(nombreRecordatorio);
		
		Recordatorio recordatorioGuardado = recordatorioDAO.save(rc);
		
		VehiculoRecordatorio vr = new VehiculoRecordatorio();
		vr.setVehiculo(vehiculo);
		vr.setFechaRecordatorio(fechaRecordatorio);
		vr.setRecordatorio(recordatorioGuardado);
		vr.setHabilitado(true);
		
		return vehiculoRecordatorioDAO.save(vr);
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
	public List<VehiculoRecordatorio> enviarNotificaciones(Usuario usuario, int diasAviso) throws Exception {
		
		Date fechaActual = new Date();
		Date fechaAdvertencia = new Date();
		String fechaAdvertenciaJapones;
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(fechaActual);
		calendar.add(Calendar.DAY_OF_WEEK, diasAviso * -1);
		
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
		MailRecordatorioHelper mailRecordatorioHelper=new MailRecordatorioHelper();
		mailRecordatorioHelper.enviarNotificaciones(vehiculosRecordatorios, usuario.getMail().trim());
		return vehiculosRecordatorios;
	}
	
	@Override
	public VehiculoRecordatorio update(VehiculoRecordatorio vehiculoRecordatorioExistente, VehiculoRecordatorio vehiculoRecordatorioModificado) {
		vehiculoRecordatorioExistente.setFechaRecordatorio(vehiculoRecordatorioModificado.getFechaRecordatorio().trim());
		return vehiculoRecordatorioDAO.save(vehiculoRecordatorioExistente);
	}
	
	@Override
	public VehiculoRecordatorio updateEstado(VehiculoRecordatorio vehiculoRecordatorio, Boolean habilitado) {
		vehiculoRecordatorio.setHabilitado(habilitado);
		return vehiculoRecordatorioDAO.save(vehiculoRecordatorio);
	}
}

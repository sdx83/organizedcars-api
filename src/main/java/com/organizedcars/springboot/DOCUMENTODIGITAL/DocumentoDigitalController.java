package com.organizedcars.springboot.DOCUMENTODIGITAL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.organizedcars.springboot.DOCUMENTODIGITAL.CLOUDINARY_IMPL.CloudinarDigitalDoc;
import com.organizedcars.springboot.DOCUMENTODIGITAL.CLOUDINARY_IMPL.CloudinarDigitalDocsService;
import com.organizedcars.springboot.HELPERS.UsefulValues;
import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.USUARIO.UsuarioServiceImpl;
import com.organizedcars.springboot.VEHICULO.Vehiculo;
import com.organizedcars.springboot.VEHICULO.VehiculoServiceImpl;


@RestController
@RequestMapping("/DocumentosDigitales")
@CrossOrigin(origins = "*")
@PropertySource("application.properties")
public class DocumentoDigitalController {	


	private static final Set<String> SET_FORMATOS=new HashSet<String>();
	static {
		SET_FORMATOS.add("jpg");
		SET_FORMATOS.add("jpeg");
		SET_FORMATOS.add("xlsx");
		SET_FORMATOS.add("pdf");
		SET_FORMATOS.add("docx");
	}


	@Autowired
	private DocumentoDigitalServiceImpl documentoDigitalService;

    @Autowired
	private DocumentoDigitalDAO documentoDigitalDAO;

    @Autowired
	private VehiculoServiceImpl vehiculoService;

    @Autowired
	private UsuarioServiceImpl usuarioService;

	@Autowired
	private CloudinarDigitalDocsService cloudinarDigitalDocsService;





	Cloudinary cloudinary=new Cloudinary(ObjectUtils.asMap(
			"cloud_name","apisbackfranivan",
			"api_key","291692491198663",
			"api_secret", UsefulValues.PROPERTIES.get("api_secret_cloudinary")
	));



    //GET: http://localhost:8080/DocumentosDigitales/1
    @GetMapping(value="/{idDD}")
 	public ResponseEntity<DocumentoDigital> getDDByID(@PathVariable("idDD") Long id) throws Exception{	
  		
  		try {
  			Optional<DocumentoDigital> dd = documentoDigitalService.findById(id);
  	 		if(dd.isPresent()) {
  	 			return ResponseEntity.ok(dd.get());
  	 		}
  	 		else {
  	 			return ResponseEntity.noContent().build();
  	 		}
		} catch (Exception e) {
			throw new Exception("Error al obtener el Documento Digital");
		}
 	}
  	
 	
  	// POST: http://localhost:8080/DocumentosDigitales
 	@PostMapping
 	public ResponseEntity<DocumentoDigital> crearDD(@RequestBody DocumentoDigital dd) throws Exception {

 		try {
 			return ResponseEntity.ok(documentoDigitalService.save(dd));
 		} catch (ResponseStatusException e) {
 	 		throw new Exception(e.getReason());
 	 	} catch (Exception e) {
 	 		throw new Exception("Error al crear el Documento Digital");
 	 	}
 	}
 	  	
    // DELETE: http://localhost:8080/DocumentosDigitales/1
 	@RequestMapping(value = "/{idDD}", method = RequestMethod.DELETE)
 	public ResponseEntity<Void> eliminarDD(@PathVariable("idDD") Long idDD) throws Exception {

 		try {
 			Optional<DocumentoDigital> ddEliminar = documentoDigitalService.findById(idDD);
 			if(ddEliminar.isPresent()) {
 				documentoDigitalService.delete(ddEliminar.get());
 				return ResponseEntity.ok(null);
  	 		}
  	 		else {
  	 			return ResponseEntity.noContent().build();
  	 		}
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getReason());
		} catch (Exception e) {
			throw new Exception("Error al eliminar el Documento Digital");
		}
 	}
 	
 // GET: http://localhost:8080/DocumentosDigitales/Vehiculos/{dominio}
    @GetMapping(value="/Vehiculos/{dominio}")
	public ResponseEntity<List<DocumentoDigital>> obtenerDDsPorDominio(@PathVariable("dominio") String dominio) throws Exception{		
 		
 		try {
 			Optional<Vehiculo> vehiculo = vehiculoService.findByDominio(dominio);
 			
 			if (!vehiculo.isPresent()) {
 				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehículo no encontrado");
 			}
 		
 			List<DocumentoDigital> documentos = documentoDigitalService.findByVehiculo(vehiculo.get());
				
 			if(documentos != null && documentos.size() > 0) {
 				return ResponseEntity.ok(documentos);
 	 		}
 			else {
 	 			return ResponseEntity.noContent().build();
 	 		}
 		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getReason());
		} catch (Exception e) {
			throw new Exception("Error al obtener los documentos");
		}
	}

	//-- EMPIEZO CON LO DEL CLOUDINARY--

	@GetMapping("/")
	public ArrayList<Object> getListaFotos(ModelMap modelMap){
    	return (ArrayList<Object>) modelMap.addAttribute("docsDigitales",documentoDigitalDAO.findAll()).getAttribute("docsDigitales");
    }

    @PostMapping("/uploadDigitalDocs")
	public ResponseEntity<Map> subirFotos(@RequestHeader(value = "fileName") String fileName,@RequestHeader(value = "nombre") String nombre,@RequestHeader(value = "apellido") String apellido,@RequestHeader(value = "idUsuario") String idUser) throws IOException {

		try{
			//Este file name seria el que nosotros
			String id=String.valueOf(idUser);
			File fileUpload=new File(fileName);
			String nombreArchivoSubir=null;
			String[] partesNombreArchivo=fileUpload.getName().replace(".","-").split("-");
			int cantidadFotosSubidas=getDocumentosSubidos(nombre,apellido,id);

			//-------
			if(SET_FORMATOS.contains(String.valueOf(partesNombreArchivo[1]).toLowerCase())){
				System.out.println("Tomo el formato");
				nombreArchivoSubir=nombre+"-"+apellido+"-"+id+"-"+cantidadFotosSubidas;

			}
			//-------


			System.out.println("FORMATO DEL NOMBRE:"+nombreArchivoSubir);

			//Aca el totalSpace, toma el maximo valor que puede llegar  tener un archivo, entonces no nos sirve, en cambio length es justo lo que necesitamo

			if(fileUpload.length()>52428800){
				System.out.println("El tamaño de el archivo no puede superar los 50MB");
				return new ResponseEntity<>(ObjectUtils.asMap("ERROR","El archivo no puede superar los 50MB"),HttpStatus.BAD_REQUEST);
			}

			if(nombreArchivoSubir==null){
				throw new IllegalArgumentException("El nombre/formato del archivo no correspondia");
			}
			//Para poder determinar el nombre del  archivo a subir, le mandamos el public_id con el nombre que queremos.

			Map resultadoUpload=cloudinary.uploader().upload(fileUpload,ObjectUtils.asMap("public_id",nombreArchivoSubir));
			if(resultadoUpload!=null && resultadoUpload.get("public_id").equals(nombreArchivoSubir)){
				Optional<Usuario> usuario=this.usuarioService.findById(Long.valueOf(id));
				CloudinarDigitalDoc documentoDigital=new CloudinarDigitalDoc(String.valueOf(resultadoUpload.get("public_id")), fileUpload.getName(),Long.valueOf(id),"Descripcion");
				this.cloudinarDigitalDocsService.guardarDocumentoDigital(documentoDigital);
			}
			return new ResponseEntity<>(resultadoUpload,HttpStatus.CREATED);
		}catch(IOException E){
			return new ResponseEntity<>(ObjectUtils.asMap("ERROR DE ARCHIVO",E.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(ObjectUtils.asMap("ERROR DE PARAMETRO",e.getMessage()),HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }


    //Diria de guardar en localStorage esos parametros, para no hacer tantas llamadas desde el front.

	@GetMapping(value = "/cantidadSubidas")
	@ResponseStatus(HttpStatus.OK)
    public int getDocumentosSubidos(@RequestHeader(value = "nombre") String nombre,@RequestHeader(value = "apellido") String apellido,@RequestHeader(value = "idUsuario") String id) throws Exception {
    	try {
    		//El coso anterior que tenia era: .resource(ObjectUtils.emptyMap()).get("resources")
			int cantidadFotos=cloudinarDigitalDocsService.getAllDocsById(Long.valueOf(id)).size();
			return cantidadFotos;
		}catch(Exception e){
    		e.printStackTrace();
		}
    	//Asi me trae todos los cosos, todos los resources de mi repo cloud, en vez de mandarle a Cloudinary le mandamos aca, pero seria lo mismo, igualmente me guardo como se hace en cloudinary por las dudas
		return -1;
	}
	@GetMapping(value = "/probarDownload/{idUsuario}")
	private String bajarImagenPorUsuario(@PathVariable(value = "idUsuario") String idUsuario,@RequestHeader(value = "nombre") String nombre,@RequestHeader(value = "apellido") String apellido,@RequestHeader(value = "cantidadFotos")String cantidadFotos) throws Exception {
    	//SecureUrl o assetId que se genera automatico?
		//Como le paso aca el cantidadFotos, no porque aca cuando clickeo el botonsito del front, le tengo que pasar como esta el registro en la base.
		String public_id=nombre+"-"+apellido+"-"+idUsuario+"-"+cantidadFotos;
		//Esto lo cambio por el publicId de la base y nos sale al toque.


		for (CloudinarDigitalDoc cloudinarDigitalDoc:this.cloudinarDigitalDocsService.getAllDocsById(Long.valueOf(idUsuario))) {
			System.out.println("El documento que trae es:"+cloudinarDigitalDoc.getFileUserName());
		}
		//Ver si en resource type le tengo que pasar siempre asi la imagen o le puedo pasar directamente el formato del archivo correspondiente.
		return cloudinary.downloadZip(
				ObjectUtils.asMap("public_ids", Arrays.asList(public_id), "resource_type", "image"));




	}




}

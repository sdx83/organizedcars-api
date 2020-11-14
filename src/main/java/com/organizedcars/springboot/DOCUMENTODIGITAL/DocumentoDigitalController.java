package com.organizedcars.springboot.DOCUMENTODIGITAL;

import com.cloudinary.ArchiveParams;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.organizedcars.springboot.DOCUMENTODIGITAL.CLOUDINARY_IMPL.CloudinarDigitalDoc;
import com.organizedcars.springboot.DOCUMENTODIGITAL.CLOUDINARY_IMPL.CloudinarDigitalDocsService;
import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.USUARIO.UsuarioServiceImpl;
import com.organizedcars.springboot.VEHICULO.Vehiculo;
import com.organizedcars.springboot.VEHICULO.VehiculoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/DocumentosDigitales")
@CrossOrigin(origins = "*")
@PropertySource("classpath:application.properties")
public class DocumentoDigitalController {	


	private static final Set<String> SET_FORMATOS=Set.of("jpg","xlsx","pdf","docx","jpeg");



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

	@Value("${api_secret}")
	private String api_secret_cloudinary;



	Cloudinary cloudinary=new Cloudinary(ObjectUtils.asMap(
			"cloud_name","apisbackfranivan",
			"api_key","291692491198663",
			"api_secret","YMATgrzrFGfPtKihy_HY0Dxa4ms"
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
	public String getListaFotos(ModelMap modelMap){
    	return (String) modelMap.addAttribute("docsDigitales",documentoDigitalDAO.findAll()).getAttribute("docsDigitales");
    }

    @PostMapping("/uploadDigitalDocs")
	public ResponseEntity<Map> subirFotos(@RequestHeader(value = "nombre") String nombre,@RequestHeader(value = "apellido") String apellido,@RequestHeader(value = "idUsuario") String id) throws IOException {

		try{
			//Este file name seria el que nosotros
			File fileUpload=new File("C:/Users/IVAN/Desktop/Lineamentos para el trabajo practico.pdf");
			String nombreArchivoSubir=null;
			String[] partesNombreArchivo=fileUpload.getName().replace(".","-").split("-");
			System.out.println("EL NOMBRE DEL ARCHIVO ES: " +fileUpload.getName());
			System.out.println("EL nombre del archivo cambiado es:" +fileUpload.getName().replace(".","-").split("-")[1]);
			int cantidadFotosSubidas=getDocumentosSubidos(nombre,apellido,id);

			//-------
			if(SET_FORMATOS.contains(String.valueOf(partesNombreArchivo[1]).toLowerCase())){
				System.out.println("Tomo el formato");
				nombreArchivoSubir=nombre+"-"+apellido+"-"+id+"-"+cantidadFotosSubidas+"."+partesNombreArchivo[1];

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
    		ArrayList<Object> object= (ArrayList<Object>) cloudinary.api().resources(ObjectUtils.emptyMap()).get("resources");
			//Ya sabemos que devuele un hashMap cada objeto.
			int documentos=0;
			String[] regex=null;
			String nombreArchivoPersona=nombre+"-"+apellido+"-"+id;
			for (Object o:object) {
				HashMap<String,Object> cloudinaryImage= (HashMap<String, Object>) o;
				for (Map.Entry<String,Object> item:cloudinaryImage.entrySet()) {
					if(item.getKey().equals("public_id")){
						String nombreImprimir=(String)item.getValue();
						if(!nombreImprimir.contains("/")){
							regex=nombreImprimir.replace(".","T").replace("-","T").split("T");
						}

						if(regex!=null) {
							String comparar=regex[0]+"-"+regex[1]+"-"+regex[2];
							System.out.println("COMPARAR:"+comparar);
							
							if (comparar.equalsIgnoreCase(nombreArchivoPersona)) {
								documentos += 1;
								System.out.println("Entró a la comparación del if");
								System.out.println("Valor que compara del IF: " + item.getValue().toString());
								System.out.println("Comparacion dentro del primer if:" + regex[0]);
							} else {
								System.out.println("Algo busco pero no encntro");
								System.out.println("Value: " + item.getValue().toString());
								System.out.println("Comparacion:" + regex[0]);
							}
						}
					}
				}
			}


			 System.out.println("La longitud de la lista del usuario es: " +object.size());
			 return documentos;
		}catch(Exception e){
    		e.printStackTrace();
		}
    	//Asi me trae todos los cosos, todos los resources de mi repo cloud
		return -1;
	}
	@GetMapping(value = "/probarDownload/{idUsuario}")
	private String bajarImagenPorUsuario(@PathVariable(value = "idUsuario") String idUsuario,@RequestHeader(value = "public_id") String public_id) throws Exception {
    	//SecureUrl o assetId que se genera automatico?

		String[] tags={"public_id",public_id};
		ArchiveParams archiveParams=new ArchiveParams();
		ArchiveParams archiveParams1=archiveParams.tags(tags).type("upload");


		//return 	cloudinary.downloadArchive(archiveParams1.toMap(),archiveParams1.targetFormat());

		//return cloudinary.api().resources(ObjectUtils.asMap("public_ids",nombres)).toString();

		for (CloudinarDigitalDoc cloudinarDigitalDoc:this.cloudinarDigitalDocsService.getAllDocsById(Long.valueOf(idUsuario))) {
			System.out.println("El documento que trae es:"+cloudinarDigitalDoc.getFileUserName());
		}
		//Ver si en resource type le tengo que pasar siempre asi la imagen o le puedo pasar directamente el formato del archivo correspondiente.
		return cloudinary.downloadZip(
				ObjectUtils.asMap("public_ids", Arrays.asList(public_id), "resource_type", "image"));


    	//La foto o documento o lo que sea, lo bajamos directo al navegador y que lo vea el user.
		//A sergio le paso la safe_url de  la foto y que la busque, mapeada contra el idUsuario
		//id 4 url 8,id 4 url 67

	}




}

package com.organizedcars.springboot.DOCUMENTODIGITAL;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.organizedcars.springboot.DOCUMENTODIGITAL.CLOUDINARY_IMPL.CloudinarDigitalDoc;
import com.organizedcars.springboot.DOCUMENTODIGITAL.CLOUDINARY_IMPL.CloudinarDigitalDocsService;
import com.organizedcars.springboot.HELPERS.UsefulValues;
import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.USUARIO.UsuarioServiceImpl;


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
		SET_FORMATOS.add("png");
	}

    @Autowired
	private UsuarioServiceImpl usuarioService;

	@Autowired
	private CloudinarDigitalDocsService cloudinarDigitalDocsService;





	Cloudinary cloudinary=new Cloudinary(ObjectUtils.asMap(
			"cloud_name","apisbackfranivan",
			"api_key","291692491198663",
			"api_secret", UsefulValues.PROPERTIES.get("api_secret_cloudinary")
	));



   //-- EMPIEZO CON LO DEL CLOUDINARY--
/*
	@GetMapping("/")
	public ArrayList<Object> getListaFotos(ModelMap modelMap){
    	return (ArrayList<Object>) modelMap.addAttribute("docsDigitales",documentoDigitalDAO.findAll()).getAttribute("docsDigitales");
    }
*/

	public ResponseEntity<Map> subirFotos(String fileName, String nombre,String apellido, String idUser) throws IOException {

		try{
			//Este file name seria el que nosotros
			final String urlDocumentos=System.getProperty("java.io.tmpdir")+"/"+fileName;
			String id=String.valueOf(idUser);
			File fileUpload=new File(urlDocumentos);
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
		//Sino funca tirar la request con prefixs
		return -1;
	}
	@GetMapping(value = "/probarDownload/{public_id}",produces = "application/json")
	private ResponseEntity<Map<String,String>> bajarImagenPorUsuario(@PathVariable(value = "public_id")String public_id) throws Exception {
		System.out.println("PASA POR ACA");
		String url=cloudinary.downloadZip(
				ObjectUtils.asMap("public_ids", Arrays.asList(public_id)));
		System.out.println("URL: "+url);
		Map<String,String> map=new HashMap<>();
		map.put("url",url);
		return new ResponseEntity<>(map,HttpStatus.OK);
		//Este string ponerlo en el onClick del boton del ver para que descargue.

	}

	@PostMapping(value = "/subirArchivosDirectorio/{nombre}/{apellido}/{idUsuario}",consumes = "multipart/form-data",produces = "application/json")
	public ResponseEntity<Map> subirArchivoDirectorio(@RequestParam(name = "archivo") MultipartFile multipartFile,@PathVariable(value = "nombre")String nombre,@PathVariable(value = "apellido") String apellido,@PathVariable(value = "idUsuario") String idUser) throws IOException{
    	File directorio;
    	try{
    		
			int control=0;
			String url=System.getProperty("java.io.tmpdir")+"/";
    		directorio=new File(url);
    		directorio.mkdir();
    		
			Path directorioImagenes= Paths.get(url);
			String rutaAbsolutaCompleta=directorioImagenes.toFile().getAbsolutePath();

			//String directorioIterar="C:\\Users\\IVAN\\Desktop\\seminarioBackNuevo\\organizedcars-api\\src\\directorio";

			//DirectoryStream<Path> ds=Files.newDirectoryStream(Paths.get(directorioIterar));
			if(!multipartFile.isEmpty()) {
				byte[] inputStreamImagen = multipartFile.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsolutaCompleta + "//" + multipartFile.getOriginalFilename());
				Files.write(rutaCompleta, inputStreamImagen);
				control = 1;
			}


			if(control==1){
				//Esta el archivo dentro de la carpeta, lo subimos.
				ResponseEntity<Map> mapReturn=this.subirFotos(multipartFile.getOriginalFilename(),nombre,apellido,idUser);
				if(mapReturn!=null){
					//Significa que no necesito tener mas el archivo en el directorio, de igual manera lo desarrollo mas adelante.
					//Llamar a la funcion de borrado.
					System.out.println("El resultado no fue null, aca tendria que ir la de borrado.");
					return new ResponseEntity<>(mapReturn.getBody(),HttpStatus.OK);
				}


			}

			Map<String,String> mapReturn=new HashMap<>();
			mapReturn.put("ERROR","ERROR AL SUBIR EL ARCHIVO");
			return new ResponseEntity<>(mapReturn,HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(Exception e){
    		throw new Error("El error fue:" +e);
		}
	}

	@GetMapping(value = "/documentosPorUsuario/{idUsuario}")
	public List<CloudinarDigitalDoc> getAllDocsInfo(@PathVariable(value = "idUsuario")int idUsuario) throws Exception {
    	try{
			return this.cloudinarDigitalDocsService.getAllDocsById((long) idUsuario);
		}catch(Exception e){
    		throw new Exception(e.getMessage());
		}

	}
}

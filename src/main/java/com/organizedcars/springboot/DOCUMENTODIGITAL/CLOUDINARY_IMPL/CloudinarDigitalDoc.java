package com.organizedcars.springboot.DOCUMENTODIGITAL.CLOUDINARY_IMPL;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "documentos_subidos_por_usuario")
public class CloudinarDigitalDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumentoDigital;

    @Column(name = "public_id")
    @NotNull
    private String public_id;

    @Column(name = "nombre_archivo_usuario")
    @NotNull
    private String fileUserName;

    @Column(name = "descripcion_archivo")
    @NotNull
    private String descripcionArchivo;

    @Column(name = "idUsuario")
    @NotNull
    private Long idUsuario;

    public CloudinarDigitalDoc(){

    }

    public CloudinarDigitalDoc(String public_id,String fileUserName,Long idUsuario,String descripcionArchivo){
        this.fileUserName=fileUserName;
        this.idUsuario=idUsuario;
        this.public_id=public_id;
        this.descripcionArchivo=descripcionArchivo;
    }


    public String getFileUserName() {
        return fileUserName;
    }

    public String getIdUser() {
        return String.valueOf(this.idUsuario);
    }

    public String getPublic_id() {
        return public_id;
    }

    public void setFileUserName(String fileUserName) {
        this.fileUserName = fileUserName;
    }

    public void setIdUser(String idUser) {
        this.idUsuario = Long.valueOf(idUser);
    }

    public void setPublic_id(String public_id) {
        this.public_id = public_id;
    }

    public Long getIdDocumentoDigital() {
        return idDocumentoDigital;
    }

}

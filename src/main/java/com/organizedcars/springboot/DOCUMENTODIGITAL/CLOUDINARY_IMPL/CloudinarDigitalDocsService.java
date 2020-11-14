package com.organizedcars.springboot.DOCUMENTODIGITAL.CLOUDINARY_IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CloudinarDigitalDocsService {

    @Autowired
    public CloudinaryDigitalDocsDAO cloudinaryDigitalDocsDAO;

    @Transactional
    public CloudinarDigitalDoc guardarDocumentoDigital(CloudinarDigitalDoc cloudinarDigitalDoc){
        return this.cloudinaryDigitalDocsDAO.save(cloudinarDigitalDoc);
    }
    @Transactional(readOnly = true)
    public List<CloudinarDigitalDoc> getAllDocs(){
        return this.cloudinaryDigitalDocsDAO.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<CloudinarDigitalDoc> getById(long idUsuario){
        return this.cloudinaryDigitalDocsDAO.findById(idUsuario);
    }
    @Transactional(readOnly = true)
    public List<CloudinarDigitalDoc> getAllDocsById(Long idUsuario){
        return this.cloudinaryDigitalDocsDAO.findByIdUsuario(idUsuario);
    }
}

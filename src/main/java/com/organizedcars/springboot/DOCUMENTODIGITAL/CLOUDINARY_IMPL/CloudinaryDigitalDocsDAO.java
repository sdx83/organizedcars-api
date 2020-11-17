package com.organizedcars.springboot.DOCUMENTODIGITAL.CLOUDINARY_IMPL;

import com.organizedcars.springboot.DOCUMENTODIGITAL.CLOUDINARY_IMPL.CloudinarDigitalDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface CloudinaryDigitalDocsDAO extends JpaRepository<CloudinarDigitalDoc,Long> {

    @Override
    Optional<CloudinarDigitalDoc> findById(Long idUsuario);

    @Query(value = "select l from CloudinarDigitalDoc l where l.idUsuario=:idUsuario")
    List<CloudinarDigitalDoc> findByIdUsuario(long idUsuario);


}

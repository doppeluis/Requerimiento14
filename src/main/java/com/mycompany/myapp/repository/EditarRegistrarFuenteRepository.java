package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EditarRegistrarFuente;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EditarRegistrarFuente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EditarRegistrarFuenteRepository extends JpaRepository<EditarRegistrarFuente, Long> {
}

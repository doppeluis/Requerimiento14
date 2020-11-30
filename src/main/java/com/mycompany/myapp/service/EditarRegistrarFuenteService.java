package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.EditarRegistrarFuente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link EditarRegistrarFuente}.
 */
public interface EditarRegistrarFuenteService {

    /**
     * Save a editarRegistrarFuente.
     *
     * @param editarRegistrarFuente the entity to save.
     * @return the persisted entity.
     */
    EditarRegistrarFuente save(EditarRegistrarFuente editarRegistrarFuente);

    /**
     * Get all the editarRegistrarFuentes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EditarRegistrarFuente> findAll(Pageable pageable);


    /**
     * Get the "id" editarRegistrarFuente.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EditarRegistrarFuente> findOne(Long id);

    /**
     * Delete the "id" editarRegistrarFuente.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

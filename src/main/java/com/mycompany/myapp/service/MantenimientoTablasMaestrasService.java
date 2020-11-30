package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.MantenimientoTablasMaestras;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link MantenimientoTablasMaestras}.
 */
public interface MantenimientoTablasMaestrasService {

    /**
     * Save a mantenimientoTablasMaestras.
     *
     * @param mantenimientoTablasMaestras the entity to save.
     * @return the persisted entity.
     */
    MantenimientoTablasMaestras save(MantenimientoTablasMaestras mantenimientoTablasMaestras);

    /**
     * Get all the mantenimientoTablasMaestras.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MantenimientoTablasMaestras> findAll(Pageable pageable);


    /**
     * Get the "id" mantenimientoTablasMaestras.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MantenimientoTablasMaestras> findOne(Long id);

    /**
     * Delete the "id" mantenimientoTablasMaestras.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

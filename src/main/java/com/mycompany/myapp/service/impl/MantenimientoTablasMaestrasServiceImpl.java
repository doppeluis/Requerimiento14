package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.MantenimientoTablasMaestrasService;
import com.mycompany.myapp.domain.MantenimientoTablasMaestras;
import com.mycompany.myapp.repository.MantenimientoTablasMaestrasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MantenimientoTablasMaestras}.
 */
@Service
@Transactional
public class MantenimientoTablasMaestrasServiceImpl implements MantenimientoTablasMaestrasService {

    private final Logger log = LoggerFactory.getLogger(MantenimientoTablasMaestrasServiceImpl.class);

    private final MantenimientoTablasMaestrasRepository mantenimientoTablasMaestrasRepository;

    public MantenimientoTablasMaestrasServiceImpl(MantenimientoTablasMaestrasRepository mantenimientoTablasMaestrasRepository) {
        this.mantenimientoTablasMaestrasRepository = mantenimientoTablasMaestrasRepository;
    }

    @Override
    public MantenimientoTablasMaestras save(MantenimientoTablasMaestras mantenimientoTablasMaestras) {
        log.debug("Request to save MantenimientoTablasMaestras : {}", mantenimientoTablasMaestras);
        return mantenimientoTablasMaestrasRepository.save(mantenimientoTablasMaestras);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MantenimientoTablasMaestras> findAll(Pageable pageable) {
        log.debug("Request to get all MantenimientoTablasMaestras");
        return mantenimientoTablasMaestrasRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MantenimientoTablasMaestras> findOne(Long id) {
        log.debug("Request to get MantenimientoTablasMaestras : {}", id);
        return mantenimientoTablasMaestrasRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MantenimientoTablasMaestras : {}", id);
        mantenimientoTablasMaestrasRepository.deleteById(id);
    }
}

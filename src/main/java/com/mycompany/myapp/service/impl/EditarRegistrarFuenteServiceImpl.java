package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EditarRegistrarFuenteService;
import com.mycompany.myapp.domain.EditarRegistrarFuente;
import com.mycompany.myapp.repository.EditarRegistrarFuenteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EditarRegistrarFuente}.
 */
@Service
@Transactional
public class EditarRegistrarFuenteServiceImpl implements EditarRegistrarFuenteService {

    private final Logger log = LoggerFactory.getLogger(EditarRegistrarFuenteServiceImpl.class);

    private final EditarRegistrarFuenteRepository editarRegistrarFuenteRepository;

    public EditarRegistrarFuenteServiceImpl(EditarRegistrarFuenteRepository editarRegistrarFuenteRepository) {
        this.editarRegistrarFuenteRepository = editarRegistrarFuenteRepository;
    }

    @Override
    public EditarRegistrarFuente save(EditarRegistrarFuente editarRegistrarFuente) {
        log.debug("Request to save EditarRegistrarFuente : {}", editarRegistrarFuente);
        return editarRegistrarFuenteRepository.save(editarRegistrarFuente);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EditarRegistrarFuente> findAll(Pageable pageable) {
        log.debug("Request to get all EditarRegistrarFuentes");
        return editarRegistrarFuenteRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EditarRegistrarFuente> findOne(Long id) {
        log.debug("Request to get EditarRegistrarFuente : {}", id);
        return editarRegistrarFuenteRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete EditarRegistrarFuente : {}", id);
        editarRegistrarFuenteRepository.deleteById(id);
    }
}

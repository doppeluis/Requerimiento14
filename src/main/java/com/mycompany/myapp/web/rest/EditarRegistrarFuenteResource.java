package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.EditarRegistrarFuente;
import com.mycompany.myapp.service.EditarRegistrarFuenteService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.EditarRegistrarFuente}.
 */
@RestController
@RequestMapping("/api")
public class EditarRegistrarFuenteResource {

    private final Logger log = LoggerFactory.getLogger(EditarRegistrarFuenteResource.class);

    private static final String ENTITY_NAME = "editarRegistrarFuente";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EditarRegistrarFuenteService editarRegistrarFuenteService;

    public EditarRegistrarFuenteResource(EditarRegistrarFuenteService editarRegistrarFuenteService) {
        this.editarRegistrarFuenteService = editarRegistrarFuenteService;
    }

    /**
     * {@code POST  /editar-registrar-fuentes} : Create a new editarRegistrarFuente.
     *
     * @param editarRegistrarFuente the editarRegistrarFuente to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new editarRegistrarFuente, or with status {@code 400 (Bad Request)} if the editarRegistrarFuente has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/editar-registrar-fuentes")
    public ResponseEntity<EditarRegistrarFuente> createEditarRegistrarFuente(@RequestBody EditarRegistrarFuente editarRegistrarFuente) throws URISyntaxException {
        log.debug("REST request to save EditarRegistrarFuente : {}", editarRegistrarFuente);
        if (editarRegistrarFuente.getId() != null) {
            throw new BadRequestAlertException("A new editarRegistrarFuente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EditarRegistrarFuente result = editarRegistrarFuenteService.save(editarRegistrarFuente);
        return ResponseEntity.created(new URI("/api/editar-registrar-fuentes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /editar-registrar-fuentes} : Updates an existing editarRegistrarFuente.
     *
     * @param editarRegistrarFuente the editarRegistrarFuente to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated editarRegistrarFuente,
     * or with status {@code 400 (Bad Request)} if the editarRegistrarFuente is not valid,
     * or with status {@code 500 (Internal Server Error)} if the editarRegistrarFuente couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/editar-registrar-fuentes")
    public ResponseEntity<EditarRegistrarFuente> updateEditarRegistrarFuente(@RequestBody EditarRegistrarFuente editarRegistrarFuente) throws URISyntaxException {
        log.debug("REST request to update EditarRegistrarFuente : {}", editarRegistrarFuente);
        if (editarRegistrarFuente.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EditarRegistrarFuente result = editarRegistrarFuenteService.save(editarRegistrarFuente);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, editarRegistrarFuente.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /editar-registrar-fuentes} : get all the editarRegistrarFuentes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of editarRegistrarFuentes in body.
     */
    @GetMapping("/editar-registrar-fuentes")
    public ResponseEntity<List<EditarRegistrarFuente>> getAllEditarRegistrarFuentes(Pageable pageable) {
        log.debug("REST request to get a page of EditarRegistrarFuentes");
        Page<EditarRegistrarFuente> page = editarRegistrarFuenteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /editar-registrar-fuentes/:id} : get the "id" editarRegistrarFuente.
     *
     * @param id the id of the editarRegistrarFuente to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the editarRegistrarFuente, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/editar-registrar-fuentes/{id}")
    public ResponseEntity<EditarRegistrarFuente> getEditarRegistrarFuente(@PathVariable Long id) {
        log.debug("REST request to get EditarRegistrarFuente : {}", id);
        Optional<EditarRegistrarFuente> editarRegistrarFuente = editarRegistrarFuenteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(editarRegistrarFuente);
    }

    /**
     * {@code DELETE  /editar-registrar-fuentes/:id} : delete the "id" editarRegistrarFuente.
     *
     * @param id the id of the editarRegistrarFuente to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/editar-registrar-fuentes/{id}")
    public ResponseEntity<Void> deleteEditarRegistrarFuente(@PathVariable Long id) {
        log.debug("REST request to delete EditarRegistrarFuente : {}", id);
        editarRegistrarFuenteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

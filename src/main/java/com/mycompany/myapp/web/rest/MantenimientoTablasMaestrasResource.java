package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.MantenimientoTablasMaestras;
import com.mycompany.myapp.service.MantenimientoTablasMaestrasService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.MantenimientoTablasMaestras}.
 */
@RestController
@RequestMapping("/api")
public class MantenimientoTablasMaestrasResource {

    private final Logger log = LoggerFactory.getLogger(MantenimientoTablasMaestrasResource.class);

    private static final String ENTITY_NAME = "mantenimientoTablasMaestras";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MantenimientoTablasMaestrasService mantenimientoTablasMaestrasService;

    public MantenimientoTablasMaestrasResource(MantenimientoTablasMaestrasService mantenimientoTablasMaestrasService) {
        this.mantenimientoTablasMaestrasService = mantenimientoTablasMaestrasService;
    }

    /**
     * {@code POST  /mantenimiento-tablas-maestras} : Create a new mantenimientoTablasMaestras.
     *
     * @param mantenimientoTablasMaestras the mantenimientoTablasMaestras to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mantenimientoTablasMaestras, or with status {@code 400 (Bad Request)} if the mantenimientoTablasMaestras has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mantenimiento-tablas-maestras")
    public ResponseEntity<MantenimientoTablasMaestras> createMantenimientoTablasMaestras(@RequestBody MantenimientoTablasMaestras mantenimientoTablasMaestras) throws URISyntaxException {
        log.debug("REST request to save MantenimientoTablasMaestras : {}", mantenimientoTablasMaestras);
        if (mantenimientoTablasMaestras.getId() != null) {
            throw new BadRequestAlertException("A new mantenimientoTablasMaestras cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MantenimientoTablasMaestras result = mantenimientoTablasMaestrasService.save(mantenimientoTablasMaestras);
        return ResponseEntity.created(new URI("/api/mantenimiento-tablas-maestras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mantenimiento-tablas-maestras} : Updates an existing mantenimientoTablasMaestras.
     *
     * @param mantenimientoTablasMaestras the mantenimientoTablasMaestras to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mantenimientoTablasMaestras,
     * or with status {@code 400 (Bad Request)} if the mantenimientoTablasMaestras is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mantenimientoTablasMaestras couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mantenimiento-tablas-maestras")
    public ResponseEntity<MantenimientoTablasMaestras> updateMantenimientoTablasMaestras(@RequestBody MantenimientoTablasMaestras mantenimientoTablasMaestras) throws URISyntaxException {
        log.debug("REST request to update MantenimientoTablasMaestras : {}", mantenimientoTablasMaestras);
        if (mantenimientoTablasMaestras.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MantenimientoTablasMaestras result = mantenimientoTablasMaestrasService.save(mantenimientoTablasMaestras);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mantenimientoTablasMaestras.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mantenimiento-tablas-maestras} : get all the mantenimientoTablasMaestras.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mantenimientoTablasMaestras in body.
     */
    @GetMapping("/mantenimiento-tablas-maestras")
    public ResponseEntity<List<MantenimientoTablasMaestras>> getAllMantenimientoTablasMaestras(Pageable pageable) {
        log.debug("REST request to get a page of MantenimientoTablasMaestras");
        Page<MantenimientoTablasMaestras> page = mantenimientoTablasMaestrasService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mantenimiento-tablas-maestras/:id} : get the "id" mantenimientoTablasMaestras.
     *
     * @param id the id of the mantenimientoTablasMaestras to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mantenimientoTablasMaestras, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mantenimiento-tablas-maestras/{id}")
    public ResponseEntity<MantenimientoTablasMaestras> getMantenimientoTablasMaestras(@PathVariable Long id) {
        log.debug("REST request to get MantenimientoTablasMaestras : {}", id);
        Optional<MantenimientoTablasMaestras> mantenimientoTablasMaestras = mantenimientoTablasMaestrasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mantenimientoTablasMaestras);
    }

    /**
     * {@code DELETE  /mantenimiento-tablas-maestras/:id} : delete the "id" mantenimientoTablasMaestras.
     *
     * @param id the id of the mantenimientoTablasMaestras to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mantenimiento-tablas-maestras/{id}")
    public ResponseEntity<Void> deleteMantenimientoTablasMaestras(@PathVariable Long id) {
        log.debug("REST request to delete MantenimientoTablasMaestras : {}", id);
        mantenimientoTablasMaestrasService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

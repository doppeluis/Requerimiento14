package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Requerimiento14App;
import com.mycompany.myapp.domain.MantenimientoTablasMaestras;
import com.mycompany.myapp.repository.MantenimientoTablasMaestrasRepository;
import com.mycompany.myapp.service.MantenimientoTablasMaestrasService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MantenimientoTablasMaestrasResource} REST controller.
 */
@SpringBootTest(classes = Requerimiento14App.class)
@AutoConfigureMockMvc
@WithMockUser
public class MantenimientoTablasMaestrasResourceIT {

    private static final String DEFAULT_TECNICA_EVALUATIVA = "AAAAAAAAAA";
    private static final String UPDATED_TECNICA_EVALUATIVA = "BBBBBBBBBB";

    @Autowired
    private MantenimientoTablasMaestrasRepository mantenimientoTablasMaestrasRepository;

    @Autowired
    private MantenimientoTablasMaestrasService mantenimientoTablasMaestrasService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMantenimientoTablasMaestrasMockMvc;

    private MantenimientoTablasMaestras mantenimientoTablasMaestras;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MantenimientoTablasMaestras createEntity(EntityManager em) {
        MantenimientoTablasMaestras mantenimientoTablasMaestras = new MantenimientoTablasMaestras()
            .tecnicaEvaluativa(DEFAULT_TECNICA_EVALUATIVA);
        return mantenimientoTablasMaestras;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MantenimientoTablasMaestras createUpdatedEntity(EntityManager em) {
        MantenimientoTablasMaestras mantenimientoTablasMaestras = new MantenimientoTablasMaestras()
            .tecnicaEvaluativa(UPDATED_TECNICA_EVALUATIVA);
        return mantenimientoTablasMaestras;
    }

    @BeforeEach
    public void initTest() {
        mantenimientoTablasMaestras = createEntity(em);
    }

    @Test
    @Transactional
    public void createMantenimientoTablasMaestras() throws Exception {
        int databaseSizeBeforeCreate = mantenimientoTablasMaestrasRepository.findAll().size();
        // Create the MantenimientoTablasMaestras
        restMantenimientoTablasMaestrasMockMvc.perform(post("/api/mantenimiento-tablas-maestras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mantenimientoTablasMaestras)))
            .andExpect(status().isCreated());

        // Validate the MantenimientoTablasMaestras in the database
        List<MantenimientoTablasMaestras> mantenimientoTablasMaestrasList = mantenimientoTablasMaestrasRepository.findAll();
        assertThat(mantenimientoTablasMaestrasList).hasSize(databaseSizeBeforeCreate + 1);
        MantenimientoTablasMaestras testMantenimientoTablasMaestras = mantenimientoTablasMaestrasList.get(mantenimientoTablasMaestrasList.size() - 1);
        assertThat(testMantenimientoTablasMaestras.getTecnicaEvaluativa()).isEqualTo(DEFAULT_TECNICA_EVALUATIVA);
    }

    @Test
    @Transactional
    public void createMantenimientoTablasMaestrasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mantenimientoTablasMaestrasRepository.findAll().size();

        // Create the MantenimientoTablasMaestras with an existing ID
        mantenimientoTablasMaestras.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMantenimientoTablasMaestrasMockMvc.perform(post("/api/mantenimiento-tablas-maestras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mantenimientoTablasMaestras)))
            .andExpect(status().isBadRequest());

        // Validate the MantenimientoTablasMaestras in the database
        List<MantenimientoTablasMaestras> mantenimientoTablasMaestrasList = mantenimientoTablasMaestrasRepository.findAll();
        assertThat(mantenimientoTablasMaestrasList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMantenimientoTablasMaestras() throws Exception {
        // Initialize the database
        mantenimientoTablasMaestrasRepository.saveAndFlush(mantenimientoTablasMaestras);

        // Get all the mantenimientoTablasMaestrasList
        restMantenimientoTablasMaestrasMockMvc.perform(get("/api/mantenimiento-tablas-maestras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mantenimientoTablasMaestras.getId().intValue())))
            .andExpect(jsonPath("$.[*].tecnicaEvaluativa").value(hasItem(DEFAULT_TECNICA_EVALUATIVA)));
    }
    
    @Test
    @Transactional
    public void getMantenimientoTablasMaestras() throws Exception {
        // Initialize the database
        mantenimientoTablasMaestrasRepository.saveAndFlush(mantenimientoTablasMaestras);

        // Get the mantenimientoTablasMaestras
        restMantenimientoTablasMaestrasMockMvc.perform(get("/api/mantenimiento-tablas-maestras/{id}", mantenimientoTablasMaestras.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mantenimientoTablasMaestras.getId().intValue()))
            .andExpect(jsonPath("$.tecnicaEvaluativa").value(DEFAULT_TECNICA_EVALUATIVA));
    }
    @Test
    @Transactional
    public void getNonExistingMantenimientoTablasMaestras() throws Exception {
        // Get the mantenimientoTablasMaestras
        restMantenimientoTablasMaestrasMockMvc.perform(get("/api/mantenimiento-tablas-maestras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMantenimientoTablasMaestras() throws Exception {
        // Initialize the database
        mantenimientoTablasMaestrasService.save(mantenimientoTablasMaestras);

        int databaseSizeBeforeUpdate = mantenimientoTablasMaestrasRepository.findAll().size();

        // Update the mantenimientoTablasMaestras
        MantenimientoTablasMaestras updatedMantenimientoTablasMaestras = mantenimientoTablasMaestrasRepository.findById(mantenimientoTablasMaestras.getId()).get();
        // Disconnect from session so that the updates on updatedMantenimientoTablasMaestras are not directly saved in db
        em.detach(updatedMantenimientoTablasMaestras);
        updatedMantenimientoTablasMaestras
            .tecnicaEvaluativa(UPDATED_TECNICA_EVALUATIVA);

        restMantenimientoTablasMaestrasMockMvc.perform(put("/api/mantenimiento-tablas-maestras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMantenimientoTablasMaestras)))
            .andExpect(status().isOk());

        // Validate the MantenimientoTablasMaestras in the database
        List<MantenimientoTablasMaestras> mantenimientoTablasMaestrasList = mantenimientoTablasMaestrasRepository.findAll();
        assertThat(mantenimientoTablasMaestrasList).hasSize(databaseSizeBeforeUpdate);
        MantenimientoTablasMaestras testMantenimientoTablasMaestras = mantenimientoTablasMaestrasList.get(mantenimientoTablasMaestrasList.size() - 1);
        assertThat(testMantenimientoTablasMaestras.getTecnicaEvaluativa()).isEqualTo(UPDATED_TECNICA_EVALUATIVA);
    }

    @Test
    @Transactional
    public void updateNonExistingMantenimientoTablasMaestras() throws Exception {
        int databaseSizeBeforeUpdate = mantenimientoTablasMaestrasRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMantenimientoTablasMaestrasMockMvc.perform(put("/api/mantenimiento-tablas-maestras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mantenimientoTablasMaestras)))
            .andExpect(status().isBadRequest());

        // Validate the MantenimientoTablasMaestras in the database
        List<MantenimientoTablasMaestras> mantenimientoTablasMaestrasList = mantenimientoTablasMaestrasRepository.findAll();
        assertThat(mantenimientoTablasMaestrasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMantenimientoTablasMaestras() throws Exception {
        // Initialize the database
        mantenimientoTablasMaestrasService.save(mantenimientoTablasMaestras);

        int databaseSizeBeforeDelete = mantenimientoTablasMaestrasRepository.findAll().size();

        // Delete the mantenimientoTablasMaestras
        restMantenimientoTablasMaestrasMockMvc.perform(delete("/api/mantenimiento-tablas-maestras/{id}", mantenimientoTablasMaestras.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MantenimientoTablasMaestras> mantenimientoTablasMaestrasList = mantenimientoTablasMaestrasRepository.findAll();
        assertThat(mantenimientoTablasMaestrasList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

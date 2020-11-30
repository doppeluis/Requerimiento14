package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.Requerimiento14App;
import com.mycompany.myapp.domain.EditarRegistrarFuente;
import com.mycompany.myapp.repository.EditarRegistrarFuenteRepository;
import com.mycompany.myapp.service.EditarRegistrarFuenteService;

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
 * Integration tests for the {@link EditarRegistrarFuenteResource} REST controller.
 */
@SpringBootTest(classes = Requerimiento14App.class)
@AutoConfigureMockMvc
@WithMockUser
public class EditarRegistrarFuenteResourceIT {

    private static final Integer DEFAULT_CODIGO = 1;
    private static final Integer UPDATED_CODIGO = 2;

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    @Autowired
    private EditarRegistrarFuenteRepository editarRegistrarFuenteRepository;

    @Autowired
    private EditarRegistrarFuenteService editarRegistrarFuenteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEditarRegistrarFuenteMockMvc;

    private EditarRegistrarFuente editarRegistrarFuente;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EditarRegistrarFuente createEntity(EntityManager em) {
        EditarRegistrarFuente editarRegistrarFuente = new EditarRegistrarFuente()
            .codigo(DEFAULT_CODIGO)
            .descripcion(DEFAULT_DESCRIPCION)
            .estado(DEFAULT_ESTADO);
        return editarRegistrarFuente;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EditarRegistrarFuente createUpdatedEntity(EntityManager em) {
        EditarRegistrarFuente editarRegistrarFuente = new EditarRegistrarFuente()
            .codigo(UPDATED_CODIGO)
            .descripcion(UPDATED_DESCRIPCION)
            .estado(UPDATED_ESTADO);
        return editarRegistrarFuente;
    }

    @BeforeEach
    public void initTest() {
        editarRegistrarFuente = createEntity(em);
    }

    @Test
    @Transactional
    public void createEditarRegistrarFuente() throws Exception {
        int databaseSizeBeforeCreate = editarRegistrarFuenteRepository.findAll().size();
        // Create the EditarRegistrarFuente
        restEditarRegistrarFuenteMockMvc.perform(post("/api/editar-registrar-fuentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(editarRegistrarFuente)))
            .andExpect(status().isCreated());

        // Validate the EditarRegistrarFuente in the database
        List<EditarRegistrarFuente> editarRegistrarFuenteList = editarRegistrarFuenteRepository.findAll();
        assertThat(editarRegistrarFuenteList).hasSize(databaseSizeBeforeCreate + 1);
        EditarRegistrarFuente testEditarRegistrarFuente = editarRegistrarFuenteList.get(editarRegistrarFuenteList.size() - 1);
        assertThat(testEditarRegistrarFuente.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testEditarRegistrarFuente.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testEditarRegistrarFuente.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createEditarRegistrarFuenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = editarRegistrarFuenteRepository.findAll().size();

        // Create the EditarRegistrarFuente with an existing ID
        editarRegistrarFuente.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEditarRegistrarFuenteMockMvc.perform(post("/api/editar-registrar-fuentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(editarRegistrarFuente)))
            .andExpect(status().isBadRequest());

        // Validate the EditarRegistrarFuente in the database
        List<EditarRegistrarFuente> editarRegistrarFuenteList = editarRegistrarFuenteRepository.findAll();
        assertThat(editarRegistrarFuenteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEditarRegistrarFuentes() throws Exception {
        // Initialize the database
        editarRegistrarFuenteRepository.saveAndFlush(editarRegistrarFuente);

        // Get all the editarRegistrarFuenteList
        restEditarRegistrarFuenteMockMvc.perform(get("/api/editar-registrar-fuentes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(editarRegistrarFuente.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)));
    }
    
    @Test
    @Transactional
    public void getEditarRegistrarFuente() throws Exception {
        // Initialize the database
        editarRegistrarFuenteRepository.saveAndFlush(editarRegistrarFuente);

        // Get the editarRegistrarFuente
        restEditarRegistrarFuenteMockMvc.perform(get("/api/editar-registrar-fuentes/{id}", editarRegistrarFuente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(editarRegistrarFuente.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO));
    }
    @Test
    @Transactional
    public void getNonExistingEditarRegistrarFuente() throws Exception {
        // Get the editarRegistrarFuente
        restEditarRegistrarFuenteMockMvc.perform(get("/api/editar-registrar-fuentes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEditarRegistrarFuente() throws Exception {
        // Initialize the database
        editarRegistrarFuenteService.save(editarRegistrarFuente);

        int databaseSizeBeforeUpdate = editarRegistrarFuenteRepository.findAll().size();

        // Update the editarRegistrarFuente
        EditarRegistrarFuente updatedEditarRegistrarFuente = editarRegistrarFuenteRepository.findById(editarRegistrarFuente.getId()).get();
        // Disconnect from session so that the updates on updatedEditarRegistrarFuente are not directly saved in db
        em.detach(updatedEditarRegistrarFuente);
        updatedEditarRegistrarFuente
            .codigo(UPDATED_CODIGO)
            .descripcion(UPDATED_DESCRIPCION)
            .estado(UPDATED_ESTADO);

        restEditarRegistrarFuenteMockMvc.perform(put("/api/editar-registrar-fuentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEditarRegistrarFuente)))
            .andExpect(status().isOk());

        // Validate the EditarRegistrarFuente in the database
        List<EditarRegistrarFuente> editarRegistrarFuenteList = editarRegistrarFuenteRepository.findAll();
        assertThat(editarRegistrarFuenteList).hasSize(databaseSizeBeforeUpdate);
        EditarRegistrarFuente testEditarRegistrarFuente = editarRegistrarFuenteList.get(editarRegistrarFuenteList.size() - 1);
        assertThat(testEditarRegistrarFuente.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testEditarRegistrarFuente.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testEditarRegistrarFuente.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingEditarRegistrarFuente() throws Exception {
        int databaseSizeBeforeUpdate = editarRegistrarFuenteRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEditarRegistrarFuenteMockMvc.perform(put("/api/editar-registrar-fuentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(editarRegistrarFuente)))
            .andExpect(status().isBadRequest());

        // Validate the EditarRegistrarFuente in the database
        List<EditarRegistrarFuente> editarRegistrarFuenteList = editarRegistrarFuenteRepository.findAll();
        assertThat(editarRegistrarFuenteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEditarRegistrarFuente() throws Exception {
        // Initialize the database
        editarRegistrarFuenteService.save(editarRegistrarFuente);

        int databaseSizeBeforeDelete = editarRegistrarFuenteRepository.findAll().size();

        // Delete the editarRegistrarFuente
        restEditarRegistrarFuenteMockMvc.perform(delete("/api/editar-registrar-fuentes/{id}", editarRegistrarFuente.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EditarRegistrarFuente> editarRegistrarFuenteList = editarRegistrarFuenteRepository.findAll();
        assertThat(editarRegistrarFuenteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

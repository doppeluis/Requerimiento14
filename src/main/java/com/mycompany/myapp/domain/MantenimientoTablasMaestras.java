package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A MantenimientoTablasMaestras.
 */
@Entity
@Table(name = "mantenimiento_tablas_maestras")
public class MantenimientoTablasMaestras implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tecnica_evaluativa")
    private String tecnicaEvaluativa;

    @ManyToOne
    @JsonIgnoreProperties(value = "mantenimientoTablasMaestras", allowSetters = true)
    private EditarRegistrarFuente operaciones;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTecnicaEvaluativa() {
        return tecnicaEvaluativa;
    }

    public MantenimientoTablasMaestras tecnicaEvaluativa(String tecnicaEvaluativa) {
        this.tecnicaEvaluativa = tecnicaEvaluativa;
        return this;
    }

    public void setTecnicaEvaluativa(String tecnicaEvaluativa) {
        this.tecnicaEvaluativa = tecnicaEvaluativa;
    }

    public EditarRegistrarFuente getOperaciones() {
        return operaciones;
    }

    public MantenimientoTablasMaestras operaciones(EditarRegistrarFuente editarRegistrarFuente) {
        this.operaciones = editarRegistrarFuente;
        return this;
    }

    public void setOperaciones(EditarRegistrarFuente editarRegistrarFuente) {
        this.operaciones = editarRegistrarFuente;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MantenimientoTablasMaestras)) {
            return false;
        }
        return id != null && id.equals(((MantenimientoTablasMaestras) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MantenimientoTablasMaestras{" +
            "id=" + getId() +
            ", tecnicaEvaluativa='" + getTecnicaEvaluativa() + "'" +
            "}";
    }
}

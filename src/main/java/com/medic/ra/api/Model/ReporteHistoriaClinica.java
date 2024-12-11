package com.medic.ra.api.Model;



import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "reportehistoriaclinica")
public class ReporteHistoriaClinica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@OneToMany(mappedBy = "reporteHistoriaClinica")
    private List<String> consultas;

    private List<String> diagnosticosFrecuentes;
    private int paciente_id;

    // Getter y Setter para id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter y Setter para consultas
    public List<String> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<String> consultas) {
        this.consultas = consultas;
    }

    // Getter y Setter para diagnosticosFrecuentes
    public List<String> getDiagnosticosFrecuentes() {
        return diagnosticosFrecuentes;
    }

    public void setDiagnosticosFrecuentes(List<String> diagnosticosFrecuentes) {
        this.diagnosticosFrecuentes = diagnosticosFrecuentes;
    }

    // Getter y Setter para paciente_id
    public int getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(int paciente_id) {
        this.paciente_id = paciente_id;
    }


}

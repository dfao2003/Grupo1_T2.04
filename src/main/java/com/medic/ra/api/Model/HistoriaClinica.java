package com.medic.ra.api.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "historiaclinica")
public class HistoriaClinica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private int paciente_id;

    private Date fechaInicio;

    private String estadoPaciente;

    private String notas;

    private Date fechaUltimaActualizacion;

    private int numeroHistoriaClinica;
}

package com.medic.ra.api.Repository.Paciente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.medic.ra.api.Model.CertificadoMedico;
import com.medic.ra.api.Model.Cita;

@Repository
public class PacienteRepository implements IPacienteRepository{

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<Cita> verHistorialCitas(int id) {
        String sql = "SELECT * FROM cita where paciente_id = ?";
        return jdbc.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Cita.class));
    }

    @Override
    public List<CertificadoMedico> solicitarCertificado(int medico) {
        String sql = "SELECT * FROM certificadomedico where medico_id = ?";
         return jdbc.query(sql, new Object[]{medico}, new BeanPropertyRowMapper<>(CertificadoMedico.class));
    }

    @Override
    public int crearCertificado(CertificadoMedico medico) {
        String sql = "INSERT INTO certificadomedico (paciente_id, medico_id, descripcionEnfermedad, fechaEmision, fechaVencimiento) VALUES (?, ?, ?, ?, ?)";

        return jdbc.update(sql, medico.getPaciente_id(), medico.getMedico_id(), medico.getDescripcionEnfermedad(), medico.getFechaEmision(), medico.getFechaVencimiento());
    }
    
}

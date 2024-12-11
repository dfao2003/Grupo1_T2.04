package com.medic.ra.api.Repository.Medico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.medic.ra.api.Model.CertificadoMedico;
import com.medic.ra.api.Model.Cita;
import com.medic.ra.api.Model.Consulta;
import com.medic.ra.api.Model.Medico;
import com.medic.ra.api.Model.Paciente;
import com.medic.ra.api.Model.Recordatorio;

@Repository
public class MedicoRepository implements IMedicoRepository{

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<Cita> verAgenda(int id) {
        String sql = "SELECT * FROM certificadomedico where medico_id = ?";
        return jdbc.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Cita.class));
    }

    @Override
    public void registrarConsulta(Paciente paciente, Consulta consulta) {
        String sql = "INSERT INTO paciente(nombre, apellido, cedula, telefono, direccion, correo, diagnostico) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        String verificarCedula = "SELECT * FROM paciente where cedula = ?";
        List<Paciente> ced = jdbc.query(verificarCedula, new Object[]{paciente.getCedula()}, new BeanPropertyRowMapper<>(Paciente.class));
        if(ced.size() <= 0){
            jdbc.update(sql, paciente.getNombre(), paciente.getApellido(), paciente.getCedula(), paciente.getTelefono(), paciente.getDireccion(), paciente.getCorreo(), paciente.getDiagnostico());
        }
        
        String consultaP = "SELECT * FROM paciente where cedula = ?";
        List<Paciente> pacientes = jdbc.query(consultaP, new Object[]{paciente.getCedula()}, new BeanPropertyRowMapper<>(Paciente.class));

        for(Paciente pa: pacientes){
            String sql2 = "INSERT INTO consulta(fecha, detalles, signosvitales, paciente_id, medico_id) VALUES (?, ?, ?, ?, ?)";
            jdbc.update(sql2, consulta.getFecha(), consulta.getDetalles(), consulta.getSignosVitales(), pa.getId(), consulta.getMedico_id());
        }
    }

    @Override
    public List<CertificadoMedico> emitirCertificados(int paciente) {
        String sql = "SELECT * FROM certificadomedico where paciente_id = ?";
        return jdbc.query(sql, new Object[]{paciente}, new BeanPropertyRowMapper<>(CertificadoMedico.class));
    }

    @Override
    public int crearMedico(Medico medico) {
        String sql = "INSERT INTO medico(especialidad, usuario_id) VALUES (?, ?)";
        try {
            System.out.println("Usuario creado con éxito.");
            return jdbc.update(sql, medico.getEspecialidad(), medico.getUsuario_id());
            
        } catch (DataAccessException e) {
            System.err.println("Error al insertar el medico: " + e.getMessage());
            return 0;
        }
    }


    @Override
    public int crearCita(Cita cita) {
        String sql = "INSERT INTO cita(fecha, hora, estado, paciente_id, medico_id) VALUES (?, ?, ?, ?, ?)";
        try {
            System.out.println("Cita creada con éxito.");
            return jdbc.update(sql, cita.getFecha(), cita.getHora(), cita.getHora(), cita.getPaciente_id(), cita.getMedico_id());
            
        } catch (DataAccessException e) {
            System.err.println("Error al insertar el medico: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int crearRecordatorio(Recordatorio recordatorio) {
        String sql = "INSERT INTO recordatorio(fechaenvio, mensaje, cita_id) VALUES (?, ?, ?)";
        try {
            System.out.println("Recoratorio creado con éxito.");
            return jdbc.update(sql, recordatorio.getFechaEnvio(), recordatorio.getMensaje(), recordatorio.getCita_id());
            
        } catch (DataAccessException e) {
            System.err.println("Error al insertar el medico: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int crearPaciente(Paciente paciente) {
        String sql = "INSERT INTO paciente(nombre, apellido, cedula, telefono, direccion, correo, diagnostico) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String verificarCedula = "SELECT * FROM paciente where cedula = ?";
        List<Paciente> ced = jdbc.query(verificarCedula, new Object[]{paciente.getCedula()}, new BeanPropertyRowMapper<>(Paciente.class));
        if(ced.size() <= 0){
            return jdbc.update(sql, paciente.getNombre(), paciente.getApellido(), paciente.getCedula(), paciente.getTelefono(), paciente.getDireccion(), paciente.getCorreo(), paciente.getDiagnostico());
        }
        return 0;
    }

    @Override
    public int cancelarCita(int id, Cita cita) {
        String sql = "UPDATE cita set fecha = ?, hora = ?, estado = ?, paciente_id = ?, medico = ? where id = ?";
        try {
            System.out.println("Cita creada con éxito.");
            return jdbc.update(sql, cita.getFecha(), cita.getHora(), cita.getHora(), cita.getPaciente_id(), cita.getMedico_id(), id);
            
        } catch (DataAccessException e) {
            System.err.println("Error al insertar el medico: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int confirmarCita(int id, Cita cita) {
        String sql = "UPDATE cita set fecha = ?, hora = ?, estado = ?, paciente_id = ?, medico = ? where id = ?";
        try {
            System.out.println("Cita creada con éxito.");
            return jdbc.update(sql, cita.getFecha(), cita.getHora(), cita.getHora(), cita.getPaciente_id(), cita.getMedico_id(), id);
            
        } catch (DataAccessException e) {
            System.err.println("Error al insertar el medico: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int enviarRecordatorio(Recordatorio recordatorio) {
        String sql = "INSERT INTO recordatorio(fechaenvio, mensaje, cita_id) VALUES (?, ?, ?)";
        String verificarCedula = "SELECT * FROM recordatorio where id = ?";
        List<Recordatorio> ced = jdbc.query(verificarCedula, new Object[]{recordatorio.getId()}, new BeanPropertyRowMapper<>(Recordatorio.class));
        if(ced.size() <= 0){
            return jdbc.update(sql, recordatorio.getFechaEnvio(), recordatorio.getMensaje(), recordatorio.getCita_id());
        }
        return 0;
    }

    
    
}

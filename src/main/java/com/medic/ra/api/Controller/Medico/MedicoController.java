package com.medic.ra.api.Controller.Medico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medic.ra.api.Model.CertificadoMedico;
import com.medic.ra.api.Model.Cita;
import com.medic.ra.api.Model.Consulta;
import com.medic.ra.api.Model.Medico;
import com.medic.ra.api.Model.Paciente;
import com.medic.ra.api.Model.Recordatorio;
import com.medic.ra.api.Repository.Medico.IMedicoRepository;


@Service
public class MedicoController implements IMedicoController{
    
    @Autowired
    private IMedicoRepository rep;

    @Override
    public List<Cita> verAgenda(int id) {
        List<Cita> listado = null;
        try {
            
            listado = rep.verAgenda(id);
        } catch (Exception e) {
            throw e;
        }
        return listado;
    }

    @Override
    public void registrarConsulta(Paciente paciente, Consulta consulta) {
        try {
            rep.registrarConsulta(paciente, consulta);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<CertificadoMedico> emitirCertificados(int paciente) {
        List<CertificadoMedico> certificado = null;
        try {
            certificado = rep.emitirCertificados(paciente);
        } catch (Exception e) {
            throw e;
        }
        return certificado;
    }

    @Override
    public int crearMedico(Medico medico) {
        int row =0;
        try {
            row = rep.crearMedico(medico);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int crearPaciente(Paciente paciente) {
        int row = 0;
        try {
            row = rep.crearPaciente(paciente);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int crearRecordatorio(Recordatorio recordatorio) {
        int row = 0;
        try {
            row = rep.crearRecordatorio(recordatorio);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int programarCita(Cita cita) {
        int row = 0;
        try {
            row = rep.crearCita(cita);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int cancelarCita(int id, Cita cita) {
        int row = 0;
        try {
            row = rep.cancelarCita(id, cita);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int confirmarCita(int id, Cita cita) {
        int row = 0;
        try {
            row = rep.confirmarCita(id, cita);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int enviarRecordatorio(Recordatorio recordatorio) {
        int row = 0;
        try {
            row = rep.enviarRecordatorio(recordatorio);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }
}

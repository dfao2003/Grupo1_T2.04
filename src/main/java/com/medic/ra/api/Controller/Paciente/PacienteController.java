package com.medic.ra.api.Controller.Paciente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medic.ra.api.Model.CertificadoMedico;
import com.medic.ra.api.Model.Cita;
import com.medic.ra.api.Repository.Paciente.IPacienteRepository;

@Service
public class PacienteController implements IPacienteController{

    @Autowired
    private IPacienteRepository rep;


    @Override
    public List<Cita> verHistorialCitas(int id) {
        List<Cita> listado = null;

        try{
            listado = rep.verHistorialCitas(id);
        }catch(Exception e){
            throw e;
        }
        return listado;
    }

    @Override
    public List<CertificadoMedico> solicitarCertificado(int medico) {
        List<CertificadoMedico> certificado = null;
        try {
            certificado = rep.solicitarCertificado(medico);    
        } catch (Exception e) {
            throw e;
        }
        return certificado;
    }

    @Override
    public int crearCertificado(CertificadoMedico medico) {
        int row = 0;
        try {
            row = rep.crearCertificado(medico);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }
    
}

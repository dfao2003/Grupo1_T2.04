package com.medic.ra.api.Repository.Paciente;

import java.util.List;

import com.medic.ra.api.Model.CertificadoMedico;
import com.medic.ra.api.Model.Cita;

public interface IPacienteRepository {
    List<Cita> verHistorialCitas(int id);
    List<CertificadoMedico> solicitarCertificado(int medico);
    int crearCertificado(CertificadoMedico medico);
}

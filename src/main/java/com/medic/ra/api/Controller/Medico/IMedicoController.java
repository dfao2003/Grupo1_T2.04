package com.medic.ra.api.Controller.Medico;

import java.util.List;

import com.medic.ra.api.Model.CertificadoMedico;
import com.medic.ra.api.Model.Cita;
import com.medic.ra.api.Model.Consulta;
import com.medic.ra.api.Model.Medico;
import com.medic.ra.api.Model.Paciente;
import com.medic.ra.api.Model.Recordatorio;

public interface IMedicoController {
    List<Cita> verAgenda(int id);
    void registrarConsulta(Paciente paciente, Consulta consulta);
    List<CertificadoMedico> emitirCertificados(int paciente);
    int crearPaciente(Paciente paciente);
    int crearMedico(Medico medico);
    int crearRecordatorio(Recordatorio recordatorio);
    int programarCita(Cita cita);
    int cancelarCita(int id, Cita cita);
    int confirmarCita(int id, Cita cita);
    int enviarRecordatorio(Recordatorio recordatorio);
}

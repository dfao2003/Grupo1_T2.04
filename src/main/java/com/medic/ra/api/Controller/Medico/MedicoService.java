package com.medic.ra.api.Controller.Medico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medic.ra.api.Model.CertificadoMedico;
import com.medic.ra.api.Model.Cita;
import com.medic.ra.api.Model.Consulta;
import com.medic.ra.api.Model.Medico;
import com.medic.ra.api.Model.Paciente;
import com.medic.ra.api.Model.PacienteConsulta;
import com.medic.ra.api.Model.Recordatorio;
import com.medic.ra.api.Model.ServiceResponse;



@RestController
@RequestMapping("api/medico")
@CrossOrigin("*")
public class MedicoService {
    @Autowired
    private MedicoController controller;


    @GetMapping("/agenda/{id}")
    public ResponseEntity<List<Cita>> getAll(@PathVariable int id) {
        var result = controller.verAgenda(id);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    

    @GetMapping("/certificado/{id}")
    public ResponseEntity<List<CertificadoMedico>> getCertificado(@PathVariable int id) {
        var result = controller.emitirCertificados(id);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<ServiceResponse> crear(@RequestBody PacienteConsulta variable) {
        ServiceResponse serv = new ServiceResponse();

        System.out.println(variable);
        Paciente paciente = new Paciente();
        paciente.setApellido(variable.getApellido());
        paciente.setNombre(variable.getNombre());
        paciente.setCedula(variable.getCedula());
        paciente.setCorreo(variable.getCorreo());
        paciente.setDiagnostico(variable.getDiagnostico());
        paciente.setDireccion(variable.getDireccion());
        paciente.setTelefono(variable.getTelefono());



        Consulta consulta = new Consulta();
        consulta.setDetalles(variable.getDetalles());
        consulta.setFecha(variable.getFecha());
        consulta.setMedico_id(variable.getMedico_id());
        consulta.setPaciente_id(variable.getPaciente_id());
        consulta.setSignosVitales(variable.getSignosVitales());

        controller.registrarConsulta(paciente, consulta);
        return new ResponseEntity<>(serv, HttpStatus.OK);
    }
    
    @PostMapping("/crearmedico")
    public ResponseEntity<ServiceResponse> crearMedico(@RequestBody Medico entity) {
        ServiceResponse serv = new ServiceResponse();
        int result = controller.crearMedico(entity);
        if(result == 1){
            serv.setMessage("Item creado");
        } 
        return new ResponseEntity<>(serv, HttpStatus.OK);
    }
    
    @PostMapping("/crearcita")
    public ResponseEntity<ServiceResponse> crearCita(@RequestBody Cita entity) {
        ServiceResponse serv = new ServiceResponse();
        int result = controller.programarCita(entity);
        if(result == 1){
            serv.setMessage("Item creado");
        } 
        return new ResponseEntity<>(serv, HttpStatus.OK);
    }

    @PostMapping("/crearpaciente")
    public ResponseEntity<ServiceResponse> crearPaciente(@RequestBody Paciente entity) {
        ServiceResponse serv = new ServiceResponse();
        int result = controller.crearPaciente(entity);
        if(result == 1){
            serv.setMessage("Item creado");
        } 
        return new ResponseEntity<>(serv, HttpStatus.OK);
    }

    @PostMapping("/crearrecordatorio")
    public ResponseEntity<ServiceResponse> crearRecordatorio(@RequestBody Recordatorio entity) {
        ServiceResponse serv = new ServiceResponse();
        int result = controller.crearRecordatorio(entity);
        if(result == 1){
            serv.setMessage("Item creado");
        } 
        return new ResponseEntity<>(serv, HttpStatus.OK);
    }

    @PutMapping("cancelarCita/{id}")
    public ResponseEntity<ServiceResponse> cancelarCita(@PathVariable int id, @RequestBody Cita cita) {
        ServiceResponse serv = new ServiceResponse();
        int result = controller.cancelarCita(id, cita);
        if(result == 1){
            serv.setMessage("Item cancelado");
        } 
        return new ResponseEntity<>(serv, HttpStatus.OK);
    }
    @PutMapping("confirmarCita/{id}")
    public ResponseEntity<ServiceResponse> confirmarCita(@PathVariable int id, @RequestBody Cita cita) {
        ServiceResponse serv = new ServiceResponse();
        int result = controller.confirmarCita(id, cita);
        if(result == 1){
            serv.setMessage("Item confirmado");
        } 
        return new ResponseEntity<>(serv, HttpStatus.OK);
    }

    @PostMapping("enviarRecordatorio")
    public ResponseEntity<ServiceResponse> enviarRecordatorio(@RequestBody Recordatorio entity) {
        ServiceResponse serv = new ServiceResponse();
        int result = controller.enviarRecordatorio(entity);
        if(result == 1){
            serv.setMessage("Item enviado");
        } 
        return new ResponseEntity<>(serv, HttpStatus.OK);
    }
    
    
}

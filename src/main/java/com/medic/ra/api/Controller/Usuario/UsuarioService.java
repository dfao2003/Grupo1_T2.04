package com.medic.ra.api.Controller.Usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medic.ra.api.Model.Consulta;
import com.medic.ra.api.Model.Paciente;
import com.medic.ra.api.Model.PacienteConsulta;
import com.medic.ra.api.Model.ServiceResponse;
import com.medic.ra.api.Model.Usuario;





@RestController
@RequestMapping("api/usuario")
@CrossOrigin("*")
public class UsuarioService {
    @Autowired
    private IUsuarioController controller;
    
    @PostMapping("/login/{email}/{pass}")
    public ResponseEntity<Usuario> Login(@PathVariable String email, @PathVariable String pass) {
        Usuario result = controller.login(email, pass);
        System.out.println(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ServiceResponse> update(@RequestBody Usuario usuario){
        ServiceResponse serv = new ServiceResponse();
        int result = controller.updateUsuario(usuario);
        if(result == 1){
            serv.setMessage("Item actualizado");
        }
        return new ResponseEntity<>(serv, HttpStatus.OK);
        
    }
    
    @GetMapping("list")
    public ResponseEntity<List<Usuario>> list() {
        var result = controller.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("save")
    public ResponseEntity<ServiceResponse> save (@RequestBody Usuario usuario) {
        ServiceResponse serv = new ServiceResponse();
        int result = controller.crearUsuario(usuario);
        if(result == 1){
            serv.setMessage("Item agregado");
        }
        return new ResponseEntity<>(serv, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ServiceResponse> delete(@PathVariable int id){
        ServiceResponse serv = new ServiceResponse();
        int result = controller.deleteUsuario(id);
        if(result == 1){
            serv.setMessage("Item eliminado");
        }
        return new ResponseEntity<>(serv, HttpStatus.OK);
    }

    @PostMapping("generarReporte")
    public ResponseEntity<ServiceResponse> generarReporte(@RequestBody PacienteConsulta variable) {
        ServiceResponse serv = new ServiceResponse();
        Consulta consulta = new Consulta();        
        consulta.setDetalles(variable.getDetalles());
        consulta.setFecha(variable.getFecha());
        consulta.setMedico_id(variable.getMedico_id());
        consulta.setPaciente_id(variable.getPaciente_id());
        consulta.setSignosVitales(variable.getSignosVitales());


        Paciente paciente = new Paciente();
        paciente.setId(variable.getPaciente_id());
        paciente.setDiagnostico(variable.getDiagnostico());
        System.out.println(variable.getPaciente_id());
        System.out.println("entra en la generacion del reporte");
        int result = controller.generarReporte(consulta, paciente);
        if(result == 1){
            serv.setMessage("Item generado");
        }
        return new ResponseEntity<>(serv, HttpStatus.OK);
    }
    
}

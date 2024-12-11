package com.medic.ra.api.Controller.Medico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.medic.ra.api.Model.CertificadoMedico;
import com.medic.ra.api.Model.Cita;
import com.medic.ra.api.Model.Medico;
import com.medic.ra.api.Model.PacienteConsulta;
import com.medic.ra.api.Model.Recordatorio;
import com.medic.ra.api.Model.ServiceResponse;
import static org.mockito.Mockito.*;


public class MedicoServiceTest {

@Mock
private MedicoController controller; 

@InjectMocks
private MedicoService medicoService; 

@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this); 
}

@Test
void testGetAgenda() {
    List<Cita> citas = new ArrayList<>();
    Cita cita1 = new Cita();
    cita1.setId(1); 
    citas.add(cita1);

    when(controller.verAgenda(1)).thenReturn(citas); 
    
    var response = medicoService.getAll(1);
    
    assertEquals(200, response.getStatusCodeValue()); 
    assertEquals(1, response.getBody().size()); 
}

@Test
void testGetCertificado() {
    
    List<CertificadoMedico> certificados = new ArrayList<>();
    CertificadoMedico certificado = new CertificadoMedico();
    certificados.add(certificado);

    when(controller.emitirCertificados(1)).thenReturn(certificados); 
    
    var response = medicoService.getCertificado(1);
    
    assertEquals(200, response.getStatusCodeValue()); 
    assertEquals(1, response.getBody().size()); 
}

@Test
void testRegistrar() {
    
    PacienteConsulta consulta = new PacienteConsulta();
    consulta.setNombre("John");
    consulta.setApellido("Doe");

    ServiceResponse serviceResponse = new ServiceResponse();

    doNothing().when(controller).registrarConsulta(any(), any()); 

    var response = medicoService.crear(consulta);
    
    assertEquals(200, response.getStatusCodeValue()); 
}

@Test
void testCrearMedico() {
    
    Medico medico = new Medico();
    medico.setEspecialidad("Cardiología");

    ServiceResponse serviceResponse = new ServiceResponse();
    serviceResponse.setMessage("Item creado");

    when(controller.crearMedico(medico)).thenReturn(1); 

    var response = medicoService.crearMedico(medico);

    assertEquals(200, response.getStatusCodeValue()); // Código de estado debe ser 200
    assertEquals("Item creado", response.getBody().getMessage()); // Verificamos el mensaje
}

@Test
void testCancelarCita() {
    
    Cita cita = new Cita();
    cita.setId(1);

    ServiceResponse serviceResponse = new ServiceResponse();

    when(controller.cancelarCita(anyInt(), any())).thenReturn(1);
    
    var response = medicoService.cancelarCita(1, cita);
    
    assertEquals(200, response.getStatusCodeValue()); 
    assertEquals("Item cancelado", response.getBody().getMessage()); 
}

@Test
void testConfirmarCita() {
    
    Cita cita = new Cita();
    cita.setId(1);

    ServiceResponse serviceResponse = new ServiceResponse();

    when(controller.confirmarCita(anyInt(), any())).thenReturn(1); 

    var response = medicoService.confirmarCita(1, cita);
    
    assertEquals(200, response.getStatusCodeValue()); 
    assertEquals("Item confirmado", response.getBody().getMessage()); 
}

@Test
void testEnviarRecordatorio() {
    
    Recordatorio recordatorio = new Recordatorio();
    recordatorio.setId(1);

    ServiceResponse serviceResponse = new ServiceResponse();

    when(controller.enviarRecordatorio(recordatorio)).thenReturn(1); 

    var response = medicoService.enviarRecordatorio(recordatorio);
    
    assertEquals(200, response.getStatusCodeValue()); 
    assertEquals("Item enviado", response.getBody().getMessage()); 
}
}

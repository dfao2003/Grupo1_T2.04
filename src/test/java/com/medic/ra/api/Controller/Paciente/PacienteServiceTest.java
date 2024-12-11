package com.medic.ra.api.Controller.Paciente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.medic.ra.api.Model.CertificadoMedico;
import com.medic.ra.api.Model.Cita;
import com.medic.ra.api.Model.ServiceResponse;

public class PacienteServiceTest {

    @Mock
    private IPacienteController controller;

    @InjectMocks
    private PacienteService pacienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    void testGetAllHistorialCitas() {
        List<Cita> citas = new ArrayList<>();
        Cita cita1 = new Cita(); 
        cita1.setId(1); 
        citas.add(cita1);
        
        when(controller.verHistorialCitas(1)).thenReturn(citas); 
        
        var response = pacienteService.getAll(1);
        
        assertEquals(200, response.getStatusCodeValue()); // Verifica que el código de estado sea 200
        assertEquals(1, response.getBody().size()); // Verifica que se haya retornado una cita
    }

    @Test
    void testGetCertificado() {
       
        List<CertificadoMedico> certificados = new ArrayList<>();
        CertificadoMedico certificado = new CertificadoMedico(); 
        certificados.add(certificado);

        when(controller.solicitarCertificado(1)).thenReturn(certificados); 
        
        var response = pacienteService.getMethodName(1);
        
        assertEquals(200, response.getStatusCodeValue()); 
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testRegistrarCertificado() {
        
        CertificadoMedico certificado = new CertificadoMedico();
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setMessage("Item agregado");

        when(controller.crearCertificado(certificado)).thenReturn(1); 
        
        var response = pacienteService.postMethodName(certificado);
        
        assertEquals(200, response.getStatusCodeValue()); 
        assertEquals("Item agregado", response.getBody().getMessage()); 
    }
    
}

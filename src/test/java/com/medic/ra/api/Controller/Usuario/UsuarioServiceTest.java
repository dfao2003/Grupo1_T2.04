package com.medic.ra.api.Controller.Usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.medic.ra.api.Model.Consulta;
import com.medic.ra.api.Model.Paciente;
import com.medic.ra.api.Model.PacienteConsulta;
import com.medic.ra.api.Model.ServiceResponse;
import com.medic.ra.api.Model.Usuario;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private IUsuarioController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin() {
        String email = "test@example.com";
        String pass = "1234";
        Usuario mockUsuario = new Usuario();
        mockUsuario.setEmail(email);
        mockUsuario.setPass(pass);

        when(controller.login(email, pass)).thenReturn(mockUsuario);

        ResponseEntity<Usuario> response = usuarioService.Login(email, pass);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(email, response.getBody().getEmail());
        verify(controller, times(1)).login(email, pass);
    }

    @Test
    void testUpdate() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setEmail("updated@example.com");
        usuario.setPass("newpassword");

        when(controller.updateUsuario(usuario)).thenReturn(1);

        ResponseEntity<ServiceResponse> response = usuarioService.update(usuario);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Item actualizado", response.getBody().getMessage());
        verify(controller, times(1)).updateUsuario(usuario);
    }

    @Test
    void testList() {
        Usuario usuario1 = new Usuario();
        usuario1.setId(1);
        usuario1.setEmail("user1@example.com");

        Usuario usuario2 = new Usuario();
        usuario2.setId(2);
        usuario2.setEmail("user2@example.com");

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        when(controller.getAll()).thenReturn(usuarios);

        ResponseEntity<List<Usuario>> response = usuarioService.list();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(controller, times(1)).getAll();
    }

    @Test
    void testSave() {
        Usuario usuario = new Usuario();
        usuario.setEmail("newuser@example.com");
        usuario.setPass("password");

        when(controller.crearUsuario(usuario)).thenReturn(1);

        ResponseEntity<ServiceResponse> response = usuarioService.save(usuario);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Item agregado", response.getBody().getMessage());
        verify(controller, times(1)).crearUsuario(usuario);
    }

    @Test
    void testDelete() {
        int userId = 1;

        when(controller.deleteUsuario(userId)).thenReturn(1);

        ResponseEntity<ServiceResponse> response = usuarioService.delete(userId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Item eliminado", response.getBody().getMessage());
        verify(controller, times(1)).deleteUsuario(userId);
    }

    @Test
    void testGenerarReporte() {
        PacienteConsulta consulta = new PacienteConsulta();
        consulta.setPaciente_id(1);
        consulta.setDiagnostico("Diagnóstico prueba");

        when(controller.generarReporte(any(Consulta.class), any(Paciente.class))).thenReturn(1);

        ResponseEntity<ServiceResponse> response = usuarioService.generarReporte(consulta);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Item generado", response.getBody().getMessage());
        verify(controller, times(1)).generarReporte(any(Consulta.class), any(Paciente.class));
    }
}

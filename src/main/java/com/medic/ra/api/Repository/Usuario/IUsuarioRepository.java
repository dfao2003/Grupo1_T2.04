package com.medic.ra.api.Repository.Usuario;

import java.util.List;

import com.medic.ra.api.Model.Consulta;
import com.medic.ra.api.Model.Paciente;
import com.medic.ra.api.Model.Usuario;

public interface IUsuarioRepository {
    Usuario iniciarSesion(String email, String password);
    void cerrarSesion();
    int crearUsuario(Usuario usuario);
    int update(Usuario usuario);
    int delete(int id);
    List<Usuario> getAll();
    int generarReporte(Consulta consulta, Paciente paciente);
    
}

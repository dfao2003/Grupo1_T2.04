package com.medic.ra.api.Controller.Usuario;

import java.util.List;

import com.medic.ra.api.Model.Consulta;
import com.medic.ra.api.Model.Paciente;
import com.medic.ra.api.Model.Usuario;

public interface IUsuarioController {
    Usuario login(String email, String password);
    void cerrarSesion();
    int crearUsuario(Usuario usuario);
    int updateUsuario(Usuario usuario);
    int deleteUsuario(int id);
    List<Usuario> getAll();
    int generarReporte(Consulta consulta, Paciente paciente);

}

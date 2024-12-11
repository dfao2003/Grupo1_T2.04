package com.medic.ra.api.Controller.Usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medic.ra.api.Model.Consulta;
import com.medic.ra.api.Model.Paciente;
import com.medic.ra.api.Model.Usuario;
import com.medic.ra.api.Repository.Usuario.IUsuarioRepository;


@Service
public class UsuarioController implements IUsuarioController{

    @Autowired
    private IUsuarioRepository rep;

    @Override
    public Usuario login(String email, String password) {
        Usuario usuario = null;

        try{
            usuario = rep.iniciarSesion(email, password);
        }catch(Exception e){
            throw e;
        }
        return usuario;
    }

    @Override
    public void cerrarSesion() {
        
    }

    @Override
    public int crearUsuario(Usuario usuario) {
        int row;
        try{
            row = rep.crearUsuario(usuario);
        }catch(Exception e){
            throw e;
        }
        return row;
    }

    @Override
    public int deleteUsuario(int id) {
        int row;
        try{
            row = rep.delete(id);
        }catch(Exception e){
            throw e;
        }
        return row;
    }

    @Override
    public List<Usuario> getAll() {
        List<Usuario> listado = null;
        try{
            listado = rep.getAll();
        }catch(Exception e){
            throw e;
        }
        return listado;
    }

    @Override
    public int updateUsuario(Usuario usuario) {
        int row;
        try{
            row = rep.update(usuario);
        }catch(Exception e){
            throw e;
        }
        return row;
    }

    @Override
    public int generarReporte(Consulta consulta, Paciente paciente) {
        int row;
        try{
            row = rep.generarReporte(consulta, paciente);
        }catch(Exception e){
            throw e;
        }
        return row;
    }
    
}

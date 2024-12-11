package com.medic.ra.api.Repository.Usuario;


import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.medic.ra.api.Model.Consulta;
import com.medic.ra.api.Model.Paciente;
import com.medic.ra.api.Model.Usuario;

@Repository
public class UsuarioRepository implements IUsuarioRepository{

    @Autowired
    private JdbcTemplate jdbc;
    
    @Override
    public Usuario iniciarSesion(String email, String password) {
        String sql = "SELECT * FROM usuario where email = ? and pass = ?";
        return jdbc.queryForObject(sql, new Object[]{email, password}, new BeanPropertyRowMapper<>(Usuario.class));
    }

    @Override
    public void cerrarSesion() {
        
        throw new UnsupportedOperationException("Unimplemented method 'cerrarSesion'");
    }

    @Override
    public int crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario(nombre, apellido, telefono, direccion, email, pass) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            System.out.println("Usuario creado con éxito.");
            return jdbc.update(sql, usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getDireccion(), usuario.getEmail(), usuario.getPass());
            
        } catch (DataAccessException e) {
            System.err.println("Error al insertar el usuario: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(int id) {
        String sql = "Delete from usuario where id = ?";
        
        try{
            return jdbc.update(sql, new Object[]{id});
            
        }catch(Exception e){
            System.err.println("Error al insertar el usuario: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public List<Usuario> getAll() {
        String sql = "SELECT * FROM usuario";
        return jdbc.query(sql, BeanPropertyRowMapper.newInstance(Usuario.class));
    }

    @Override
    public int update(Usuario usuario) {
        String sql = "UPDATE usuario set nombre = ?, apellido = ?, telefono = ?, direccion = ?, email = ?, pass = ? where id = ?";
        try {
            System.out.println("Usuario creado con éxito.");
            return jdbc.update(sql, usuario.getNombre(), usuario.getApellido(), usuario.getTelefono(), usuario.getDireccion(), usuario.getEmail(), usuario.getPass(), usuario.getId());
            
        } catch (DataAccessException e) {
            System.err.println("Error al insertar el usuario: " + e.getMessage());
            return 0;
        }
    }

   @Override
public int generarReporte(Consulta consulta, Paciente paciente) {
    // Sentencia SQL para insertar el reporte en la base de datos
    String sql = "INSERT INTO reportehistoriaclinica(consultas, diagnosticosfrecuentes, paciente_id) VALUES (?, ?, ?)";

    try {
        // Consulta SQL para obtener las consultas del paciente
        String consul = "SELECT * FROM consulta WHERE paciente_id = ?";
        List<Consulta> consultas = jdbc.query(consul, new Object[]{paciente.getId()}, new BeanPropertyRowMapper<>(Consulta.class));

        // Verificamos si hay consultas disponibles para el paciente
        if (consultas.size() > 0) {
            // Extraer los diagnósticos de cada consulta utilizando un bucle tradicional
            List<String> diagnosticosFrecuentesList = new ArrayList<>();
            for (Consulta c : consultas) {
                diagnosticosFrecuentesList.add(c.getDiagnostico());
            }

            // Convertir la lista a un arreglo
            String[] diagnosticosFrecuentesArray = diagnosticosFrecuentesList.toArray(new String[0]);

            // Crear el array de PostgreSQL usando el tipo 'TEXT'
            Array sqlDiagnosticoArray = jdbc.getDataSource().getConnection().createArrayOf("TEXT", diagnosticosFrecuentesArray);

            // Ahora inserta el reporte con el arreglo de diagnósticos
            // Insertar el reporte en la base de datos
            return jdbc.update(sql, sqlDiagnosticoArray, paciente.getDiagnostico(), paciente.getId());
        }

    } catch (DataAccessException e) {
        // Manejo de excepciones específicas de acceso a datos
        System.err.println("Error al insertar el reporte: " + e.getMessage());
        throw e;
    } catch (SQLException e) {
        // Manejo de excepciones de SQL
        System.err.println("Error de SQL: " + e.getMessage());
        throw new RuntimeException(e);
    }

    return 0; // Retorna 0 si no se insertó el reporte
}

}

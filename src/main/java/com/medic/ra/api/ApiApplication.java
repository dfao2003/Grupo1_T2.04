package com.medic.ra.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.medic.ra.api.Model.Usuario;
import com.medic.ra.api.Repository.Usuario.UsuarioRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ApiApplication {
 	@Autowired
    private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

    @PostConstruct
    public void init() {
        // Crear un nuevo usuario
        Usuario usuario = new Usuario();
        Usuario usuario2 = new Usuario();
        Usuario usuario3 = new Usuario();
        Usuario usuario4 = new Usuario();
        Usuario usuario5 = new Usuario();

        //usuario.setId(1);
        usuario.setNombre("Jorge");
        usuario.setApellido("Lituma");
        usuario.setTelefono("1234567890");
        usuario.setDireccion("Calle 13");
        usuario.setEmail("jlitumat@est.ups.edu.ec");
        usuario.setPass("1234");

        //usuario2.setId(1);
        usuario2.setNombre("Marco");
        usuario2.setApellido("Cajamarca");
        usuario2.setTelefono("31251231321");
        usuario2.setDireccion("Bolivar");
        usuario2.setEmail("mcajamarcac@est.ups.edu.ec");
        usuario2.setPass("1234");

        //usuario3.setId(1);
        usuario3.setNombre("Diego");
        usuario3.setApellido("Loja");
        usuario3.setTelefono("12344513122");
        usuario3.setDireccion("Mariscal Lamar");
        usuario3.setEmail("dloja@est.ups.edu.ec");
        usuario3.setPass("1234");

        //usuario4.setId(1);
        usuario4.setNombre("Anthony");
        usuario4.setApellido("Moya");
        usuario4.setTelefono("3213423123");
        usuario4.setDireccion("La 18");
        usuario4.setEmail("amoyao@est.ups.edu.ec");
        usuario4.setPass("1234");

        //usuario5.setId(1);
        usuario5.setNombre("David");
        usuario5.setApellido("Alvarado");
        usuario5.setTelefono("41491313211");
        usuario5.setDireccion("Barrial Blanco");
        usuario5.setEmail("dalvarado@est.ups.edu.ec");
        usuario5.setPass("1234");
        // Guardar el usuario en la base de datos
        usuarioRepository.crearUsuario(usuario);
        usuarioRepository.crearUsuario(usuario2);
        usuarioRepository.crearUsuario(usuario3);
        usuarioRepository.crearUsuario(usuario4);
        usuarioRepository.crearUsuario(usuario5);

        System.out.println("Usuario inicial insertado en la base de datos");
    }

}

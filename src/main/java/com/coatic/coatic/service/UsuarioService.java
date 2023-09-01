package com.coatic.coatic.service;

import com.coatic.coatic.repository.*;
import com.coatic.coatic.entidades.*;
import jakarta.transaction.Transactional;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private BCryptPasswordEncoder codificador;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        List<GrantedAuthority> ga = buildAuthorities(usuario.getRol());
        return buildUser(usuario, ga);
    }

    public User buildUser(Usuario usuario, List<GrantedAuthority> ga) {
        return new User(usuario.getEmail(), usuario.getPassword(), ga);
    }

    public List<GrantedAuthority> buildAuthorities(Rol rol) {
        List<GrantedAuthority> ga = new ArrayList<>();
        ga.add( new SimpleGrantedAuthority("ROLE_" + rol.getNombre()) );
        return ga;
    }

    @Transactional
    public void registro(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail()))
            throw new IllegalArgumentException("Ya existe un usuario con este email");

        usuario.setPassword( codificador.encode(usuario.getPassword()) );
        usuario.setRol(rolRepository.findByNombre("Usuario").orElseThrow(() -> new IllegalArgumentException("Error al crear usuario")));
        usuarioRepository.save(usuario);
    }

}

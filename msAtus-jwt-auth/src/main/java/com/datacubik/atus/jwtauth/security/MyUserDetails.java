package com.datacubik.atus.jwtauth.security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.datacubik.atus.jwtauth.dao.UsuarioDAO;
import com.datacubik.atus.jwtauth.model.entity.Usuario;

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private UsuarioDAO userRepository;

  @Override
  public UserDetails loadUserByUsername(String cveCorreo) throws UsernameNotFoundException {
    final Usuario usuario = userRepository.findByCveCorreoIgnoreCase(cveCorreo);

    if (usuario == null) {
      throw new UsernameNotFoundException("User '" + cveCorreo + "' not found");
    }
    
    List<GrantedAuthority> authorities = usuario.getRoles()
			.stream()
			.map(rol -> new SimpleGrantedAuthority(rol.getNomNombre()))
			.peek(authority -> System.out.println("Role : " + authority.getAuthority()))
			.collect(Collectors.toList());
    
    return org.springframework.security.core.userdetails.User//
        .withUsername(cveCorreo)//
        .password(usuario.getCveContrasena())//
        .authorities(authorities)//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}

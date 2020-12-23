package com.datacubik.atus.jwtauth.service.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.datacubik.atus.jwtauth.dao.UsuarioDAO;
import com.datacubik.atus.jwtauth.exception.CustomException;
import com.datacubik.atus.jwtauth.model.entity.Usuario;
import com.datacubik.atus.jwtauth.security.JwtTokenProvider;

@Service
public class UserService {

  @Autowired
  private UsuarioDAO userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  public String signin(String username, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      Usuario usuario = userRepository.findByCveCorreoIgnoreCase(username);
      return jwtTokenProvider.createToken(username, usuario.getRoles());
    } catch (AuthenticationException e) {
    	e.printStackTrace();
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public Usuario search(String cveCorreo) {
    Usuario usuario = userRepository.findByCveCorreoIgnoreCase(cveCorreo);
    if (usuario == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return usuario;
  }

  public Usuario whoami(HttpServletRequest req) {
	Usuario usuario = userRepository.findByCveCorreoIgnoreCase(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
	if(usuario == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
	return usuario;
  }

  public String refresh(String cveCorreo) {
    return jwtTokenProvider.createToken(cveCorreo, userRepository.findByCveCorreoIgnoreCase(cveCorreo).getRoles());
  }

}

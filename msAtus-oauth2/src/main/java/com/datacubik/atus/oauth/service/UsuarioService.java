package com.datacubik.atus.oauth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.datacubik.atus.oauth.client.UsuarioFeignClient;
import com.datacubik.atus.oauth.model.dao.UsuarioDAO;
import com.datacubik.atus.oauth.model.dto.UsuarioDTO;
import com.datacubik.atus.oauth.model.entity.Rol;
import com.datacubik.atus.oauth.model.entity.Usuario;
import com.datacubik.atus.oauth.model.dto.RolDTO;

@Service
public class UsuarioService implements UserDetailsService {
	
	//@Autowired
	//UsuarioFeignClient client;
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public UserDetails loadUserByUsername(String cveCorreo) throws UsernameNotFoundException {
		UsuarioDTO usuario = this.findByCveCorreoIgnoreCase(cveCorreo);
		
		
		if(usuario == null)
			throw new UsernameNotFoundException("Error : '" +  cveCorreo + "' no existe en el sistema");
		
		//List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		//authorities.add(new SimpleGrantedAuthority(usuario.getRol().getNomNombre()));
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getNomNombre()))
				.peek(authority -> System.out.println("Role : " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario.getCveCorreo(), usuario.getCveContrasena(), usuario.isEnable(), true, true, true, authorities);
	}
	
	private UsuarioDTO findByCveCorreoIgnoreCase(String cveCorreo) {
		Usuario usuario = usuarioDAO.findByCveCorreoIgnoreCase(cveCorreo);
		return usuario==null?null:new UsuarioDTO(usuario);
	}

}

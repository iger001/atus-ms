package com.datacubik.atus.jwtauth.dao; 

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.datacubik.atus.jwtauth.model.entity.Usuario;

@Component
public interface UsuarioDAO extends PagingAndSortingRepository<Usuario, Long> {
	
	public Usuario findByCveCorreoIgnoreCase(String cveCorreo);
	
	boolean existsByCveCorreo(String cveCorreo);

	
}

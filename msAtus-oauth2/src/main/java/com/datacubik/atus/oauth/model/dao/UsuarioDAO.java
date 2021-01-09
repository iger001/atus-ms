package com.datacubik.atus.oauth.model.dao; 

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.datacubik.atus.oauth.model.entity.Usuario;

@Component
public interface UsuarioDAO extends PagingAndSortingRepository<Usuario, Long> {
	
	public Usuario findByCveCorreoIgnoreCase(String cveCorreo);
	
}

package com.datacubik.atus.oauth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.datacubik.atus.oauth.model.dto.UsuarioDTO;

@FeignClient(name = "msAtus-usuarios", url = "localhost:9090/msAtus-usuarios")
public interface UsuarioFeignClient {
	
	@GetMapping("/usuarios/usuarioByCveCorreo/{cveCorreo}")
	public UsuarioDTO getUsuarioByCveCorreo(@PathVariable String cveCorreo);

}

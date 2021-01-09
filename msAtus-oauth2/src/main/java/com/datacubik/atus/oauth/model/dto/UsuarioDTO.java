package com.datacubik.atus.oauth.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.datacubik.atus.oauth.model.entity.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6302936711753898926L;
	
	private Long cveUsuario;
	private String cveCorreo;
	private String cveContrasena;
	private String nomNombre; 
	private String nomApellidoPaterno;
	private String nomApellidoMaterno;
	private List<RolDTO> roles;
	private String desComentario;
	private Date fecExpira;
	private Date fecAlta;
	private Date fecActualizacion;
	private Date fecBaja;
	
	public boolean isEnable() {
		if(fecBaja!=null || fecExpira!=null&&fecExpira.getTime()<new Date().getTime()) return false;
		return true;
	}
	
	public UsuarioDTO(Usuario usuario) {
		super();
		if(usuario==null) return;
		BeanUtils.copyProperties(usuario, this);
		
		List<RolDTO> rolesDTO = usuario.getRoles().stream().map(RolDTO::new).collect(Collectors.toList());
		this.setRoles(rolesDTO);
	}

	public UsuarioDTO() {
		super();
	}
}

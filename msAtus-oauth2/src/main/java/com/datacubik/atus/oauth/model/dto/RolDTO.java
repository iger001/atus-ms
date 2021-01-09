package com.datacubik.atus.oauth.model.dto;

import lombok.Data;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.datacubik.atus.oauth.model.entity.Rol;

@Data
public class RolDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2441612824487269175L;

	private Integer cveRol;
	private String nomNombre;
	
	public RolDTO(Rol rol) {
		super();
		if(rol==null) return;
		BeanUtils.copyProperties(rol, this);
	}

	public RolDTO() {
		super();
	}
	
}

package com.datacubik.atus.oauth.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="\"ATUS_ROL\"")
public class Rol implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1966255991754954836L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CVE_ROL")
	private Integer cveRol;
	
	@Column(name = "NOM_NOMBRE")
	private String nomNombre;
	
	@Column(name = "FEC_EXPIRA")
	private Date fecExpira;
	
	@Column(name = "FEC_ALTA")
	private Date fecAlta;
	
	@PrePersist
	public void prePersist() {
		fecAlta = new Date();
		fecActualizacion = null;
		fecBaja = null;
	}
	
	@Column(name = "FEC_ACTUALIZACION")
	private Date fecActualizacion;
	
	@Column(name = "FEC_BAJA")
	private Date fecBaja;
}

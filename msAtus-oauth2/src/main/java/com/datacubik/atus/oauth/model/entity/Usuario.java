package com.datacubik.atus.oauth.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="\"ATUS_USUARIO\"")
public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1149694625008025061L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CVE_USUARIO")
	private Long cveUsuario;
	
	@Column(name = "CVE_CORREO")
	private String cveCorreo;
	
	@Column(name = "CVE_CONTRASENA")
	private String cveContrasena;
	
	@Column(name = "NOM_NOMBRE")
	private String nomNombre; 
	
	@Column(name = "NOM_APELLIDOPATERNO")
	private String nomApellidoPaterno;

	@Column(name = "NOM_APELLIDOMATERNO")
	private String nomApellidoMaterno;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="ATUS_USUARIO_ROL",
			joinColumns=@JoinColumn(name = "CVE_USUARIO"),
			inverseJoinColumns=@JoinColumn(name = "CVE_ROL"))
	private List<Rol> roles;
	
	@Column(name = "DES_COMENTARIO")
	private String desComentario;
	
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
	
	
	/*
	  * Regresar true cuando a fec_expira seá mayor a la fecha actual y cuando fecha baja sea nulo
	*/
	/*public boolean isEnable() {
		if(fecBaja!=null || fecExpira.getTime()<new Date().getTime()) return false;
		return true;
	}*/
}

package entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity
public class Info implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private int idInfo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateInfo;

	private int idSociete;
	
	@NotNull( message = "Veuillez saisir l'info" )
	@Size( max = 100, message = "L'info ne doit pas dépasser 100 caractères" )
	private String vInfo;

	public Info() {
	}

	public int getIdInfo() {
		return this.idInfo;
	}

	public void setIdInfo(int idInfo) {
		this.idInfo = idInfo;
	}

	public Date getDateInfo() {
		return this.dateInfo;
	}

	public void setDateInfo(Date dateInfo) {
		this.dateInfo = dateInfo;
	}

	public int getIdSociete() {
		return this.idSociete;
	}

	public void setIdSociete(int idSociete) {
		this.idSociete = idSociete;
	}

	public String getVInfo() {
		return this.vInfo;
	}

	public void setVInfo(String vInfo) {
		this.vInfo = vInfo;
	}

}
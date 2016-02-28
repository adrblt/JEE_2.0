package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Contrat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idContrat;

	private int idSociete;
	
	private int idType;

	private String libelle;

	public Contrat() {
	}

	public int getIdContrat() {
		return this.idContrat;
	}

	public void setIdContrat(int idContrat) {
		this.idContrat = idContrat;
	}

	public int getIdSociete() {
		return this.idSociete;
	}

	public void setIdSociete(int idSociete) {
		this.idSociete = idSociete;
	}
	
	public int getIdType() {
		return this.idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
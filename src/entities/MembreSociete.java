package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class MembreSociete implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idMS;

	private int idSociete;

	private int idUtilisateur;

	public MembreSociete() {
	}

	public int getIdMS() {
		return this.idMS;
	}

	public void setIdMS(int idMS) {
		this.idMS = idMS;
	}

	public int getIdSociete() {
		return this.idSociete;
	}

	public void setIdSociete(int idSociete) {
		this.idSociete = idSociete;
	}

	public int getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

}
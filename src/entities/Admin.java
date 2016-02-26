package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idAdmin;

	private int idUtilisateur;

	public Admin() {
	}

	public int getIdAdmin() {
		return this.idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public int getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

}
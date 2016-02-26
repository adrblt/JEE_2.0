package entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idRole;

	private String vRole;

	public Role() {
	}

	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getVRole() {
		return this.vRole;
	}

	public void setVRole(String vRole) {
		this.vRole = vRole;
	}

}
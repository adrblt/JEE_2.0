package entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Secteur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idSecteur;

	private String vSecteur;

	public Secteur() {
	}

	public int getIdSecteur() {
		return this.idSecteur;
	}

	public void setIdSecteur(int idSecteur) {
		this.idSecteur = idSecteur;
	}

	public String getVSecteur() {
		return this.vSecteur;
	}

	public void setVSecteur(String vSecteur) {
		this.vSecteur = vSecteur;
	}

}
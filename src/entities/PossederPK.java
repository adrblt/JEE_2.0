package entities;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class PossederPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idInvestisseur;

	private int idContrat;

	public PossederPK() {
	}
	public int getIdInvestisseur() {
		return this.idInvestisseur;
	}
	public void setIdInvestisseur(int idInvestisseur) {
		this.idInvestisseur = idInvestisseur;
	}
	public int getIdContrat() {
		return this.idContrat;
	}
	public void setIdContrat(int idContrat) {
		this.idContrat = idContrat;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PossederPK)) {
			return false;
		}
		PossederPK castOther = (PossederPK)other;
		return 
			(this.idInvestisseur == castOther.idInvestisseur)
			&& (this.idContrat == castOther.idContrat);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idInvestisseur;
		hash = hash * prime + this.idContrat;
		
		return hash;
	}
}
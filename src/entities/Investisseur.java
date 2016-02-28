package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Investisseur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int idInvestisseur;

	private int idUtilisateur;
	
	private float vCompte;

	public Investisseur() {
	}

	public int getIdInvestisseur() {
		return this.idInvestisseur;
	}

	public void setIdInvestisseur(int idInvestisseur) {
		this.idInvestisseur = idInvestisseur;
	}

	public int getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public float getvCompte() {
		return vCompte;
	}

	public void setvCompte(float vCompte) {
		this.vCompte = vCompte;
	}
}
package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Ventes")
public class Vente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int idVente;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDepart;

	private int idContrat;

	private int idInvestisseur;

	private float prixDepart;

	public Vente() {
	}

	public int getIdVente() {
		return this.idVente;
	}

	public void setIdVente(int idVente) {
		this.idVente = idVente;
	}

	public Date getDateDepart() {
		return this.dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	public int getIdContrat() {
		return this.idContrat;
	}

	public void setIdContrat(int idContrat) {
		this.idContrat = idContrat;
	}

	public int getIdInvestisseur() {
		return this.idInvestisseur;
	}

	public void setIdInvestisseur(int idInvestisseur) {
		this.idInvestisseur = idInvestisseur;
	}

	public float getPrixDepart() {
		return this.prixDepart;
	}

	public void setPrixDepart(float prixDepart) {
		this.prixDepart = prixDepart;
	}

}
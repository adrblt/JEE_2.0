package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Possessions database table.
 * 
 */
@Entity
@Table(name="Possessions")
public class Possession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int idPossession;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAchat;

	private int idContrat;

	private int idInvestisseur;

	private float prixAchat;

	public Possession() {
	}

	public int getIdPossession() {
		return this.idPossession;
	}

	public void setIdPossession(int idPossession) {
		this.idPossession = idPossession;
	}

	public Date getDateAchat() {
		return this.dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
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

	public float getPrixAchat() {
		return this.prixAchat;
	}

	public void setPrixAchat(float prixAchat) {
		this.prixAchat = prixAchat;
	}

}
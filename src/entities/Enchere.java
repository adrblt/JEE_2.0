package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Encheres")
public class Enchere implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int idEnchere;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFinal;

	private int idContrat;

	private int idPossesseur;
	
	private int nbEncheres;

	private float prixFinal;

	public Enchere() {
	}

	public int getIdEnchere() {
		return this.idEnchere;
	}

	public void setIdEnchere(int idEnchere) {
		this.idEnchere = idEnchere;
	}

	public Date getDateFinal() {
		return this.dateFinal;
	}

	public void setDateFinal(Date dateFinal) {
		this.dateFinal = dateFinal;
	}

	public int getIdContrat() {
		return this.idContrat;
	}

	public void setIdContrat(int idContrat) {
		this.idContrat = idContrat;
	}

	public int getIdPossesseur() {
		return this.idPossesseur;
	}

	public void setIdPossesseur(int idPossesseur) {
		this.idPossesseur = idPossesseur;
	}

	public float getPrixFinal() {
		return this.prixFinal;
	}

	public void setPrixFinal(float prixFinal) {
		this.prixFinal = prixFinal;
	}

	public int getNbEncheres() {
		return nbEncheres;
	}

	public void setNbEncheres(int nbEncheres) {
		this.nbEncheres = nbEncheres;
	}
}
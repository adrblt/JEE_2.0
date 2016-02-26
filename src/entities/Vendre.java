package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Vendre implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VendrePK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDebut;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFin;

	private byte isEnchere;

	private float prixVente;

	public Vendre() {
	}

	public VendrePK getId() {
		return this.id;
	}

	public void setId(VendrePK id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public byte getIsEnchere() {
		return this.isEnchere;
	}

	public void setIsEnchere(byte isEnchere) {
		this.isEnchere = isEnchere;
	}

	public float getPrixVente() {
		return this.prixVente;
	}

	public void setPrixVente(float prixVente) {
		this.prixVente = prixVente;
	}

}
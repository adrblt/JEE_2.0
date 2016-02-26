package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


@Entity
public class Posseder implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PossederPK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAchat;

	private float prixAchat;

	public Posseder() {
	}

	public PossederPK getId() {
		return this.id;
	}

	public void setId(PossederPK id) {
		this.id = id;
	}

	public Date getDateAchat() {
		return this.dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public float getPrixAchat() {
		return this.prixAchat;
	}

	public void setPrixAchat(float prixAchat) {
		this.prixAchat = prixAchat;
	}

}
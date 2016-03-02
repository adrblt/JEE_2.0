package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Logs")
public class Log implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int idLog;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLog;

	private int idContrat;

	private int idInvestisseur;

	private float prixLog;

	private byte vLog;

	public Log() {
	}

	public int getIdLog() {
		return this.idLog;
	}

	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}

	public Date getDateLog() {
		return this.dateLog;
	}

	public void setDateLog(Date dateLog) {
		this.dateLog = dateLog;
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

	public float getPrixLog() {
		return this.prixLog;
	}

	public void setPrixLog(float prixLog) {
		this.prixLog = prixLog;
	}

	public byte getVLog() {
		return this.vLog;
	}

	public void setVLog(byte vLog) {
		this.vLog = vLog;
	}

}
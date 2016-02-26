package entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idType;

	private String vType;

	public Type() {
	}

	public int getIdType() {
		return this.idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public String getVType() {
		return this.vType;
	}

	public void setVType(String vType) {
		this.vType = vType;
	}

}
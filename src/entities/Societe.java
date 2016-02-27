package entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Societe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int idSociete;
	
	@NotNull( message = "Veuillez saisir une description" )
	@Size( max = 200, message = "La description ne doit pas dépasser 200 caractères" )
	private String description;

	@NotNull( message = "Veuillez choisir un secteur" )
	private int idSecteur;

	@NotNull( message = "Veuillez saisir un nom" )
	@Size( min = 3, message = "Le nom doit contenir au moins 3 caractères" )
	private String nom;

	public Societe() {
	}

	public int getIdSociete() {
		return this.idSociete;
	}

	public void setIdSociete(int idSociete) {
		this.idSociete = idSociete;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdSecteur() {
		return this.idSecteur;
	}

	public void setIdSecteur(int idSecteur) {
		this.idSecteur = idSecteur;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
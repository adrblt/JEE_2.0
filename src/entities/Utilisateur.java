package entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Date;


@Entity
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private int idUtilisateur;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateInscription;
	
	@NotNull( message = "Please enter an email address" )
	@Pattern( regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "Please enter a valid email" )
	private String email;

	@NotNull( message = "Please select a role" )
	private int idRole;

	@NotNull( message = "Please enter a last name" )
	@Size( min = 3, message = "The last name must contains at least 3 characters" )
	private String nom;
	
	@NotNull( message = "Please enter a first name" )
	@Size( min = 3, message = "The first name must contains at least 3 characters" )
	private String prenom;
	
	@NotNull( message = "Please enter a password" )
	@Size( min = 3, message = "The password must contains at least 3 characters" )
	private String mdp;
	//@Pattern(regexp = ".*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*", message = "Le mot de passe saisi n'est pas assez sécurisé")

	public Utilisateur() {
	}

	public int getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Date getDateInscription() {
		return this.dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMdp() {
		return this.mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

}
package com.it.uniroma3.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;

	
	@Column(nullable=false, length=100)
	private String nome;
	
	@Column(nullable=false, length=100)
	private String cognome;
	
	@Column(updatable=false, nullable=false)
	private LocalDateTime creationTime;
	
	
	
	@OneToMany( cascade = CascadeType.REMOVE, mappedBy= "owner")
	private List<Project> ownedProjects;
	
	@ManyToMany(mappedBy = "members")
	private List<Project> visibleProjects;
	
	public User() {
		this.ownedProjects= new ArrayList<>();
		this.visibleProjects= new ArrayList<>();
	}

	
	public User(String nome,String cognome) {
		this();
		this.nome=nome;
		this.cognome=cognome;
		
	}
	
	@PrePersist
	protected void onPersist() {
		this.creationTime=LocalDateTime.now();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public LocalDateTime getCreationTime() {
		return creationTime;
	}


	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}


	public List<Project> getOwnedProjects() {
		return ownedProjects;
	}


	public void setOwnedProjects(List<Project> ownedProjects) {
		this.ownedProjects = ownedProjects;
	}


	public List<Project> getVisibleProjects() {
		return visibleProjects;
	}


	public void setVisibleProjects(List<Project> visibleProjects) {
		this.visibleProjects = visibleProjects;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", creationTime=" + creationTime
				+ ", ownedProjects=" + ownedProjects + ", visibleProjects=" + visibleProjects + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((ownedProjects == null) ? 0 : ownedProjects.hashCode());
		result = prime * result + ((visibleProjects == null) ? 0 : visibleProjects.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (ownedProjects == null) {
			if (other.ownedProjects != null)
				return false;
		} else if (!ownedProjects.equals(other.ownedProjects))
			return false;
		if (visibleProjects == null) {
			if (other.visibleProjects != null)
				return false;
		} else if (!visibleProjects.equals(other.visibleProjects))
			return false;
		return true;
	}
	
	
	
}

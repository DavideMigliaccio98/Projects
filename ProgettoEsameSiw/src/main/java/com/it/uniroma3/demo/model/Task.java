package com.it.uniroma3.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="tasks")
public class Task {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	
	@Column(nullable=false, length=100)
	private String nome;
	
	@Column(nullable=false, length=100)
	private String descrizione;
	
	@Column(nullable=false)
	private boolean completed;
	
	@Column(updatable=false, nullable=false)
	private LocalDateTime creationTime;
	
	@ManyToOne
	private Project ownedProject;
	
	@OneToOne
	private User associatedUser;
	
	public Task() {
		
	}
	
	@PrePersist
	protected void onPersist() {
		this.creationTime=LocalDateTime.now();
	}
	
	public Task (String nome, String descrizione, Project ownedProject, User associatedUser, boolean completed) {
		this.nome = nome;
		this.descrizione=descrizione;
		this.ownedProject=ownedProject;
		this.associatedUser=associatedUser;
		this.completed= completed;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public Project getOwnedProject() {
		return ownedProject;
	}

	public void setOwnedProject(Project ownedProject) {
		this.ownedProject = ownedProject;
	}

	public User getAssociatedUser() {
		return associatedUser;
	}

	public void setAssociatedUser(User associatedUser) {
		this.associatedUser = associatedUser;
	}
	

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associatedUser == null) ? 0 : associatedUser.hashCode());
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((ownedProject == null) ? 0 : ownedProject.hashCode());
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
		Task other = (Task) obj;
		if (associatedUser == null) {
			if (other.associatedUser != null)
				return false;
		} else if (!associatedUser.equals(other.associatedUser))
			return false;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
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
		if (ownedProject == null) {
			if (other.ownedProject != null)
				return false;
		} else if (!ownedProject.equals(other.ownedProject))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", completed=" + completed
				+ ", creationTime=" + creationTime + ", ownedProject=" + ownedProject + ", associatedUser="
				+ associatedUser + "]";
	}
	
	
}

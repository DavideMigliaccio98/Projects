package com.it.uniroma3.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="projects")
public class Project {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, length=100)
	private String nome;
	
	@Column(nullable = false, length = 100)
	private String descrizione;

	@Column(updatable=false, nullable=false)
	private LocalDateTime dataInizio;
	
	@ManyToOne(fetch= FetchType.EAGER)
	private User owner;
	
	@ManyToMany
	private List<User> members;
	
	@OneToMany(fetch= FetchType.EAGER, cascade= CascadeType.ALL, mappedBy = "ownedProject")
	private List<Task> tasks;
	
	public Project () {
		this.members = new ArrayList<>();
        this.tasks = new ArrayList<>(); 
	}
	
	public Project (String nome, User owner, String descrizione) {
		this();
		this.nome=nome;
		this.owner=owner;
		this.descrizione = descrizione;
		
		
	}
	@PrePersist
	protected void onPersist() {
		this.dataInizio=LocalDateTime.now();
	}
	
	
	public void addTask(Task task) {
		this.tasks.add(task);
	
		
	}

	
	public void setDescrizione(String descrizione) {
		this.descrizione=descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}
	
	public void addMember(User member) {
		this.members.add(member);
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

	public LocalDateTime getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataInizio == null) ? 0 : dataInizio.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((members == null) ? 0 : members.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
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
		Project other = (Project) obj;
		if (dataInizio == null) {
			if (other.dataInizio != null)
				return false;
		} else if (!dataInizio.equals(other.dataInizio))
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
		if (members == null) {
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (tasks == null) {
			if (other.tasks != null)
				return false;
		} else if (!tasks.equals(other.tasks))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", dataInizio=" + dataInizio
				+ ", owner=" + owner + ", members=" + members + ", tasks=" + tasks + "]";
	} 
	
	

	
}

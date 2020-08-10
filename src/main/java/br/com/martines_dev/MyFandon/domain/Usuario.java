package br.com.martines_dev.MyFandon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	@Column(unique=true)
	private String username;
	
	@JsonIgnore
	@NotNull
	private String password;
	
	public Usuario() {
		
	}

	
	
	public Usuario(Long id, @NotNull String name, @NotNull String username, @NotNull String password) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
		

}

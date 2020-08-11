package br.com.martines_dev.MyFandon.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comentario implements Serializable  {


	private static final long serialVersionUID = 227920418182640486L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column( nullable=false)
	private String mensagem;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;
	
	
	
	@JoinColumn( name="personagem_id" , nullable = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Personagem personagem;
	

	
	@JoinColumn( name="anime_id" , nullable = true )
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Anime anime;

	



	public Comentario(Long id, Usuario usuario) {
		super();
		this.id = id;
		this.usuario = usuario;
	}
	
	
	public Comentario() {
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Personagem getPersonagem() {
		return personagem;
	}


	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}


	public Anime getAnime() {
		return anime;
	}


	public void setAnime(Anime anime) {
		this.anime = anime;
	}


	

}

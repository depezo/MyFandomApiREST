package br.com.martines_dev.MyFandon.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Personagem   {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	@Column(unique = true ,nullable = false)
	private String nome;
	private String idade;
	private String descricao;
	private String historia;
	private String ator;
	
	public Personagem() {
		
	}
	
	
	public Personagem(Long id, String nome, String idade, String descricao, String historia, String ator,
			List<Comentario> comentarios) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.descricao = descricao;
		this.historia = historia;
		this.ator = ator;
		this.comentarios = comentarios;
	}


	@ManyToOne(targetEntity = Comentario.class , fetch = FetchType.EAGER)
	private List<Comentario> comentarios;
	
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
	public String getIdade() {
		return idade;
	}
	public void setIdade(String idade) {
		this.idade = idade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getHistoria() {
		return historia;
	}
	public void setHistoria(String historia) {
		this.historia = historia;
	}
	public String getAtor() {
		return ator;
	}
	public void setAtor(String ator) {
		this.ator = ator;
	}
	 
	public List<Comentario> getComentarios() {

		return comentarios;
	}
	 
	public void setComentarios(List<Comentario> comentarios) {
		
		this.comentarios = comentarios;
	}

	
}

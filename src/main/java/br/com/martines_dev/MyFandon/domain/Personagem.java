package br.com.martines_dev.MyFandon.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Personagem implements Commentable {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	@Column(unique = true ,nullable = false)
	private String nome;
	private String idade;
	private String descricao;
	private String historia;
	private String ator;
	
	
	@ManyToOne(targetEntity = Comentario.class)
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
	@Override
	public List<Comentario> getCommentario() {

		return comentarios;
	}
	@Override
	public void setComentario(List<Comentario> comentarios) {
		
		this.comentarios = comentarios;
	}

	
}

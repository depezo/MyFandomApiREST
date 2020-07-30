package br.com.martines_dev.MyFandon.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Anime implements Commentable {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(nullable = false , unique = true )
	private String nome;
	@Column(nullable = false )
	private String resumo;
	@Column(nullable = false )
	private String descricao;
	@Column(nullable = false )
	private String autorDaObra;
		
	@ManyToMany( targetEntity = Usuario.class )
	private List<Usuario> admin;
	@ManyToOne ( targetEntity = Comentario.class )
	private List<Comentario> comentarios;	// por enquanto é nullable	
	@ManyToOne ( targetEntity = Personagem.class )
	private List<Personagem> personagems;
		
	
	
	public List<Usuario> getAdmin() {
		return admin;
	}
	public void setAdmin(List<Usuario> admin) {
		this.admin = admin;
	}
		
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getResumo() {
		return resumo;
	}
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getAutorDaObra() {
		return autorDaObra;
	}
	public void setAutorDaObra(String autorDaObra) {
		this.autorDaObra = autorDaObra;
	}
	public List<Comentario> getCommentario() {
		return comentarios;
	}
	public void setComentario(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	public List<Personagem> getPersonagems() {
		return personagems;
	}
	public void setPersonagems(List<Personagem> personagems) {
		this.personagems = personagems;
	}	
}

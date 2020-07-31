package br.com.martines_dev.MyFandon.domain;

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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@Entity
@JsonAutoDetect
public class Anime {//implements Commentable 

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
		
	
	@ManyToMany( 
		targetEntity = Usuario.class )
	@Column( nullable = false)
	private List<Usuario> admin;
	
	@ManyToMany( targetEntity = Categoria.class  )
	private List<Categoria> categorias;
	
	
	@OneToMany ( 
		targetEntity = Comentario.class,
		fetch = FetchType.LAZY )
	private List<Comentario> comentarios;	// por enquanto é nullable
	
	@OneToMany ( 
		targetEntity = Personagem.class ,
		fetch = FetchType.LAZY , 
		cascade = CascadeType.ALL  )
	private List<Personagem> personagems;
	
	
	public Anime() {
		
	}
	
	
	public Anime(Long id, String nome, String resumo, String descricao, String autorDaObra, List<Usuario> admin,
			List<Comentario> comentarios, List<Personagem> personagems) {
		super(); 
		this.id = id;
		this.nome = nome;
		this.resumo = resumo;
		this.descricao = descricao;
		this.autorDaObra = autorDaObra;
		this.admin = admin;
		this.comentarios = comentarios;
		this.personagems = personagems;
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
	public List<Usuario> getAdmin() {
		return admin;
	}
	public void setAdmin(List<Usuario> admin) {
		this.admin = admin;
	}
	public List<Personagem> getPersonagems()
	{
		if( personagems == null) 
		{ 
			return new ArrayList<>();
		}
		return personagems;
	}
	public void setPersonagems(List<Personagem> personagems) {
		this.personagems = personagems;
	}
	
	
	
	
	
	public List<Comentario> getComentarios() {
		if( comentarios == null) 
		{ 
			return new ArrayList<>();
		}
		return comentarios;
	}
	
	public void setComentarios(List<Comentario> comentario) {
		this.comentarios = comentario;
	}
	
	
	public List<Categoria> getCategorias() {
		return categorias;
	}


	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	
	@Override
	public String toString() {
		return "Anime [id=" + id + ", nome=" + nome + ", resumo=" + resumo + ", descricao=" + descricao
				+ ", autorDaObra=" + autorDaObra + ", admin=" + admin + ", comentarios=" + comentarios
				+ ", personagems=" + personagems + "]";
	}
	
	
	

/*******************************
 * JUST SOME STUFF
 */
	

	
	
/*******************************
 * RELATIONAL
 *******************************/
//	public List<Usuario> getAdmin() {
//		return admin;
//	}
//	public void setAdmin(List<Usuario> admin) {
//		this.admin = admin;
//	}
//	public List<Personagem> getPersonagems() {
//		return personagems;
//	}
//	public void setPersonagems(List<Personagem> personagems) {
//		this.personagems = personagems;
//	}	
	
//	@Override
//	public List<Comentario> getComentarios() {
//		return comentarios;
//	}
//	@Override
//	public void setComentarios(List<Comentario> comentarios) {
//		this.comentarios = comentarios;
//	}	
}

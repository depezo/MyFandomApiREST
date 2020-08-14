package br.com.martines_dev.MyFandon.domain;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Anime implements Serializable{//implements Commentable 


	private static final long serialVersionUID = 2080314245740381830L;

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
	
	private String genero;		
	
	
	@ManyToMany( targetEntity = Usuario.class ,fetch = FetchType.LAZY )
	@Column( nullable = false)
	@NotNull
	private List<Usuario> admin;
		
	
	@ManyToMany( targetEntity = Categoria.class ,fetch = FetchType.LAZY )
	private List<Categoria> categorias;
	
	
	@OneToMany ( targetEntity = Comentario.class  , 
						cascade = CascadeType.REMOVE, 
						mappedBy = "anime" , 
						orphanRemoval = true,
						fetch = FetchType.EAGER) 
	private List<Comentario> comentarios;	
    
	
	@OneToMany ( targetEntity = Personagem.class, 
						cascade = CascadeType.REMOVE, 
						mappedBy = "anime"  ,
						orphanRemoval = true,
						fetch = FetchType.LAZY)
	@JsonBackReference 
	private List<Personagem> personagems;
	
	
	public Anime() {
		
	}
	
	
	public Anime(Long id, String nome, String resumo, String descricao, String autorDaObra, List<Usuario> admin) {
		super(); 
		this.id = id;
		this.nome = nome;
		this.resumo = resumo;
		this.descricao = descricao;
		this.autorDaObra = autorDaObra;
		this.admin = admin;
	}

	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
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
	
	
}

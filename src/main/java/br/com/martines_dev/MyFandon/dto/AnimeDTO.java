package br.com.martines_dev.MyFandon.dto;

import java.util.List;

import br.com.martines_dev.MyFandon.domain.Categoria;
import br.com.martines_dev.MyFandon.domain.Comentario;
import br.com.martines_dev.MyFandon.domain.Personagem;
import br.com.martines_dev.MyFandon.domain.Usuario;

public class AnimeDTO {


	private Long id;
	
	private String nome;
	private String resumo;
	private String descricao;
	private String autorDaObra;
	private String genero;		

	private List<Usuario>    admin;
	private List<Categoria>  categorias;
	private List<Comentario> comentarios;	
	private List<Personagem> personagems;
	
	
	public AnimeDTO() {}
	
	
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


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public List<Usuario> getAdmin() {
		return admin;
	}


	public void setAdmin(List<Usuario> admin) {
		this.admin = admin;
	}


	public List<Categoria> getCategorias() {
		return categorias;
	}


	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}


	public List<Comentario> getComentarios() {
		return comentarios;
	}


	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}


	public List<Personagem> getPersonagems() {
		return personagems;
	}


	public void setPersonagems(List<Personagem> personagems) {
		this.personagems = personagems;
	}

}

package br.com.martines_dev.MyFandon;

import javax.validation.constraints.NotNull;

public class UsuarioDTO {

	private Long id;
	
	@NotNull
	private String nomeCompleto;
	
	@NotNull
	private String nomeUsuario;
	
	@NotNull
	private String senha;

	public UsuarioDTO(@NotNull String nomeCompleto, @NotNull String nomeUsuario, @NotNull String senha) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
	}

	public UsuarioDTO(Long id, @NotNull String nomeCompleto, @NotNull String nomeUsuario, @NotNull String senha) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
	}

	public UsuarioDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

		
}

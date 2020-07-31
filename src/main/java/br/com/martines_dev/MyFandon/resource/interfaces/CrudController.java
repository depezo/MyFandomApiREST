package br.com.martines_dev.MyFandon.resource.interfaces;

import java.util.List;

public interface CrudController<C,T> {

	
	public C inserir(C categoria) ;
	public C atualizar(C categoria, T id) ;
	public C pegarUm(T id);
	public void deletar(T id);
	public List<C> listar();
}

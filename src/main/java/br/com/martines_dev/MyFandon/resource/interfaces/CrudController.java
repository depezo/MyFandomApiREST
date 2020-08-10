package br.com.martines_dev.MyFandon.resource.interfaces;

import java.util.List;

public abstract class CrudController<C,T> {

	public C testNotForce( C x ) {
		return x;
	}
	
	
	public abstract C atualizar(C categoria, T id) ;
	public abstract C pegarUm(T id);
	public abstract void deletar(T id);
	public abstract List<C> listar();
}

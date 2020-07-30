package br.com.martines_dev.MyFandon.domain;

import java.util.List;

public interface Commentable {

	List<Comentario> getCommentario();
	void setComentario( List<Comentario> comentario);
}

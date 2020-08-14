package br.com.martines_dev.MyFandon.util;

import com.google.common.base.Optional;

import br.com.martines_dev.MyFandon.exceptions.IdNaoConfereException;

public class LongIdUtil {
	
	
	public static void validateId(Long idOfObject , Long idOfParam ) {
		if( Optional.fromNullable( idOfObject).isPresent() ) {
			if( !idOfObject.equals( idOfParam) ) {
				throw new IdNaoConfereException();
			}
		}
	}
	
	
	
}

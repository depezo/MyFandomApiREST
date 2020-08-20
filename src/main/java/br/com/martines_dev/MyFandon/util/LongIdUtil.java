package br.com.martines_dev.MyFandon.util;


import br.com.martines_dev.MyFandon.exceptions.IdNaoConfereException;
import java.util.Optional;

public class LongIdUtil {
	
	
	public static void validateId(Long idOfObject , Long idOfParam ) {
		if( Optional.ofNullable(idOfObject).isPresent() ) {
			if( !idOfObject.equals( idOfParam) ) {
				throw new IdNaoConfereException();
			}
		}
	}
	
	
	
}

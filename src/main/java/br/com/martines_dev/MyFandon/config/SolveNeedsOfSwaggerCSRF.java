package br.com.martines_dev.MyFandon.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SolveNeedsOfSwaggerCSRF 
{
	@Controller
	public class CSRFController {
	    @RequestMapping(value = "/csrf", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	    public ResponseEntity<CsrfToken> getToken(final HttpServletRequest request) {
	        return ResponseEntity.ok().body(new HttpSessionCsrfTokenRepository().generateToken(request));
	    }
	}
}

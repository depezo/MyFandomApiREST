package br.com.martines_dev.MyFandon.resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

	@GetMapping("/")
	RedirectView redirectToSwagger( HttpServletRequest request ) {
		
		
		return new RedirectView( request.getContextPath() + "/swagger-ui.html");
	}
}

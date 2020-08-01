package br.com.martines_dev.MyFandon.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

	@GetMapping("/")
	RedirectView redirectToSwagger() {
		return new RedirectView("/swagger-ui.html");
	}
}

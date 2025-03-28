package tuto.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("connected")
public class WebConnexion {
	@GetMapping("/bonjour")
	public String bonjour() {
		return "bonjour";
	}
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	@PostMapping("/connect")
	public String connect(String username, String password, Model model) {
		var sb=new StringBuilder(username);
		if(sb.reverse().toString().equals( password )) {
			model.addAttribute( "connected",username );
			return "redirect:/bonjour";
		}else {
			model.addAttribute( "alert", "Mot de passe incorrect" );
			model.addAttribute( "connected",username );
			model.addAttribute( "password",password );
			return "index";
		}
	}
	@PostMapping("/disconnect")
	public String disconnect( SessionStatus status, Model model, RedirectAttributes ra) {
		status.setComplete();
		ra.addFlashAttribute( "alert","Déconnexion effectuée avec succès" );
		model.addAttribute( "connected",null );
		return "redirect:/index";
	}
}

package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import vttp2023.batch3.ssf.frontcontroller.model.Captcha;
import vttp2023.batch3.ssf.frontcontroller.model.Login;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@Controller
@RequestMapping(path = {"/"})
public class FrontController {
	@Autowired
	public AuthenticationService aSvc;

	@GetMapping
	public String getLoginPage(Model m) {
		
		
		m.addAttribute("login", new Login());

		return "view0";
	}

	@PostMapping(path = "/login")
	public String getLoginPage(@ModelAttribute("login") @Valid Login login, BindingResult bind, HttpSession s, Model m)
			throws Exception {

		if (bind.hasErrors()) {
			// Captcha captcha = (Captcha) s.getAttribute("captcha");
			// if (captcha != null) {
			// 	captcha = new Captcha();
			// 	m.addAttribute("captcha", captcha);
			// 	s.setAttribute("captcha", captcha);
			// }
			return "view0";
		}

		if (aSvc.isLocked(login.getUsername())) {
			m.addAttribute("username", login.getUsername());
			return "view2";
		}

		// Captcha captcha = (Captcha) s.getAttribute("captcha");
		// if (captcha != null) {
		// 	//if user get the captcha wrong
		// 	if (!isCorrectCaptcha(login.getCaptchaAnswer(), captcha)) {

		// 		bind.addError(new FieldError("login", "captchaAnswer", "captcha has failed"));

		// 		if (loginAttemptExceeded(s, login)) {
		// 			aSvc.disableUser(login.getUsername());
		// 			removeFromSession(s, login.getUsername());
		// 			return "view2";
		// 		}

		// 		Captcha aCaptcha= new Captcha();
		// 		m.addAttribute("captcha", aCaptcha);
		// 		s.setAttribute("captcha", aCaptcha);

		// 		return "view0";
		// 	}
		//}
		
		//try{
		aSvc.authenticate(login.getUsername(), login.getPassword());
	// }catch(Exception e){
		
	// 		//check if the username exceed
	// 	if (loginAttemptExceeded(s, login)) {
	// 		aSvc.disableUser(login.getUsername());
	// 		removeFromSession(s, login.getUsername());
	// 		return "view2";
	// 	}


	// 	return "view0";
	// }
		
		
	

		return "view1";

	}

	@GetMapping("/logout")
	public String logout(HttpSession s){
		s.invalidate();

		return "redirect:/";
	}



	private boolean isCorrectCaptcha(int captchaAnswer, Captcha captcha) {
		if (captchaAnswer == captcha.getAnswer()) {
			return true;
		}
		return false;
	}

	private void removeFromSession(HttpSession s, String username) {
		boolean isLockednow = aSvc.isLocked(username);
		if (isLockednow == false) {
			s.removeAttribute(username);
		}

	}

	private Integer loginAttempts(HttpSession s, String username) {
		Integer attempts = (Integer) s.getAttribute(username);
		if (attempts == null) {
			s.setAttribute(username, 1);
		} else {
			s.setAttribute(username, attempts + 1);
		}

		return (Integer) s.getAttribute(username);

	}

	private boolean loginAttemptExceeded(HttpSession s, Login login) {
		return loginAttempts(s, login.getUsername()) > 3;
	}
	// TODO: Task 2, Task 3, Task 4, Task 6

}

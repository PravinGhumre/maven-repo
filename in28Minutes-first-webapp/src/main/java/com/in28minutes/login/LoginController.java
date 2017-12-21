package com.in28minutes.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@Autowired
	LoginService service;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
		System.out.println(" showLoginPage ");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleLoginRequest(@RequestParam String name,
			@RequestParam String password, ModelMap model) {
		System.out.println(" handleLoginRequest ");

		System.out.println(" Messages LastName =>>"
				+ this.messageSource.getMessage("employee.myLastName", null,
						"Default Ghumre", null));
		if (!service.validateUser(name, password)) {
			model.addAttribute("errorMessage", "Invalid Credentials !!");
			return "login";
		}
		model.addAttribute("name", name);
		model.addAttribute("password", password);
		return "welcome";
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}

package com.baina.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baina.dto.UserMailRequest;
import com.baina.dto.UserRegistrationDto;
import com.baina.service.UserRegMailService;
import com.baina.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	private UserService userService;
	
	private UserRegMailService userRegMailService;
	
	@Value("{spring.mail.username}")
	private String fromMail;
	
	public UserRegistrationController(UserService userService,UserRegMailService userRegMailService){
		super();
		this.userService=userService;
		this.userRegMailService=userRegMailService;
	}
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
    	model.addAttribute("user",userRegistrationDto());
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.save(registrationDto);
        Map<String,Object> model=new HashMap<>();
        UserMailRequest mail=new UserMailRequest();
        mail.setName(registrationDto.getFirstName());
        mail.setTo(registrationDto.getEmail());
        mail.setSubject("UserRegistration for security.....");
        mail.setFrom(fromMail);
        model.put("name",registrationDto.getFirstName());
        model.put("message","successfully registered user deatails in my server with username :"+registrationDto.getEmail());
        userRegMailService.sendMail(mail, model);
        return "redirect:/registration?success";
    }

}

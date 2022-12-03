package com.DNA.Project;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controller2 
{
	@GetMapping("/")
	public String home()
	{
		return "greetings";
	}
}

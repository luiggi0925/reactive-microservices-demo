package pe.edu.ltmj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@GetMapping({"", "/"})
	public String sayHello(@RequestParam(required=false) String name) {
		return (name == null || name.trim().isEmpty()) ? "Hola mundo!" : "Hola " + name; 
	}
}

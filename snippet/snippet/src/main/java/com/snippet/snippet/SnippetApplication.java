package com.snippet.snippet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class SnippetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnippetApplication.class, args);
	}
	@GetMapping("/")
	public String apiRoot(){
		return "This is the first page snippets";
	}

}

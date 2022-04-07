package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//sep
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@RestController
public class HelloController {

	@GetMapping("/test/")
	public String index() {
		return "Greetings from Spring Boot! testing changes";
	}

	@GetMapping("/books/")
	public String book() {
		return "Welcome to the main page! Working on frontend & input :)";
	}

	@GetMapping("/getbooks/")
	public String getBooks() {
		return "All the books you want!";
	}

	@GetMapping("/getbook/")
	public String getBook() {
		return "The book you want!";
	}

	@GetMapping("/createbook/")
	public String createBook() {
		return "Create new book";
	}
// 	@GetMapping("/greeting")
//   public String greetingForm(Model model) {
//     model.addAttribute("greeting", new Book());
//     return "greeting";
  


	// @GetMapping("/addBook/")
	// public String addBook(@ModelAttribute("SpringWeb")Book theBook, 
	//    ModelMap model) {
	//    model.addAttribute("title", theBook.getTitle());
	//    model.addAttribute("author", theBook.getAuthor();
	//    model.addAttribute("ISBN", theBook.getISBN());
	   
	//    return "result";
	// }
// 	@RequestMapping("/student/")
//    	public Test student() {
// 		return "Test";
//       //return new Book("title1", 12345, "Hesse");
//    }

}

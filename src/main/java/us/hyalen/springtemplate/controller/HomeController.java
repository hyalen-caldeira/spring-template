package us.hyalen.springtemplate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("welcomeMessage", "I'm definitely here ...");
        model.addAttribute("greetings", List.of("Hyalen", "Moreira", "Caldeira"));

        // Name of the template. In this case home.html
        return "home";
    }
}

package us.hyalen.springtemplate.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    private boolean firstVisited = true;

    @RequestMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("welcomeMessage", "I'm definitely here ...");
        model.addAttribute("greetings", List.of("Hyalen", "Moreira", "Caldeira"));

        // Name of the template. In this case home.html
        return "home";
    }

    @PostMapping("/another-home")
    public String setFirstVisited(Model model) {
        model.addAttribute("firstVisited", firstVisited);

        firstVisited = false;
        // Name of the template. In this case home.html
        return "home";
    }
}

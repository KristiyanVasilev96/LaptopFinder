package project.laptop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")

public class BrandController {
    @GetMapping("/all")
    public String all(){
        return "brands";
    }
}

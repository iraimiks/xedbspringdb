package lv.backendherv1.backendherv1.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleTestContr {
    @RequestMapping("/help")
    public String first(){
        return "String nice try";
    }
}

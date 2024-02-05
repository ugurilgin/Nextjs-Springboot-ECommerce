package com.nextecommerce.commerce.apis;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/products")
    public String products() {
        return "index";
    }

    @RequestMapping(value = "/products/{id}")
    public String productsId(@PathVariable String id) {
        return "index";
    }

    @RequestMapping(value = "/about")
    public String about() {
        return "index";
    }

    @RequestMapping(value = "/contact")
    public String contact() {
        return "index";
    }

    @RequestMapping(value = "/cart")
    public String carts() {
        return "index";
    }

    @RequestMapping(value = "/checkout")
    public String checkout() {
        return "index";
    }



}

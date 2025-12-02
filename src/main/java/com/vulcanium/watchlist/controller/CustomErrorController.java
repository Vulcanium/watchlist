package com.vulcanium.watchlist.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletResponse response) {
        int code = response.getStatus();
        System.out.println("Error with code " + code + " happened!");

        return "error";
    }
}

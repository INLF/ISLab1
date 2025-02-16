package com.eldar.ISlab1.controller;


import com.eldar.ISlab1.service.impl.RingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MainController {
    private final RingServiceImpl ringServiceImpl;




}

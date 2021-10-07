package ru.darvell.gb.spring.controller.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.darvell.gb.spring.domain.dto.ShopUserRegisterDTO;
import ru.darvell.gb.spring.service.UserService;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @GetMapping
    public String getForm(Model model) {
        model.addAttribute("registerData", new ShopUserRegisterDTO());
        return "users/register";
    }

    @PostMapping
    public String addUser(ShopUserRegisterDTO dto) {
        userService.addUser(dto);
        return "redirect:/product";
    }

}

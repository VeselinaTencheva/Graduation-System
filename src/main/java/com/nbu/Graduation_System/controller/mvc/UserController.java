package com.nbu.Graduation_System.controller.mvc;

import com.nbu.Graduation_System.dto.UserDto;
import com.nbu.Graduation_System.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    return "users/view";
                })
                .orElse("redirect:/users");
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "users/form";
    }

    @PostMapping
    public String createUser(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        UserDto savedUser = userService.save(userDto);
        redirectAttributes.addFlashAttribute("message", "User created successfully");
        return "redirect:/users/" + savedUser.getId();
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable Long id, Model model) {
        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    return "users/form";
                })
                .orElse("redirect:/users");
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserDto userDto, 
                           RedirectAttributes redirectAttributes) {
        if (!userService.existsById(id)) {
            return "redirect:/users";
        }
        userDto.setId(id);
        userService.save(userDto);
        redirectAttributes.addFlashAttribute("message", "User updated successfully");
        return "redirect:/users/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (userService.existsById(id)) {
            userService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "User deleted successfully");
        }
        return "redirect:/users";
    }
}

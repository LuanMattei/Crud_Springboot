package com.example.crud.controller;

import com.example.crud.Exception.userNotFoundException;
import com.example.crud.entities.User;
import com.example.crud.services.userServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class userController {
    @Autowired private userServices service;
    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public  String showNewForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Add new User");
        return "user_form";
    }
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
     service.save(user);
     ra.addFlashAttribute("message","The user has been saved successfully. ");
     return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id,Model model,RedirectAttributes ra){
        try{
            User user = service.get(id);
            model.addAttribute("user",user);
            model.addAttribute("pageTitle","Edit User");
            return "user_form";
        }catch (userNotFoundException e){
            ra.addFlashAttribute("message","The user has been saved successfully");
            return "redirect:/users";

        }
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message","The user has been deleted successfully");
        }
        catch (userNotFoundException e){
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/users";
    }

}

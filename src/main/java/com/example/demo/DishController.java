package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class DishController {
    @Autowired
    DishRepository messageRepository;
    @Autowired
    RecipientRepository recipientRepository;

    @RequestMapping("/")
    public String homePage(Model model)
    {

        model.addAttribute("messagelist",messageRepository.findAll());
        model.addAttribute("recipilist",recipientRepository.findAll());
        return "index";
    }

    @RequestMapping("/addmessage")
    public String addPet(Model model)
    {
        model.addAttribute("aMessage", new Dish());
        model.addAttribute("messageRecip",recipientRepository.findAll());
        return "dishform";
    }

    @RequestMapping("/savemessage")
    public String savePet(@ModelAttribute("aMessage") Dish message, Model model)
    {

        messageRepository.save(message);
        model.addAttribute("messageNow",recipientRepository.findAll());
        return "redirect:/";
    }

    @RequestMapping("/redirect/{id}")
    public String redirect(@PathVariable("id") long id, Model model){
        Dish mess=messageRepository.findById(id).get();
        mess.setLikeCount(mess.getLikeCount()+1);
        mess.setUrl(mess.getUrl());
        messageRepository.save(mess);

        return "redirect:/";
    }
    @RequestMapping("/redirectdis/{id}")
    public String redirectdis(@PathVariable("id") long id, Model model){
        Dish mess=messageRepository.findById(id).get();
        mess.setDislikeCount(mess.getDislikeCount()+1);
        mess.setUrl(mess.getUrl());
        messageRepository.save(mess);

        return "redirect:/";
    }


}

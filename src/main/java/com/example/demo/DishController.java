package com.example.demo;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


@Controller
public class DishController {

    @Autowired
    private DishRepository dishes;

    @Autowired
    private TastyRepository tastyVotes;

    @Autowired
    private NastyRepository nastyVotes;

    @Autowired
    private TastyNastyService foodService;

    @Autowired
    private CloudinaryConfig cloudc;

    @Autowired
    DishRepository messageRepository;
    @Autowired
    RecipientRepository recipientRepository;

    @RequestMapping("/")
    public String homePage(Model model)
    {
        ArrayList<Dish> dishList = (ArrayList)dishes.findAll();
        for (Dish dish:dishList){
            dish.setLast5minutes(foodService.last5MinuteResult(dish.getId()));
        }
        model.addAttribute("messagelist",messageRepository.findAll());
        model.addAttribute("recipilist",recipientRepository.findAll());
        return "listfood";
    }

    @RequestMapping("list")
    public String showList(Model model)
    {
        ArrayList<Dish> dishList = (ArrayList)dishes.findAll();
        for (Dish dish:dishList){
            dish.setLast5minutes(foodService.last5MinuteResult(dish.getId()));
        }
        model.addAttribute("dishes", dishList);
        return "listfood3";
    }

    @RequestMapping("/tastyvote")
    public String addTastyVote(HttpServletRequest request) {
        long foodID = new Long(request.getParameter("id"));
        Tasty tastyNastyVote = new Tasty();
        tastyNastyVote.setTheDish(dishes.findById(foodID).get());
        tastyNastyVote.setVoteAt();
        tastyVotes.save(tastyNastyVote);
        return "redirect:/";
    }

    @RequestMapping("/nastyvote")
    public String addNastyNote(HttpServletRequest request)
    {
        long foodID= new Long(request.getParameter("id"));
        Nasty nastyVote = new Nasty();
        nastyVote.setTheDish(dishes.findById(foodID).get());
        nastyVote.setVoteAt();
        nastyVotes.save(nastyVote);
        return "redirect:/";
    }

    @RequestMapping("/showvotes")
    public @ResponseBody String pizzaVotes()
    {
        Dish pizza = dishes.findById(new Long(1)).get();
        String tastyNasty = pizza.getTastyVotes().size()>=pizza.getNastyVotes().size()?
                "tasty":"nasty";
        return pizza.getDescription()+" has"+pizza.getTastyVotes().size()+" tasty votes and "+pizza.getNastyVotes().size()+"nasty votes. The balance tilts in favour of: "+tastyNasty;
    }

    @GetMapping("/dish")
    public String addDish (Model model)
    {
        model.addAttribute("theDish", new Dish());
        return  "addfood";
    }

    @PostMapping("/savefood")
    public String savefood( @Valid @ModelAttribute("theDish") Dish toSave, BindingResult result, Model model, MultipartHttpServletRequest request)
        throws IOException {
        MultipartFile f = request.getFile("file");
        if (f.isEmpty() && toSave.getImageurl().isEmpty()) {
            return "addfood";
        }
        if (toSave.getImageurl().isEmpty()) {
            Map uploadResult = cloudc.upload(f.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            String uploadURL = (String)
                    uploadResult.get("url");
            String uploadedName = (String)
                    uploadResult.get("public_id");

            String transformedImage = cloudc.createUrl(uploadedName);


            System.out.println(transformedImage);
            System.out.println("Uploaded:" + uploadURL);
            System.out.println("Name:" + uploadedName);
            toSave.setImageurl(transformedImage);
        }
        if (result.hasErrors()) {
            return "addfood";
        }
        dishes.save(toSave);
        return "redirect:/";
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

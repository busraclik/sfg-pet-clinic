package gru.springframework.sfgpetclinic.controllers;

import gru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String listOwners(Model model){

        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping({"/find"})
    public String findOwners(){
        return "notimplemented";
    }


//    @GetMapping("/{ownerId}")
//    public String showOwner(@PathVariable String ownerId, Model model){
//
//        model.addAttribute("owner",ownerService.findById(Long.parseLong(ownerId)));
//        return "owners/ownerDetails";
//    }
}

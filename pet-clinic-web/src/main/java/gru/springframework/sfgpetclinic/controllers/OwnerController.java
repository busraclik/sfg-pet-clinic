package gru.springframework.sfgpetclinic.controllers;

import gru.springframework.sfgpetclinic.model.Owner;
import gru.springframework.sfgpetclinic.services.OwnerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

//    @RequestMapping({"","/","/index","/index.html"})
//    public String listOwners(Model model){
//
//        model.addAttribute("owners", ownerService.findAll());
//        return "owners/index";
//    }

    @RequestMapping({"/find"})
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        //allow parameterless get request for /owners to return all records
        if (owner.getLastName() == null){
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        //find owners by last name
        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (results.isEmpty()){
            //no owners found
            result.rejectValue("lastname","not found", "not found");
            return "owners/findOwners";
        }else if (results.size() == 1){
            //one owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }else{
            //multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

//    @GetMapping("/{ownerId}")
//    public String showOwner(@PathVariable String ownerId, Model model){
//        model.addAttribute("owner",ownerService.findById(Long.parseLong(ownerId)));
//        return "owners/ownerDetails";
//    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    //todo initCreationForm

    @GetMapping("/new")
    public String initCreationForm(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    //todo processCreationForm
    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("owner", owner);
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            model.addAttribute("owner", owner);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }


    //todo initUpdateOwnerForm
//    @GetMapping("/{ownerId}/edit")
//    public String initUpdateOwnerForm(@PathVariable String ownerId,Model model){
//        model.addAttribute("owner", ownerService.findById(Long.parseLong(ownerId));
//        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
//    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }
    //todo processUpdateOwnerForm
    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId){
        if (result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }


}

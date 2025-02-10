package gru.springframework.sfgpetclinic.controllers;
import ch.qos.logback.core.util.StringUtil;
import gru.springframework.sfgpetclinic.model.Owner;
import gru.springframework.sfgpetclinic.model.Pet;
import gru.springframework.sfgpetclinic.model.PetType;
import gru.springframework.sfgpetclinic.services.OwnerService;
import gru.springframework.sfgpetclinic.services.PetService;
import gru.springframework.sfgpetclinic.services.PetTypeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Savepoint;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
   private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;
    private final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }
    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes(){
        return petTypeService.findAll();
    }

    //findOwner

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId){
        return ownerService.findById(ownerId);
    }

    //initOwnerBinder
    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

//initCreationForm

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner,Model model){
        Pet pet = new Pet();
        owner.getPets().add(pet);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

// processCreationForm
    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model){

        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
            result.rejectValue("name","duplicate", "already exists");
        }

        owner.getPets().add(pet);
        if (result.hasErrors()){
            model.put("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }else{
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }

    }


// initUpdateForm

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId,Model model){
       model.addAttribute("pet", this.petService.findById(petId));
       return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

// processUpdateForm

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(Owner owner, @Valid Pet pet, BindingResult result, Model model){
        if (result.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }else {
            owner.getPets().add(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

}

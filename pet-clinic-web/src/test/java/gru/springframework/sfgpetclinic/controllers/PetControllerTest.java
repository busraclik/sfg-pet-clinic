package gru.springframework.sfgpetclinic.controllers;

import gru.springframework.sfgpetclinic.model.Owner;
import gru.springframework.sfgpetclinic.model.Pet;
import gru.springframework.sfgpetclinic.model.PetType;
import gru.springframework.sfgpetclinic.services.OwnerService;
import gru.springframework.sfgpetclinic.services.PetService;
import gru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

@Mock
OwnerService ownerService;
@Mock
PetService petService;
@Mock
PetTypeService petTypeService;

@InjectMocks
PetController petController;

MockMvc mockMvc;
Owner owner;
Set<PetType> petTypes;
@BeforeEach
void setUp(){
     owner = Owner.builder().id(1L).build();
     petTypes = new HashSet<>();
     petTypes.add(PetType.builder().id(1L).name("Dog").build());
     petTypes.add(PetType.builder().id(2L).name("Cat").build());

     mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
}

@Test
void initCreationForm() throws Exception{
     when(ownerService.findById(any())).thenReturn(owner);
     when(petTypeService.findAll()).thenReturn(petTypes);

     mockMvc.perform(get("/owners/1/pets/new"))
             .andExpect(status().isOk())
             .andExpect(model().attributeExists("owner"))
             .andExpect(model().attributeExists("pet"))
             .andExpect(view().name("pets/createOrUpdatePetForm"));
}


//processCreationForm
@Test
void processCreationForm() throws Exception{
     when(ownerService.findById(anyLong())).thenReturn(owner);
     when(petTypeService.findAll()).thenReturn(petTypes);

     mockMvc.perform(post("/owners/1/pets/new"))
             .andExpect(status().is3xxRedirection())
             .andExpect(view().name("redirect:/owners/1"));

     verify(petService).save(any());
}
//initUpdateForm
@Test
void initUpdateForm() throws Exception{
     when(ownerService.findById(any())).thenReturn(owner);
     when(petTypeService.findAll()).thenReturn(petTypes);
     when(petService.findById(any())).thenReturn(Pet.builder().id(2L).build());

     mockMvc.perform(get("/owners/1/pets/2/edit"))
             .andExpect(status().isOk())
             .andExpect(model().attributeExists("owner"))
             .andExpect(model().attributeExists("pet"))
             .andExpect(view().name("pets/createOrUpdatePetForm"));

}

//processUpdateForm
@Test
void processUpdateForm() throws Exception{
     when(ownerService.findById(any())).thenReturn(owner);
     when(petTypeService.findAll()).thenReturn(petTypes);

     mockMvc.perform(post("/owners/1/pets/2/edit"))
             .andExpect(status().is3xxRedirection())
             .andExpect(view().name("redirect:/owners/1"));

     verify(petService).save(any());
}


}

package gru.springframework.sfgpetclinic.controllers;

import gru.springframework.sfgpetclinic.model.Vet;
import gru.springframework.sfgpetclinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.thymeleaf.spring6.expression.Mvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    VetService vetService;
    @InjectMocks
     VetController vetController;

    MockMvc mockMvc;
    Set<Vet> vets;

    @BeforeEach
    void setUp() {
        vets = new HashSet<>();
        vets.add(Vet.builder().id(1L).build());
        vets.add(Vet.builder().id(2L).build());
        vets.add(Vet.builder().id(3L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(vetController)
                .build();
    }

    @Test
    void listVets() throws Exception {
        when(vetService.findAll()).thenReturn(vets);

        mockMvc.perform(MockMvcRequestBuilders.get("/vets"))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/index"))
                .andExpect(model().attribute("vets", hasSize(3)));
    }

    @Test
    void listVetsByIndex() throws Exception {
        when(vetService.findAll()).thenReturn(vets);
        mockMvc.perform(MockMvcRequestBuilders.get("/vets/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/index"))
                .andExpect(model().attribute("vets", hasSize(3)));
    }
}
package gru.springframework.sfgpetclinic.services.springdatajpa;

import gru.springframework.sfgpetclinic.model.Pet;
import gru.springframework.sfgpetclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {
    @Mock
    PetRepository petRepository;
    @InjectMocks
    PetSDJpaService petSDJpaService;
    Pet returnPet;

    @BeforeEach
    void setUp() {
        returnPet = Pet.builder().id(1L).build();
    }

    @Test
    void findById() {
       when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));
       Pet pet = petSDJpaService.findById(1L);
       assertNotNull(pet);
    }

    @Test
    void finByIdNotFound() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());
        Pet pet = petSDJpaService.findById(1L);
        assertNull(pet);
    }

    @Test
    void save() {
        Pet petToSave = Pet.builder().id(1L).build();
        when(petRepository.save(any())).thenReturn(returnPet);
        Pet savedPet = petSDJpaService.save(petToSave);
        assertNotNull(savedPet);
    }

    @Test
    void findAll() {
        Set<Pet> returnPets = new HashSet<>();
        returnPets.add(Pet.builder().id(1L).build());
        returnPets.add(Pet.builder().id(2L).build());

        when(petRepository.findAll()).thenReturn(returnPets);
        Set<Pet> petSet = petSDJpaService.findAll();
        assertNotNull(petSet);
        assertEquals(2,petSet.size());
    }

    @Test
    void delete() {
        petSDJpaService.delete(returnPet);
        verify(petRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        petSDJpaService.deleteById(1L);
        verify(petRepository).deleteById(anyLong());
    }
}
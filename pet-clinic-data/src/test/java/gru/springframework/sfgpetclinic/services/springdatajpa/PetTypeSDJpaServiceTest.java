package gru.springframework.sfgpetclinic.services.springdatajpa;

import gru.springframework.sfgpetclinic.model.Pet;
import gru.springframework.sfgpetclinic.model.PetType;
import gru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import gru.springframework.sfgpetclinic.repositories.VetRepository;
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
class PetTypeSDJpaServiceTest {

    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    PetTypeSDJpaService petTypeSDJpaService;
    PetType returnPetType;
    @BeforeEach
    void setUp() {
        returnPetType = PetType.builder().id(1L).build();
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnPetType));
        PetType petType = petTypeSDJpaService.findById(1L);
        assertNotNull(petType);
    }
    @Test
    void findByIdNotFound() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.empty());
        PetType petType = petTypeSDJpaService.findById(1L);
        assertNull(petType);
    }

    @Test
    void save() {
        PetType petTypeToSave = PetType.builder().id(1L).build();
        when(petTypeRepository.save(any())).thenReturn(returnPetType);
        PetType savedPetType = petTypeSDJpaService.save(petTypeToSave);
        assertNotNull(savedPetType);
    }

    @Test
    void findAll() {
        Set<PetType> returnPetTypes = new HashSet<>();
        returnPetTypes.add(PetType.builder().id(1L).build());
        returnPetTypes.add(PetType.builder().id(2L).build());
        when(petTypeRepository.findAll()).thenReturn(returnPetTypes);

        Set<PetType> petTypes = petTypeSDJpaService.findAll();
        assertNotNull(petTypes);
        assertEquals(2, petTypes.size());

    }

    @Test
    void delete() {
        petTypeSDJpaService.delete(returnPetType);
        verify(petTypeRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        petTypeSDJpaService.deleteById(1L);
        verify(petTypeRepository).deleteById(anyLong());
    }
}
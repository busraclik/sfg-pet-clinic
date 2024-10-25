package gru.springframework.sfgpetclinic.services.springdatajpa;

import gru.springframework.sfgpetclinic.model.Vet;
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
class VetSDJpaServiceTest {

    @Mock
    VetRepository vetRepository;
    @InjectMocks
    VetSDJpaService vetSDJpaService;
    Vet returnVet;

    @BeforeEach
    void setUp() {
        returnVet = Vet.builder().id(1L).build();
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnVet));
        Vet vet = vetSDJpaService.findById(1L);
        assertNotNull(vet);
    }

    @Test
    void save() {
        Vet vetToSave = Vet.builder().id(1L).build();

        when(vetRepository.save(any())).thenReturn(returnVet);
        Vet savedVet = vetSDJpaService.save(vetToSave);
        assertNotNull(savedVet);

    }

    @Test
    void findAll() {
        Set<Vet> returnVets = new HashSet<>();
        returnVets.add(Vet.builder().id(1L).build());
        returnVets.add(Vet.builder().id(2L).build());
        when(vetRepository.findAll()).thenReturn(returnVets);

        Set<Vet> vetSet = vetSDJpaService.findAll();
        assertNotNull(vetSet);
        assertEquals(vetSet.size(),2);

    }

    @Test
    void delete() {
       vetSDJpaService.delete(returnVet);
       verify(vetRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        vetSDJpaService.deleteById(1L);
        verify(vetRepository).deleteById(anyLong());
    }
}
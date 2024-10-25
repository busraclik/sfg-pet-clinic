package gru.springframework.sfgpetclinic.services.springdatajpa;

import gru.springframework.sfgpetclinic.model.Speciality;
import gru.springframework.sfgpetclinic.repositories.SpecialityRepository;
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
class SpecialitySDJPaServiceTest {
    @Mock
    SpecialityRepository specialityRepository;
    @InjectMocks
    SpecialitySDJPaService specialitySDJPaService;
    Speciality returnSpeciality;

    @BeforeEach
    void setUp() {
        returnSpeciality = Speciality.builder().id(1L).build();
    }

    @Test
    void findById() {
        when(specialityRepository.findById(anyLong())).thenReturn(Optional.of(returnSpeciality));
        Speciality speciality = specialitySDJPaService.findById(1L);
        assertNotNull(speciality);
    }
    @Test
    void findByIdNotFound() {
        when(specialityRepository.findById(anyLong())).thenReturn(Optional.empty());
        Speciality speciality = specialitySDJPaService.findById(1L);
        assertNull(speciality);
    }

    @Test
    void save() {
        Speciality specialityToSave = Speciality.builder().id(1L).build();
        when(specialityRepository.save(any())).thenReturn(returnSpeciality);
        Speciality savedSpeciality = specialitySDJPaService.save(specialityToSave);
        assertNotNull(savedSpeciality);
    }


    @Test
    void findAll() {
        Set<Speciality> returnSpecialitySet = new HashSet<>();
        returnSpecialitySet.add(Speciality.builder().id(1L).build());
        returnSpecialitySet.add(Speciality.builder().id(2L).build());
        when(specialityRepository.findAll()).thenReturn(returnSpecialitySet);

        Set<Speciality> specialitySet = specialitySDJPaService.findAll();
        assertEquals(specialitySet.size(),2);


    }

    @Test
    void delete() {
        specialitySDJPaService.delete(returnSpeciality);
        verify(specialityRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        specialitySDJPaService.deleteById(anyLong());
        verify(specialityRepository).deleteById(anyLong());
    }
}
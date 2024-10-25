package gru.springframework.sfgpetclinic.services.springdatajpa;

import gru.springframework.sfgpetclinic.model.Visit;
import gru.springframework.sfgpetclinic.repositories.VisitRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {
    @Mock
    VisitRepository visitRepository;
    @InjectMocks
    VisitSDJpaService visitSDJpaService;
    Visit returnVisit;

    @BeforeEach
    void setUp() {
        returnVisit = Visit.builder().id(1L).build();
    }

    @Test
    void findById() {
        when(visitRepository.findById(1L)).thenReturn(Optional.of(returnVisit));
        Visit visit = visitSDJpaService.findById(1L);
        assertNotNull(visit);
    }

    @Test
    void findByIdNotFound() {
        when(visitRepository.findById(1L)).thenReturn(Optional.empty());
        Visit visit = visitSDJpaService.findById(1L);
        assertNull(visit);
    }
    @Test
    void save() {
        Visit visitToSave = Visit.builder().id(1L).build();
        when(visitRepository.save(visitToSave)).thenReturn(returnVisit);
        Visit savedVisit = visitSDJpaService.save(visitToSave);
        assertNotNull(savedVisit);
    }

    @Test
    void findAll() {
        Set<Visit> returnVisits = new HashSet<>();
        returnVisits.add( Visit.builder().id(1L).build());
        returnVisits.add( Visit.builder().id(2L).build());

        when(visitRepository.findAll()).thenReturn(returnVisits);
        Set<Visit> visits = visitSDJpaService.findAll();
        assertNotNull(visits);
        assertEquals(2, visits.size());
    }

    @Test
    void delete() {
        visitSDJpaService.delete(returnVisit);
        verify(visitRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        visitSDJpaService.deleteById(1L);
        verify(visitRepository).deleteById(anyLong());
    }
}
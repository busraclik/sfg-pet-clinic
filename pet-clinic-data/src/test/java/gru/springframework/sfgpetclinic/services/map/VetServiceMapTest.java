package gru.springframework.sfgpetclinic.services.map;

import gru.springframework.sfgpetclinic.model.Speciality;
import gru.springframework.sfgpetclinic.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class VetServiceMapTest {

    VetServiceMap vetServiceMap;
    final Long vetId = 1L;

    @BeforeEach
    void setUp() {
        vetServiceMap = new VetServiceMap(new SpecialityMapService());
        Set<Speciality> specialitySet = new HashSet<>();
        vetServiceMap.save(Vet.builder().id(vetId).specialities(specialitySet).build());
    }

    @Test
    void findById() {
        Vet vet = vetServiceMap.findById(vetId);
        assertEquals(vet.getId(), vetId);
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Vet vet2 = vetServiceMap.save(Vet.builder().id(id).specialities(new HashSet<>()).build());
        assertEquals(2, vet2.getId());
    }

    @Test
    void saveNoId() {
        Vet vet = vetServiceMap.save(Vet.builder().specialities(new HashSet<>()).build());
        assertNotNull(vet);
        assertNotNull(vet.getId());
    }

    @Test
    void findAll() {
        Set<Vet> vets = vetServiceMap.findAll();
        assertEquals(1,vets.size());
    }

    @Test
    void deleteById() {
        vetServiceMap.deleteById(vetId);
        assertEquals(0, vetServiceMap.findAll().size());
    }

    @Test
    void delete() {
        vetServiceMap.delete(vetServiceMap.findById(vetId));
        assertEquals(0, vetServiceMap.findAll().size());
    }
}
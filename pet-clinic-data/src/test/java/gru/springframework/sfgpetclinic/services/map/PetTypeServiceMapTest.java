package gru.springframework.sfgpetclinic.services.map;

import gru.springframework.sfgpetclinic.model.PetType;
import gru.springframework.sfgpetclinic.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeServiceMapTest {
    PetTypeServiceMap petTypeServiceMap;
    final Long petTypeId = 1L;
    @BeforeEach
    void setUp() {
        petTypeServiceMap = new PetTypeServiceMap();
        petTypeServiceMap.save(PetType.builder().id(petTypeId).build());
    }

    @Test
    void findById() {
        PetType petType = petTypeServiceMap.findById(petTypeId);
        assertEquals(petTypeId, petType.getId());
    }

    @Test
    void save() {
        Long id = 2L;
        PetType petType2 = PetType.builder().id(id).build();
        petTypeServiceMap.save(petType2);
        assertEquals(petType2.getId(),id);
    }

    @Test
    void findAll() {
        Set<PetType> petTypes = petTypeServiceMap.findAll();
        assertEquals(petTypes.size(),1);
    }

    @Test
    void delete() {
        petTypeServiceMap.delete(petTypeServiceMap.findById(petTypeId));
        assertEquals(0, petTypeServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        petTypeServiceMap.deletedById(petTypeId);
        assertEquals(0, petTypeServiceMap.findAll().size());
    }
}
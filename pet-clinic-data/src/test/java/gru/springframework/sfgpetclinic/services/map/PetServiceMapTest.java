package gru.springframework.sfgpetclinic.services.map;

import gru.springframework.sfgpetclinic.model.Pet;
import gru.springframework.sfgpetclinic.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.parsers.SAXParser;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceMapTest {

    PetServiceMap petServiceMap;
    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMap();
        petServiceMap.save(Pet.builder().id(id).name("Fındık").build());
    }

    @Test
    void findById() {
        Pet pet = petServiceMap.findById(id);
        assertEquals(1, pet.getId());
    }

    @Test
    void saveExistingId() {
       Long id = 2L;
       Pet pet2 = Pet.builder().id(id).name("Kaju").build();
       Pet savedPet = petServiceMap.save(pet2);
       assertEquals(2, savedPet.getId());
    }

    @Test
    void saveNoId() {
        Pet savedPet = petServiceMap.save(Pet.builder().build());

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());

    }

    @Test
    void findAll() {
        Set<Pet> petsSet = petServiceMap.findAll();
        assertEquals(1, petsSet.size());
    }

    @Test
    void deleteById() {
        petServiceMap.deleteById(id);
        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void delete() {
        petServiceMap.delete(petServiceMap.findById(id));
        assertEquals(0, petServiceMap.findAll().size());
    }
}
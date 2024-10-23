package gru.springframework.sfgpetclinic.services.map;

import gru.springframework.sfgpetclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SpecialityMapServiceTest {
    SpecialityMapService specialityMapService;
    final Long id = 1L;

    @BeforeEach
    void setUp() {
        specialityMapService = new SpecialityMapService();
        specialityMapService.save(Speciality.builder().id(id).build());
    }

    @Test
    void findById() {
        Speciality speciality = specialityMapService.findById(id);
        assertEquals(speciality.getId(),id);
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Speciality speciality = Speciality.builder().id(id).build();
        assertEquals(speciality.getId(),id);
    }

    @Test
    void saveNoId() {
        Speciality speciality = specialityMapService.save(Speciality.builder().build());
        assertNotNull(speciality);
        assertNotNull(speciality.getId());
    }

    @Test
    void findAll() {
        Set<Speciality> specialities = specialityMapService.findAll();
        assertEquals(1, specialities.size());
    }

    @Test
    void deleteById() {
        specialityMapService.deletedById(id);
        assertEquals(0, specialityMapService.findAll().size());
    }

    @Test
    void delete() {
        specialityMapService.delete(specialityMapService.findById(id));
        assertEquals(0, specialityMapService.findAll().size());
    }
}
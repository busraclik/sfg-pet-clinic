package gru.springframework.sfgpetclinic.services.map;

import gru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {
    OwnerServiceMap ownerServiceMap;
    final Long ownerId = 1L;
    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerId).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet =  ownerServiceMap.findAll();
        assertEquals(1,ownerSet.size());
    }
    @Test
    void findById() {
       Owner owner = ownerServiceMap.findById(ownerId);
       assertEquals(owner.getId(),ownerId);
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerServiceMap.save(owner2);
        assertEquals(2, savedOwner.getId());

    }

    @Test
    void saveNoId() {
        Owner savedOwner = Owner.builder().build();
        assertNotNull(savedOwner);
        //assertNotNull(savedOwner.getId());
    }

    @Test
    void deleteById() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByLastName() {
    }
}
package gru.springframework.sfgpetclinic.services.map;

import gru.springframework.sfgpetclinic.model.Pet;
import gru.springframework.sfgpetclinic.services.PetService;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object);
    }

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deletedById(id);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }
}

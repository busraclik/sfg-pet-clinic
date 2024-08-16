package gru.springframework.sfgpetclinic.services.map;

import gru.springframework.sfgpetclinic.model.Vet;
import gru.springframework.sfgpetclinic.services.CrudService;
import gru.springframework.sfgpetclinic.services.VetService;

import java.util.Set;

public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deletedById(id);
    }

    @Override
    public void delete(Vet object) {
       super.delete(object);
    }
}

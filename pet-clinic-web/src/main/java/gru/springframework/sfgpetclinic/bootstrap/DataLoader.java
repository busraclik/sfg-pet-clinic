package gru.springframework.sfgpetclinic.bootstrap;

import gru.springframework.sfgpetclinic.model.*;
import gru.springframework.sfgpetclinic.services.OwnerService;
import gru.springframework.sfgpetclinic.services.PetTypeService;
import gru.springframework.sfgpetclinic.services.SpecialityService;
import gru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count  = petTypeService.findAll().size();
        if (count == 0) {
            dataLoad();
        }


    }

    private void dataLoad() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);


        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savrdSurgery = specialityService.save(surgery);

        Speciality dentistyr = new Speciality();
        dentistyr.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistyr);


        Owner owner1 = new Owner();
        //owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("12346655");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        //owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("12346655");

        Pet fionasCat = new Pet();
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setPetType(savedCatPetType);
        owner2.getPets().add(fionasCat);

        ownerService.save(owner2);

        System.out.println("load owners...");

        Vet vet1 = new Vet();
        //vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedDentistry);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        //vet2.setId(2L);
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savrdSurgery);
        vetService.save(vet2);

        Vet vet3 = new Vet();
        vet3.setFirstName("Busra");
        vet3.setLastName("Celik");
        vet3.getSpecialities().add(savedRadiology);
        vetService.save(vet3);

        Vet vet4 = new Vet();
        vet4.setFirstName("Duru");
        vet4.setLastName("Simge");
        vet4.getSpecialities().add(savrdSurgery);
        vetService.save(vet4);



        System.out.println("sload vets...");
    }
}

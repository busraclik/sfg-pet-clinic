package gru.springframework.sfgpetclinic.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person{

    public Owner(Long id, String firstName, String lastName,String address, String city, String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
        //this.pets = (pets != null) ? pets : new HashSet<>();
    }

    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "telephone")
    private String telephone;

    //ex: if i delete owner pets delete also
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @Builder.Default
    private Set<Pet> pets = new HashSet<>();


    public Pet getPet(String name){
        return getPet(name, false);
    }

    public Pet getPet(String name, boolean ingnoreNew){
       name = name.toLowerCase();

       for (Pet pet : pets){
           if (!ingnoreNew || !pet.isNew()){
               String compName = pet.getName();
               compName = compName.toLowerCase();
               if (compName.equals(name)){
                   return pet;
               }
           }
       }
       return null;
    }

}

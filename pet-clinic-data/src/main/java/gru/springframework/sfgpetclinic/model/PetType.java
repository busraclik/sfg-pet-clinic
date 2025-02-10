package gru.springframework.sfgpetclinic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "types")
public class PetType extends BaseEntity{
    //adding builder pattern
//    @Builder
//    public PetType(Long id, String name){
//        super(id);
//        this.name = name;
//    }

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return name;
    }

}

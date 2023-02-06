package com.example.changelogautomaticgeneration.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Customer implements Serializable {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "name", unique = true, nullable = false, length = 128)
    @NotNull
    private String name;
    @Column(name = "phone", length = 64)
    private String phone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Address> address;
}

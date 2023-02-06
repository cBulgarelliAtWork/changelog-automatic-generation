package com.example.changelogautomaticgeneration.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "address_type")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class AddressType implements Serializable {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "description", nullable = false, unique = true, length = 128)
    @NotNull
    private String description;
}

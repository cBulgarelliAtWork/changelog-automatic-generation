package com.example.changelogautomaticgeneration.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "contact_method")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ContactMethod implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "method_type", nullable = false, length = 1)
    @NotEmpty
    private ContactMethodType type;
    @Column(name = "value", nullable = false, length = 128)
    @NotEmpty
    private String value;
    @Column(name = "descriptionnnnnnnn", nullable = false, length = 128)
    @NotEmpty
    private String description;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_customer", referencedColumnName = "id", nullable = false, updatable = false)
    @NotNull
    private Customer customer;
}

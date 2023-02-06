package com.example.changelogautomaticgeneration.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Address implements Serializable {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_customer", referencedColumnName = "id", nullable = false)
    @NotNull
    private Customer customer;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_address_type", referencedColumnName = "id", nullable = false)
    @NotNull
    private AddressType addressType;
    @Column(name = "street", nullable = false, length = 128)
    @NotNull
    private String street;
    @Column(name = "number", nullable = false, length = 32)
    @NotNull
    private String number;
    @Column(name = "city", nullable = false, length = 128)
    @NotNull
    private String city;
    @Column(name = "country", nullable = false, length = 128)
    @NotNull
    private String country;
    @Column(name = "zip_code", nullable = false, length = 32)
    @NotNull
    private String zipCode;
}

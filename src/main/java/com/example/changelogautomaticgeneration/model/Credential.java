package com.example.changelogautomaticgeneration.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "credential")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Credential implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "username", unique = true, updatable = false, length = 32)
    private String username;
    @Column(name = "password", length = 32)
    private String password;
}

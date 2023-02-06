package com.example.changelogautomaticgeneration.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "article")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Article implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "description", unique = true, nullable = false, length = 256)
    @NotNull
    private String description;
    @Column(name = "visible", nullable = false)
    private boolean visible;
}

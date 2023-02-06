package com.example.changelogautomaticgeneration.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Order implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "order_date", nullable = false)
    @NotNull
    private LocalDateTime date;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_customer", referencedColumnName = "id", nullable = false, updatable = false)
    @NotNull
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    @Builder.Default
    @NotEmpty
    private List<OrderItem> items = new ArrayList<>();
}

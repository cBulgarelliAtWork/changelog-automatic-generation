package com.example.changelogautomaticgeneration.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "order")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_order", referencedColumnName = "id", nullable = false, updatable = false)
    @NotNull
    private Order order;
    @ManyToOne
    @JoinColumn(name = "id_article", referencedColumnName = "id", nullable = false)
    @NotNull
    private Article article;
    @Column(name = "amount", nullable = false)
    @NotNull
    private int amount;
}

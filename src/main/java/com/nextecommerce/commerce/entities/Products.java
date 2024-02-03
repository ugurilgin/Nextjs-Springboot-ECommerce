package com.nextecommerce.commerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Products extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    private Long id;
    @NotBlank(message = "Title name can not be null")
    @Column(name = "title", nullable = false)
    private String title;
    @NotNull(message = "Price  can not be null")
    @Column(name = "price", nullable = false)
    private Double price;

    @NotBlank(message = "Description name can not be null")
    @Column(name = "description", nullable = false)
    private String description;

    @NotBlank(message = "Category name can not be null")
    @Column(name = "category", nullable = false)
    private String category;

    @NotBlank(message = "Image name can not be null")
    @Column(name = "image", nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "rating_id")
    private Ratings ratings;


}

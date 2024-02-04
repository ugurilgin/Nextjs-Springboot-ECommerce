package com.nextecommerce.commerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted = false")
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


    @NotBlank(message = "Image name can not be null")
    @Column(name = "image", nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "rating_id")
    private Ratings ratings;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "products_categories",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") })
    private Set<Categories> category = new HashSet<>();

    public void addCategory(Categories category) {
        this.category.add(category);
        category.getProducts().add(this);
    }

    public void removeCategory(long categoryId) {
        Categories categories = this.category.stream().filter(t -> t.getId() == categoryId).findFirst().orElse(null);
        if (categories != null) {
            this.category.remove(categories);
            categories.getProducts().remove(this);
        }
    }

}

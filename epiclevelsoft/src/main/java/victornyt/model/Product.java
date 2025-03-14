package victornyt.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    public Integer id;
    @ManyToOne
    @JoinColumn(columnDefinition = "category_id")
    public Category category;
    @Column(unique = true)
    public String name;
    public String description;
    public Double cost;
    public Double price;
    public Integer stockQuantity;
    public LocalDateTime deletedAt;
}

package victornyt.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "salestemp")
public class SaleTemp {
    @Id
    @GeneratedValue
    public Long id;
    @ManyToOne
    @JoinColumn(columnDefinition = "product_id")
    public Product product;
    @ManyToOne
    @JoinColumn(columnDefinition = "client_id")
    public Client client;
    public Integer quantity;
    public Double price;
    public Double cost;
    public LocalDateTime createdAt;
}

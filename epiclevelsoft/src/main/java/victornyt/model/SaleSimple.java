package victornyt.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "salessimple")
public class SaleSimple {
    @Id
    @GeneratedValue
    public Integer id;
    public LocalDateTime createdAt;
    @OneToOne
    @JoinColumn(columnDefinition = "sale_id")
    public Sale sale;
    @ManyToOne
    @JoinColumn(columnDefinition = "client_id")
    public Client client;
    public Double totalValue;
    @ManyToOne
    @JoinColumn(columnDefinition = "payment_id")
    public Payment payment;
}

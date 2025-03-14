package victornyt.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue
    public Long id;
    public LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(columnDefinition = "client_id")
    public Client client;
    @JdbcTypeCode(SqlTypes.JSON)
    public List<SaleItem> saleItems;
    @ManyToOne
    @JoinColumn(columnDefinition = "payment_id")
    public Payment payment;
    public Integer installments;
}

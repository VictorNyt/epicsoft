package victornyt.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue
    public Integer id;
    @Column(unique = true)
    public String name;
    public Double tax;
    public LocalDateTime deletedAt;
}

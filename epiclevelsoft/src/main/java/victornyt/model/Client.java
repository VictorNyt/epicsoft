package victornyt.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue
    public Integer id;
    @Column(unique = true)
    public String name;
    @Column(unique = true)
    public String cpf;
    public String Address;
    public Integer telephone;
    public String description;
    public LocalDateTime deletedAt;
}

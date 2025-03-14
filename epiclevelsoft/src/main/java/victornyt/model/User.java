package victornyt.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    public Integer id;
    @Column(unique = true)
    public String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String password;
    public String name;
    @Column(name = "is_admin", columnDefinition = "boolean default false")
    public boolean isAdmin;
    public LocalDateTime deletedAt;

    public User() {
    }

    public User(String username, String password, String name, boolean isAdmin) {
        this.username = username.toLowerCase();
        this.password = BcryptUtil.bcryptHash(password);
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public void setPassword(String password) {
        this.password = BcryptUtil.bcryptHash(password);
    }
}
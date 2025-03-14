package victornyt.repository;

import java.util.List;
import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import victornyt.model.Payment;

@ApplicationScoped
public class PaymentRepository implements PanacheRepositoryBase<Payment, Integer> {
    public Optional<Payment> findByName(String name) {
        return find("name", name).firstResultOptional();
    }

    public Optional<Payment> findByNameAndIdNot(String name, int id){
        return find("name = ?1 and id <> ?2", name, id).firstResultOptional();
    }

    @Override
    public List<Payment> listAll(){
        return find("deletedAt is null").list();
    }
}

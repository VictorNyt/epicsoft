package victornyt.repository;

import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import victornyt.model.Sale;

@ApplicationScoped
public class SaleRepository implements PanacheRepositoryBase<Sale, Long> {
    public Optional<Sale> findByName(String name){
        return find("name", name).firstResultOptional();
    }

    public Optional<Sale> findByNameAndIdNot(String name, int id){
        return find("name = ?1 and id <> ?2", name, id).firstResultOptional();
    }
}
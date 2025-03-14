package victornyt.repository;

import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import victornyt.model.SaleTemp;

@ApplicationScoped
public class SaleTRepository implements PanacheRepositoryBase<SaleTemp, Integer>{
public Optional<SaleTemp> findByName(String name){
        return find("name", name).firstResultOptional();
    }

    public Optional<SaleTemp> findByNameAndIdNot(String name, int id){
        return find("name = ?1 and id <> ?2", name, id).firstResultOptional();
    }
}

package victornyt.repository;

import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import victornyt.model.SaleSimple;

@ApplicationScoped
public class SaleSRepository implements PanacheRepositoryBase<SaleSimple, Integer>{
public Optional<SaleSimple> findByName(String name){
        return find("name", name).firstResultOptional();
    }

    public Optional<SaleSimple> findByNameAndIdNot(String name, int id){
        return find("name = ?1 and id <> ?2", name, id).firstResultOptional();
    }
}

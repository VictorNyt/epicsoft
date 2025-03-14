package victornyt.repository;

import java.util.List;
import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import victornyt.model.Product;

@ApplicationScoped
public class ProductRepository implements PanacheRepositoryBase<Product, Integer>{
public Optional<Product> findByName(String name){
        return find("name", name).firstResultOptional();
    }

    public Optional<Product> findByNameAndIdNot(String name, int id){
        return find("name = ?1 and id <> ?2", name, id).firstResultOptional();
    }

    @Override
    public List<Product> listAll(){
        return find("deletedAt is null").list();
    }
}

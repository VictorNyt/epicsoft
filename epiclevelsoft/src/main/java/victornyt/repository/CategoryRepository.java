package victornyt.repository;

import java.util.List;
import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import victornyt.model.Category;

@ApplicationScoped
public class CategoryRepository implements PanacheRepositoryBase<Category, Integer>{
    public Optional<Category> findByName(String name){
        return find("name", name).firstResultOptional();
    }

    public Optional<Category> findByNameAndIdNot(String name, int id){
        return find("name = ?1 and id <> ?2", name, id).firstResultOptional();
    }

    @Override
    public List<Category> listAll(){
        return find("deletedAt is null").list();
    }
}

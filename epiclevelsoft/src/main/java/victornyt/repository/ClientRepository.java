package victornyt.repository;

import java.util.List;
import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import victornyt.model.Client;

@ApplicationScoped
public class ClientRepository implements PanacheRepositoryBase<Client, Integer>{
    public Optional<Client> findByName(String name){
        return find("name", name).firstResultOptional();
    }

    public Optional<Client> findByNameAndIdNot(String name, int id){
        return find("name = ?1 and id <> ?2", name, id).firstResultOptional();
    }
    
    @Override
    public List<Client> listAll(){
        return find("deletedAt is null").list();
    }
}

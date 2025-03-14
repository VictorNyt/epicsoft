package victornyt.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import victornyt.model.SaleSimple;
import victornyt.repository.SaleSRepository;

@Path("/sales")
@Consumes("application/json")
@Produces("application/json")
public class SaleSController {
    @Inject
    SaleSRepository saleSRepository;

    @GET
    public List<SaleSimple> getALL() {
        return saleSRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public SaleSimple show(Integer id){
        return saleSRepository.findById(id);
    }
}

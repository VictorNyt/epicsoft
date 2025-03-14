package victornyt.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import victornyt.model.SaleTemp;
import victornyt.repository.SaleTRepository;

@Path("/salet")
@Consumes("application/json")
@Produces("application/json")
public class SaleTController {
    @Inject
    SaleTRepository saleTRepository;

    @GET
    public List<SaleTemp> getALL() {
        return saleTRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public SaleTemp show(Integer id){
        return saleTRepository.findById(id);
    }
}

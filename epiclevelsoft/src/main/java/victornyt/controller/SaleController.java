package victornyt.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import victornyt.model.Sale;
import victornyt.repository.SaleRepository;

@Path("/sale")
@Consumes("application/json")
@Produces("application/json")
public class SaleController {
    @Inject
    SaleRepository saleRepository;

    @GET
    public List<Sale> getALL() {
        return saleRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Sale show(Integer id){
        return saleRepository.findById(id.longValue());
    }
}

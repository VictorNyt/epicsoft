package victornyt.controller;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import victornyt.model.Product;
import victornyt.repository.ProductRepository;

@Path("/product")
@Consumes("application/json")
@Produces("application/json")
public class ProductController {
@Inject
    ProductRepository productRepository;

    @GET
    public List<Product> getALL() {
        return productRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Product show(Integer id){
        return productRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(Product product){
        var opt = productRepository.findByName(product.name);
        if (opt.isPresent()){
            var data = opt.get();
            if(data.deletedAt != null){
                data.deletedAt = null;
                productRepository.persistAndFlush(data);
                return Response.status(201).entity(data).build();
            }
            return Response.status(409).build();
        }
        productRepository.persistAndFlush(product);
        return Response.status(201).entity(product).build();
    }
    
    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(Integer id, Product product){
        if (productRepository.findByNameAndIdNot(product.name, id).isPresent()){
            return Response.status(409).build();
        }
        product.id = id;
        productRepository.persistAndFlush(product);
        return Response.status(200).entity(product).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(Integer id){
        var opt = productRepository.findById(id);
        opt.deletedAt = LocalDateTime.now();
        return Response.status(200).build();
    }
}

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
import victornyt.model.Category;
import victornyt.repository.CategoryRepository;

@Path("/category")
@Consumes("application/json")
@Produces("application/json")
public class CategoryController {
    @Inject
    CategoryRepository categoryRepository;

    @GET
    public List<Category> getALL() {
        return categoryRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Category show(Integer id){
        return categoryRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(Category category){
        var opt = categoryRepository.findByName(category.name);
        if (opt.isPresent()){
            var data = opt.get();
            if(data.deletedAt != null){
                data.deletedAt = null;
                categoryRepository.persistAndFlush(data);
                return Response.status(201).entity(data).build();
            }
            return Response.status(409).build();
        }
        categoryRepository.persistAndFlush(category);
        return Response.status(201).entity(category).build();
    }
    
    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(Integer id, Category category){
        if (categoryRepository.findByNameAndIdNot(category.name, id).isPresent()){
            return Response.status(409).build();
        }
        category.id = id;
        categoryRepository.persistAndFlush(category);
        return Response.status(200).entity(category).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(Integer id){
        var opt = categoryRepository.findById(id);
        opt.deletedAt = LocalDateTime.now();
        return Response.status(200).build();
    }
}
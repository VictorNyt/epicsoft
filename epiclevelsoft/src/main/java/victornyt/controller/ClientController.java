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
import victornyt.model.Client;
import victornyt.repository.ClientRepository;

@Path("/client")
@Consumes("application/json")
@Produces("application/json")
public class ClientController {
    @Inject
    ClientRepository clientRepository;

    @GET
    public List<Client> getALL() {
        return clientRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Client show(Integer id){
        return clientRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(Client client){
        var opt = clientRepository.findByName(client.name);
        if (opt.isPresent()){
            var data = opt.get();
            if(data.deletedAt != null){
                data.deletedAt = null;
                clientRepository.persistAndFlush(data);
                return Response.status(201).entity(data).build();
            }
            return Response.status(409).build();
        }
        clientRepository.persistAndFlush(client);
        return Response.status(201).entity(client).build();
    }
    
    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(Integer id, Client client){
        if (clientRepository.findByNameAndIdNot(client.name, id).isPresent()){
            return Response.status(409).build();
        }
        client.id = id;
        clientRepository.persistAndFlush(client);
        return Response.status(200).entity(client).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(Integer id){
        var opt = clientRepository.findById(id);
        opt.deletedAt = LocalDateTime.now();
        return Response.status(200).build();
    }

}

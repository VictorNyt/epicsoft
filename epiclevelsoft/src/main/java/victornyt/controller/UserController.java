package victornyt.controller;

import java.util.List;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import victornyt.model.User;
import victornyt.repository.UserRepository;

@Path("/user")
@Consumes("application/json")
@Produces("application/json")
public class UserController {

    @Inject
    UserRepository userRepository;

    @GET
    public List<User> getALL() {
        return userRepository.listAll();
    }

    @POST
    @Transactional
    public Response create(User user) {
        if (userRepository.findByUsername(user.username).isPresent()) {
            return Response.status(409).build();
        }
        user.password = BcryptUtil.bcryptHash(user.password);
        userRepository.persistAndFlush(user);
        return Response.status(201).entity(user).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response updateUser(Integer id, User user) {
        if (userRepository.findByUsername(user.username).isPresent()) {
            user.id = id;
            user.password = BcryptUtil.bcryptHash(user.password);
            userRepository.persistAndFlush(user);
            return Response.status(200).build();
        }
        return Response.status(401).build();
    }

}
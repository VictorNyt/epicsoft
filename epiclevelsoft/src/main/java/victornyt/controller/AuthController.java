package victornyt.controller;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import victornyt.model.User;
import victornyt.repository.UserRepository;

@Path("/auth")
@Consumes("application/json")
@Produces("application/json")
public class AuthController {

    record LoginRequest(String username, String password) {
    }

    record PasswordChange(@JsonProperty("old_password") String oldPassword,
            @JsonProperty("new_password") String newPassword) {
    }

    @Inject
    UserRepository userRepository;

    @POST
    @Path("/login")
    public Response login(LoginRequest request) {
        var optUser = userRepository.findByUsername(request.username());
        if (!optUser.isPresent()) {
            return Response.status(401).build();
        }
        var user = optUser.get();
        if (!BcryptUtil.matches(request.password(), user.password)) {
            return Response.status(401).build();
        }
        var token = generateToken(user, 60 * 60 * 20);
        Map<String, Object> response = Map.of("user", user, "token", token);

        return Response.ok(response).build();
    }

    private String generateToken(User user, long expires) {
        var now = Instant.now();
        var expiresIn = now.plusSeconds(expires);
        List<String> permissions = List.of("Authenticated");
        var token = Jwt
                .issuer("epiclevelsoft")
                .subject(user.id.toString())
                .upn(user.username)
                .issuedAt(now)
                .expiresAt(expiresIn)
                .groups(Set.copyOf(permissions))
                .sign();
        return token;
    }

    @GET
    @RolesAllowed("Authenticated")
    @Path("/profile")
    public Response profile(@Context SecurityContext ctx) {
        if (ctx.getUserPrincipal() == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        var principal = ctx.getUserPrincipal().getName();
        var user = userRepository.findByUsername(principal.toString());
        return user
                .map(Response::ok)
                .orElse(Response.status(Response.Status.UNAUTHORIZED))
                .build();
    }

    @PUT
    @RolesAllowed("Authenticated")
    @Transactional
    @Path("/update")
    public Response update(@Context SecurityContext ctx, PasswordChange request) {
        var optUser = userRepository.findByUsername(ctx.getUserPrincipal().getName());
        if (!optUser.isPresent()) {
            return Response.status(401).build();
        }
        var user = optUser.get();
        if (!BcryptUtil.matches(request.oldPassword(), user.password)) {
            return Response.status(403).entity(Map.of("error", "Incorrect senha")).build();
        }
        user.setPassword(request.newPassword());

        userRepository.changePassword(user);

        return Response.status(204).build();
    }

}
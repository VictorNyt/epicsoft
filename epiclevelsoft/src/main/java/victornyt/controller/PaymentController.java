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
import victornyt.model.Payment;
import victornyt.repository.PaymentRepository;

@Path("/payment")
@Consumes("application/json")
@Produces("application/json")
public class PaymentController {
    @Inject
    PaymentRepository paymentRepository;

    @GET
    public List<Payment> getALL(){
        return paymentRepository.listAll();
    }
    
    @GET
    @Path("/{id}")
    public Payment show(Integer id){
        return paymentRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(Payment payment){
        var opt = paymentRepository.findByName(payment.name);
        if (opt.isPresent()){
            var data = opt.get();
            if(data.deletedAt != null){
                data.deletedAt = null;
                paymentRepository.persistAndFlush(data);
                return Response.status(201).entity(data).build();
            }
            return Response.status(409).build();
        }
        paymentRepository.persistAndFlush(payment);
        return Response.status(201).entity(payment).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(Integer id, Payment payment){
        if (paymentRepository.findByNameAndIdNot(payment.name, id).isPresent()){
            return Response.status(409).build();
        }
        payment.id = id;
        paymentRepository.persistAndFlush(payment);
        return Response.status(201).entity(payment).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(Integer id){
        var opt = paymentRepository.findById(id);
        opt.deletedAt = LocalDateTime.now();
        return Response.status(201).build();
    }
}

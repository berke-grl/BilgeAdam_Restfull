package com.example.jakartatest.Controller;

import com.example.jakartatest.Entities.Ders;
import com.example.jakartatest.Repositories.DersRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Variant;

import java.sql.SQLException;
import java.util.List;

@Path("/ders")
public class DersController {
    private DersRepository repository = new DersRepository();

    @Path("/getAll")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Ders> list;
        try {
            list = repository.getAll();
            if (!list.isEmpty())
                return Response.ok().entity(list).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity("Ders listesi boş").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Bir hata oluştu").build();
        }
    }

    @Path("/getById/{id}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") long id) {
        Ders ders;
        try {
            ders = repository.getById(id);
            if (ders != null)
                return Response.ok().entity(ders).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity(id + "id'li Ders bulunamadı").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Bir hata oluştu").build();
        }
    }

    @Path("/add")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response save(Ders ders) {
        try {
            boolean result = repository.save(ders);
            if (result)
                return Response.ok().entity("Ders başarıyla oluşturuldu").build();
            else
                return Response.status(Response.Status.BAD_REQUEST).entity("Ders oluşturmada hata oluştu").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Bir hata oluştu").build();
        }
    }

    @Path("deleteById/{id}")
    @DELETE
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") long id) {
        try {
            boolean result = repository.deleteById(id);
            if (result)
                return Response.ok().entity(id + "id'li Ders silindi").build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity(id + "id'li Ders bulunamadı").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Bir hata oluştu").build();
        }
    }
}

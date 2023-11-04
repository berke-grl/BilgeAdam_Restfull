package com.example.jakartatest.Controller;

import com.example.jakartatest.Entities.Konu;
import com.example.jakartatest.Repositories.KonuRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/konu")
public class KonuController {

    KonuRepository repository = new KonuRepository();

    @Path("/getAll")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Konu> list;

        try {
            list = repository.getAll();
            if (!list.isEmpty())
                return Response.ok().entity(list).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity("Konu listesi boş").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Bir hata oluştu").build();
        }
    }

    @Path("/getById/{id}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") long id) {
        Konu konu;
        try {
            konu = repository.getById(id);
            if (konu != null)
                return Response.ok().entity(konu).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity(id + " id'li Konu bulunamadı").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Bir hata oluştu").build();
        }
    }

    @Path("/add")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response save(Konu konu){
        try {
            boolean result = repository.save(konu);
            if (result)
                return Response.ok().entity("Konu kaydetme başarılı").build();
            else
                return Response.status(Response.Status.BAD_REQUEST).entity("Konu Kaydedilemedi").build();
        }catch (SQLException e){
            e.printStackTrace();
            return Response.serverError().entity("Bir hata oluştu").build();
        }
    }

    @Path("/deleteById/{id}")
    @DELETE
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") long id){
        try {
            boolean result = repository.deleteById(id);
            if (result)
                return Response.ok().entity(id+" id'li KOnu başarıyla silindi").build();
            else
                return Response.serverError().entity(id+" id'li kayıt silnemedii").build();
        }catch (SQLException e){
            e.printStackTrace();
            return Response.serverError().entity("Bir hata oluştu").build();
        }
    }
}

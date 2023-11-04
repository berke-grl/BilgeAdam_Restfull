package com.example.jakartatest.Controller;

import com.example.jakartatest.Entities.Ogrenci;
import com.example.jakartatest.Repositories.OgrenciRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/ogrenci")
public class OgrenciController {

    private OgrenciRepository repository = new OgrenciRepository();

    @Path("/getAll")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Ogrenci> list;
        try {
            list = repository.getAll();
            if (!list.isEmpty())
                return Response.status(Response.Status.OK).entity(list).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity("Ogrenci listesi boş").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Bir hata oluştu").build();
        }
    }

    @Path("/getById")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getById(@QueryParam("id") long id) {
        Ogrenci ogrenci;
        try {
            ogrenci = repository.getById(id);
            if (ogrenci != null)
                return Response.ok().entity(ogrenci).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity(id + "'id'li Öğrenci bulunamadı").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Bir hata oluştu").build();
        }
    }

    @Path("/add")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response save(Ogrenci ogrenci) {
        try {
            boolean result = repository.save(ogrenci);
            if (result)
                return Response.ok().entity("Öğrenci başarıyla kaydedildi").build();
            else
                return Response.status(Response.Status.BAD_REQUEST).entity("Öğrenci kaydedilemedi").build();
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
                return Response.ok().entity(id + "id''li Öğrenci başarıyla silindi").build();
            else
                return Response.status(Response.Status.BAD_REQUEST).entity("Öğrenci silme işlemi başarısız").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Bir hata oluştu").build();
        }
    }
}

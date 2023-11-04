package com.example.jakartatest.Controller;

import com.example.jakartatest.Entities.Ogretmen;
import com.example.jakartatest.Repositories.OgretmenRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.*;
import java.util.List;

@Path("/ogretmen")
public class OgretmenController {

    private OgretmenRepository repository = new OgretmenRepository();

    @Path("/getAll")
    @GET()
    @Produces(MediaType.APPLICATION_JSON) //list veriyi okumak için bu formatta olmalı
    public Response getAll() {
        List<Ogretmen> list;
        try {
            list = repository.getAll();
            if (!list.isEmpty())
                return Response.ok().entity(list).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity("Öğretmen listesi boş").build();
        } catch (SQLException e) {
            return Response.serverError().entity("Bir Hata Oluştu").build();
        }
    }

    @Path("/getById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON) //list veriyi okumak için bu formatta olmalı
    public Response getById(@PathParam("id") long id) {
        try {
            Ogretmen ogretmen = repository.getById(id);
            if (ogretmen != null) return Response.status(Response.Status.OK).entity(ogretmen).build();
            else return Response.status(Response.Status.NOT_FOUND).entity(id + "'li öğretmen bulunamadı").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Bir hata oluştu").build();
        }
    }

    @Path("/add")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response addOgretmen(Ogretmen ogretmen) {
        try {
            boolean result = repository.save(ogretmen);
            if (result)
                return Response.status(Response.Status.CREATED).entity("Öğretmen başarıyla oluşturuldu").build();
            else return Response.serverError().entity("Öğretmen oluşturulamadı").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Bir hata oluştu").build();
        }
    }

    @Path("deleteById/{id}")
    @DELETE
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response deleteOgretmenById(@PathParam("id") long id) {
        try {
            boolean result = repository.deleteById(id);
            if (result) return Response.status(Response.Status.OK).entity("Öğretmen silindi !").build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity(id + "id'li silinicek öğrenci bulunamadı veya işlem başarısız oldu").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Bir hata oluştu").build();
        }
    }
}
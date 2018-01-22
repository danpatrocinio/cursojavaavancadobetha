package api.biblioteca.satc.rest;


import api.biblioteca.satc.enums.Cargo;
import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Genero;
import api.biblioteca.satc.service.GeneroService;
import api.biblioteca.satc.util.ExceptionResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/cargos")
public class CargoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cargo[] findAll() {
        return Cargo.values();
    }

}

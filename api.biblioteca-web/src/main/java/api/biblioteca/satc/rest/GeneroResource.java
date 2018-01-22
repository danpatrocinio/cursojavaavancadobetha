package api.biblioteca.satc.rest;


import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Genero;
import api.biblioteca.satc.service.GeneroService;
import api.biblioteca.satc.util.ExceptionResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/generos")
public class GeneroResource extends AbstractCrudResource<Genero> {

    @Inject
    private GeneroService service;

    @Override
    public GeneroService getService() {
        return service;
    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Genero genero) {
        try {
            service.inserir(genero);
            return Response.status(Response.Status.CREATED).entity(genero).build();
        } catch (ModelException e) {
            return Response.ok(new ExceptionResponse(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Genero genero) {
        try {
            service.atualizar(genero);
            return Response.status(Response.Status.OK).entity(genero).build();
        } catch (ModelException e) {
            return Response.ok(new ExceptionResponse(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    @DELETE
    @Path("/{idGenero}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idGenero") Long idGenero) {
        try {
            service.remover(idGenero);
            return Response.status(Response.Status.OK).build();
        } catch (ModelException e) {
            return Response.ok(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
        }
    }

}

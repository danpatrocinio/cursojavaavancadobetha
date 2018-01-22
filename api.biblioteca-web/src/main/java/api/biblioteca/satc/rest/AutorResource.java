package api.biblioteca.satc.rest;


import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Autor;
import api.biblioteca.satc.service.AbstractCrudService;
import api.biblioteca.satc.service.AutorService;
import api.biblioteca.satc.util.ExceptionResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/autores")
public class AutorResource extends AbstractCrudResource<Autor> {

    @Inject
    private AutorService service;

    @Override
    protected AbstractCrudService<Autor> getService() {
        return service;
    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Autor autor) {
        try {
            service.inserir(autor);
            return Response.status(Response.Status.CREATED).entity(autor).build();
        } catch (ModelException e) {
            return Response.ok(new ExceptionResponse(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Autor autor) {
        try {
            service.atualizar(autor);
            return Response.status(Response.Status.OK).entity(autor).build();
        } catch (ModelException e) {
            return Response.ok(new ExceptionResponse(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    @DELETE
    @Path("/{idAutor}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idAutor") Long idAutor) {
        try {
            service.remover(idAutor);
            return Response.status(Response.Status.OK).build();
        } catch (ModelException e) {
            return Response.ok(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
        }
    }

}

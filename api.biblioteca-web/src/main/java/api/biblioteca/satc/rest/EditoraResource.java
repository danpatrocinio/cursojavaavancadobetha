package api.biblioteca.satc.rest;


import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Editora;
import api.biblioteca.satc.service.EditoraService;
import api.biblioteca.satc.util.ExceptionResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/editoras")
public class EditoraResource extends AbstractCrudResource<Editora> {

    @Inject
    private EditoraService service;

    @Override
    public EditoraService getService() {
        return service;
    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Editora editora) {
        try {
            service.inserir(editora);
            return Response.status(Response.Status.CREATED).entity(editora).build();
        } catch (ModelException e) {
            return Response.ok(new ExceptionResponse(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Editora editora) {
        try {
            service.atualizar(editora);
            return Response.status(Response.Status.OK).entity(editora).build();
        } catch (ModelException e) {
            return Response.ok(new ExceptionResponse(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    @DELETE
    @Path("/{idEditora}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idEditora") Long idEditora) {
        try {
            service.remover(idEditora);
            return Response.status(Response.Status.OK).build();
        } catch (ModelException e) {
            return Response.ok(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
        }
    }

}

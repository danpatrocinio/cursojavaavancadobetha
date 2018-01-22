package api.biblioteca.satc.rest;


import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Cliente;
import api.biblioteca.satc.model.EntitySelectable;
import api.biblioteca.satc.service.AbstractCrudService;
import api.biblioteca.satc.service.ClienteService;
import api.biblioteca.satc.util.ExceptionResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/clientes")
public class ClienteResource extends AbstractCrudResource<Cliente> {

    @Inject
    private ClienteService service;

    @Override
    protected AbstractCrudService<Cliente> getService() {
        return service;
    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Cliente cliente) {
        try {
            service.inserir(cliente);
            return Response.status(Response.Status.CREATED).entity(cliente).build();
        } catch (ModelException e) {
            return Response.ok(new ExceptionResponse(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/selectable")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EntitySelectable> findAllToSelectable() {
        return service.findAllToSelectableNome();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Cliente cliente) {
        try {
            service.atualizar(cliente);
            return Response.status(Response.Status.OK).entity(cliente).build();
        } catch (ModelException e) {
            return Response.ok(new ExceptionResponse(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    @DELETE
    @Path("/{idCliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idCliente") Long idCliente) {
        try {
            service.remover(idCliente);
            return Response.status(Response.Status.OK).build();
        } catch (ModelException e) {
            return Response.ok(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
        }
    }

}

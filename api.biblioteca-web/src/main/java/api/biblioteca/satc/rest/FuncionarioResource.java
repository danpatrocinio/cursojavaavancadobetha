package api.biblioteca.satc.rest;


import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Funcionario;
import api.biblioteca.satc.model.EntitySelectable;
import api.biblioteca.satc.service.FuncionarioService;
import api.biblioteca.satc.util.ExceptionResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/funcionarios")
public class FuncionarioResource extends AbstractCrudResource<Funcionario> {

    @Inject
    private FuncionarioService service;

    @Override
    public FuncionarioService getService() {
        return service;
    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Funcionario funcionario) {
        try {
            service.inserir(funcionario);
            return Response.status(Response.Status.CREATED).entity(funcionario).build();
        } catch (ModelException e) {
            return Response.ok(new ExceptionResponse(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/selectable")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EntitySelectable> findAllToSelectable() {
        return service.findAllToSelectable();
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(Funcionario funcionario) {
        try {
            service.atualizar(funcionario);
            return Response.status(Response.Status.OK).entity(funcionario).build();
        } catch (ModelException e) {
            return Response.ok(new ExceptionResponse(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    @DELETE
    @Path("/{idFuncionario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idFuncionario") Long idFuncionario) {
        try {
            service.remover(idFuncionario);
            return Response.status(Response.Status.OK).build();
        } catch (ModelException e) {
            return Response.ok(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
        }
    }

}

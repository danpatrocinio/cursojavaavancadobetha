package api.biblioteca.satc.rest;


import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Emprestimo;
import api.biblioteca.satc.service.AbstractCrudService;
import api.biblioteca.satc.service.EmprestimoService;
import api.biblioteca.satc.util.ExceptionResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/emprestimos")
public class EmprestimoResource extends AbstractCrudResource<Emprestimo> {

    @Inject
    private EmprestimoService service;

    @Override
    protected AbstractCrudService<Emprestimo> getService() {
        return service;
    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Emprestimo emprestimo) {
        try {
            service.inserir(emprestimo);
            return Response.status(Response.Status.CREATED).entity(emprestimo).build();
        } catch (ModelException e) {
            return Response.ok(new ExceptionResponse(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Emprestimo emprestimo) {
        try {
            service.atualizar(emprestimo);
            return Response.status(Response.Status.OK).entity(emprestimo).build();
        } catch (ModelException e) {
            return Response.ok(new ExceptionResponse(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    @DELETE
    @Path("/{idEmprestimo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idEmprestimo") Long idEmprestimo) {
        try {
            service.remover(idEmprestimo);
            return Response.status(Response.Status.OK).build();
        } catch (ModelException e) {
            return Response.ok(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/pendentes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Emprestimo> getAllPendentes() {
        return service.findAllPendentes();
    }

}

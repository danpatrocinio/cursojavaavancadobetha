package api.biblioteca.satc.rest;


import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.EntitySelectable;
import api.biblioteca.satc.model.Livro;
import api.biblioteca.satc.service.AbstractCrudService;
import api.biblioteca.satc.service.LivroService;
import api.biblioteca.satc.util.ExceptionResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/livros")
public class LivroResource extends AbstractCrudResource<Livro> {

    @Inject
    private LivroService service;

    @Override
    protected AbstractCrudService<Livro> getService() {
        return service;
    }

    @Override
    public List<Livro> findAll(Integer pageNumber, Integer pageSize, String filterField, String filterValue, String order) {
        return super.findAll(pageNumber, pageSize, filterField, filterValue, order);
    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(Livro livro) {
        try {
            service.inserir(livro);
            return Response.status(Response.Status.CREATED).entity(livro).build();
        } catch (ModelException e) {
            return Response.ok(new ExceptionResponse(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/selectable/disponiveis")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Livro> findAllDisponiveisParaEmprestimoToSelectable() {
        return service.findAllDisponiveisParaEmprestimoToSelectable();
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
    public Response atualizar(Livro livro) {
        try {
            service.atualizar(livro);
            return Response.status(Response.Status.OK).entity(livro).build();
        } catch (ModelException e) {
            return Response.ok(new ExceptionResponse(e.getMessage())).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{idLivro}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response remover(@PathParam("idLivro") Long idLivro) {
        try {
            service.remover(idLivro);
            return Response.status(Response.Status.OK).build();
        } catch (ModelException e) {
            return Response.ok(e.getMessage()).status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/titulo/{titulo}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Livro> findByTitulo(@PathParam("titulo") String titulo) {
        return service.findByTitulo(titulo);
    }

    @GET
    @Path("/autor/{idAutor}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Livro> findByAutor(@PathParam("idAutor") Long idAutor) {
        return service.findByAutor(idAutor);
    }

    @GET
    @Path("/editora/{idEditora}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Livro> findByEditora(@PathParam("idEditora") Long idEditora) {
        return service.findByEditora(idEditora);
    }

    @GET
    @Path("/genero/{idGenero}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Livro> findByGenero(@PathParam("idGenero") Long idGenero) {
        return service.findByGenero(idGenero);
    }

    @GET
    @Path("/isbn/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Livro> findByISBN(@PathParam("isbn") String isbn) {
        return service.findByISBN(isbn);
    }
}

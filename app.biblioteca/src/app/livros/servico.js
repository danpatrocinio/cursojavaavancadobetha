import AbstractCrudService from "../abstract.crud.service";

export default class LivroServico extends AbstractCrudService {

  constructor($http) {
    super($http, 'http://localhost:8080/api.biblioteca-web/rest/livros')
  }

}

LivroServico.$inject = ['$http']

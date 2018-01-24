import AbstractCrudService from "../abstract.crud.service";

export default class AutorServico extends AbstractCrudService {

  constructor($http) {
    super($http, 'http://localhost:8080/api.biblioteca-web/rest/autores')
  }

}

AutorServico.$inject = ['$http']

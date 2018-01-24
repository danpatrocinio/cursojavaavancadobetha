import AbstractCrudService from "../abstract.crud.service";

export default class GeneroServico extends AbstractCrudService {

  constructor($http) {
    super($http, 'http://localhost:8080/api.biblioteca-web/rest/generos')
  }

}

GeneroServico.$inject = ['$http']

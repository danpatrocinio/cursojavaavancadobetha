import AbstractCrudService from "../abstract.crud.service";

export default class EditoraServico extends AbstractCrudService {

  constructor($http) {
    super($http, 'http://localhost:8080/api.biblioteca-web/rest/editoras')
  }

}

EditoraServico.$inject = ['$http']

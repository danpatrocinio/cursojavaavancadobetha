import AbstractCrudService from "../abstract.crud.service";

export default class FuncionarioServico extends AbstractCrudService {

  constructor($http) {
    super($http, 'http://localhost:8080/api.biblioteca-web/rest/funcionarios')
  }

}

FuncionarioServico.$inject = ['$http']

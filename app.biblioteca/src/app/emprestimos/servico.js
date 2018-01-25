import AbstractCrudService from "../abstract.crud.service";

export default class EmprestimoServico extends AbstractCrudService {

  constructor($http) {
    super($http, 'http://localhost:8080/api.biblioteca-web/rest/emprestimos')
  }

}

EmprestimoServico.$inject = ['$http']

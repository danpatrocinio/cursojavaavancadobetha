import AbstractCrudService from "../abstract.crud.service";

export default class EmprestimoServico extends AbstractCrudService {

  constructor($http) {
    super($http, 'http://localhost:8080/api.biblioteca-web/rest/emprestimos')
  }

  loadPendentes() {
      return this._http.get(`${this._url}/pendentes`).then(response => response.data)
  }

}

EmprestimoServico.$inject = ['$http']

import AbstractCrudService from "../abstract.crud.service";

export default class LivroServico extends AbstractCrudService {

  constructor($http) {
    super($http, 'http://localhost:8080/api.biblioteca-web/rest/livros')
  }

  loadDisponiveis() {
    return this._http.get(`${this._url}/selectable/disponiveis`).then(response => response.data)
  }

}

LivroServico.$inject = ['$http']

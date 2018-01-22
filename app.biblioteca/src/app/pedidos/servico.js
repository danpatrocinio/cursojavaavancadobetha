import AbstractCrudService from "../abstract.crud.service";

export default class PedidoServico extends AbstractCrudService {

  constructor($http) {
    super($http, 'http://localhost:8080/Pedidex-web/api/pedidos')
  }

}

PedidoServico.$inject = ['$http']

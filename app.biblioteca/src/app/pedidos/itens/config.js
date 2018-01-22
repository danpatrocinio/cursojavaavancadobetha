import ListController from './list.controller'
import FormController from './form.controller'

import PedidoServico from './servico'

import { itemConfig } from './itens/config'

export const pedidoConfig = (modulo) => {

  modulo.service('PedidoServico', PedidoServico)
        .config(itemConfig(modulo))
  
  return ['$stateProvider', '$urlRouterProvider', 
   ($stateProvider, $urlRouterProvider) => {
    $stateProvider
      .state('pedido', {
        template: require('@views/default.html'),
        url: '/pedidos',
        onEnter: ['$state', function($state) {
          $state.go('pedido.list')
        }]
      })
      .state('pedido.list', {
        template: require('@views/pedidos/list.html'),
        url: '/list',
        controller: ListController,
        controllerAs: 'vm'
      })
      .state('pedido.new', {
        template: require('@views/pedidos/form.html'),
        url: '/new',
        controller: FormController,
        controllerAs: 'vm'
      })
      .state('pedido.edit', {
        template: require('@views/pedidos/form.html'),
        url: '/{id}',
        controller: FormController,
        controllerAs: 'vm'
      });
  }]
}

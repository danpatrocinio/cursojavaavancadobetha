import ListController from './list.controller'
import FormController from './form.controller'

import ProdutoServico from './servico'

export const produtoConfig = (modulo) => {

  modulo.service('ProdutoServico', ProdutoServico)
  
  return ['$stateProvider', '$urlRouterProvider', 
   ($stateProvider, $urlRouterProvider) => {
    $stateProvider
      .state('produto', {
        template: require('@views/default.html'),
        url: '/produtos',
        onEnter: ['$state', function($state) {
          $state.go('produto.list')
        }]
      })
      .state('produto.list', {
        template: require('@views/produtos/list.html'),
        url: '/list',
        controller: ListController,
        controllerAs: 'vm'
      })
      .state('produto.new', {
        template: require('@views/produtos/form.html'),
        url: '/new',
        controller: FormController,
        controllerAs: 'vm'
      })
      .state('produto.edit', {
        template: require('@views/produtos/form.html'),
        url: '/{id}',
        controller: FormController,
        controllerAs: 'vm'
      });
  }]
}

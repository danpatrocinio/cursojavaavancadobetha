import ListController from './list.controller'
import FormController from './form.controller'

import AutorServico from './servico'

export const autorConfig = (modulo) => {

  modulo.service('AutorServico', AutorServico)
  
  return ['$stateProvider', '$urlRouterProvider', 
   ($stateProvider, $urlRouterProvider) => {
    $stateProvider
      .state('autor', {
        template: require('@views/default.html'),
        url: '/autores',
        onEnter: ['$state', function($state) {
          $state.go('autor.list')
        }]
      })
      .state('autor.list', {
        template: require('@views/autores/list.html'),
        url: '/list',
        controller: ListController,
        controllerAs: 'vm'
      })
      .state('autor.new', {
        template: require('@views/autores/form.html'),
        url: '/new',
        controller: FormController,
        controllerAs: 'vm'
      })
      .state('autor.edit', {
        template: require('@views/autores/form.html'),
        url: '/{id}',
        controller: FormController,
        controllerAs: 'vm'
      });
  }]
}

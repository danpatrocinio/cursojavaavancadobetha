import ListController from './list.controller'
import FormController from './form.controller'

import GeneroServico from './servico'

export const generoConfig = (modulo) => {

  modulo.service('GeneroServico', GeneroServico)
  
  return ['$stateProvider', '$urlRouterProvider', 
   ($stateProvider, $urlRouterProvider) => {
    $stateProvider
      .state('genero', {
        template: require('@views/default.html'),
        url: '/generos',
        onEnter: ['$state', function($state) {
          $state.go('genero.list')
        }]
      })
      .state('genero.list', {
        template: require('@views/generos/list.html'),
        url: '/list',
        controller: ListController,
        controllerAs: 'vm'
      })
      .state('genero.new', {
        template: require('@views/generos/form.html'),
        url: '/new',
        controller: FormController,
        controllerAs: 'vm'
      })
      .state('genero.edit', {
        template: require('@views/generos/form.html'),
        url: '/{id}',
        controller: FormController,
        controllerAs: 'vm'
      });
  }]
}

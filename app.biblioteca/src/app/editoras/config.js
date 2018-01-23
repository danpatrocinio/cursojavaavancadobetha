import ListController from './list.controller'
import FormController from './form.controller'

import EditoraServico from './servico'

export const editoraConfig = (modulo) => {

  modulo.service('EditoraServico', EditoraServico)
  
  return ['$stateProvider', '$urlRouterProvider', 
   ($stateProvider, $urlRouterProvider) => {
    $stateProvider
      .state('editora', {
        template: require('@views/default.html'),
        url: '/editoras',
        onEnter: ['$state', function($state) {
          $state.go('editora.list')
        }]
      })
      .state('editora.list', {
        template: require('@views/editoras/list.html'),
        url: '/list',
        controller: ListController,
        controllerAs: 'vm'
      })
      .state('editora.new', {
        template: require('@views/editoras/form.html'),
        url: '/new',
        controller: FormController,
        controllerAs: 'vm'
      })
      .state('editora.edit', {
        template: require('@views/editoras/form.html'),
        url: '/{id}',
        controller: FormController,
        controllerAs: 'vm'
      });
  }]
}

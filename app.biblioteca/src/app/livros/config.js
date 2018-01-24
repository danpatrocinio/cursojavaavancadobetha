import ListController from './list.controller'
import FormController from './form.controller'

import LivroServico from './servico'

export const livroConfig = (modulo) => {

  modulo.service('LivroServico', LivroServico)
  
  return ['$stateProvider', '$urlRouterProvider', 
   ($stateProvider, $urlRouterProvider) => {
    $stateProvider
      .state('livro', {
        template: require('@views/default.html'),
        url: '/livros',
        onEnter: ['$state', function($state) {
          $state.go('livro.list')
        }]
      })
      .state('livro.list', {
        template: require('@views/livros/list.html'),
        url: '/list',
        controller: ListController,
        controllerAs: 'vm'
      })
      .state('livro.new', {
        template: require('@views/livros/form.html'),
        url: '/new',
        controller: FormController,
        controllerAs: 'vm'
      })
      .state('livro.edit', {
        template: require('@views/livros/form.html'),
        url: '/{id}',
        controller: FormController,
        controllerAs: 'vm'
      });
  }]
}

import ListController from './list.controller'
import FormController from './form.controller'

import EmprestimoServico from './servico'

export const emprestimoConfig = (modulo) => {

  modulo.service('EmprestimoServico', EmprestimoServico)
  
  return ['$stateProvider', '$urlRouterProvider', 
   ($stateProvider, $urlRouterProvider) => {
    $stateProvider
      .state('emprestimo', {
        template: require('@views/default.html'),
        url: '/emprestimos',
        onEnter: ['$state', function($state) {
          $state.go('emprestimo.list')
        }]
      })
      .state('emprestimo.list', {
        template: require('@views/emprestimos/list.html'),
        url: '/list',
        controller: ListController,
        controllerAs: 'vm'
      })
      .state('emprestimo.new', {
        template: require('@views/emprestimos/form.html'),
        url: '/new',
        controller: FormController,
        controllerAs: 'vm'
      })
      .state('emprestimo.edit', {
        template: require('@views/emprestimos/form.html'),
        url: '/{id}',
        controller: FormController,
        controllerAs: 'vm'
      });
  }]
}

import ListController from './list.controller'
import FormController from './form.controller'

import FuncionarioServico from './servico'

export const funcionarioConfig = (modulo) => {

  modulo.service('FuncionarioServico', FuncionarioServico)
  
  return ['$stateProvider', '$urlRouterProvider', 
   ($stateProvider, $urlRouterProvider) => {
    $stateProvider
      .state('funcionario', {
        template: require('@views/default.html'),
        url: '/funcionarios',
        onEnter: ['$state', function($state) {
          $state.go('funcionario.list')
        }]
      })
      .state('funcionario.list', {
        template: require('@views/funcionarios/list.html'),
        url: '/list',
        controller: ListController,
        controllerAs: 'vm'
      })
      .state('funcionario.new', {
        template: require('@views/funcionarios/form.html'),
        url: '/new',
        controller: FormController,
        controllerAs: 'vm'
      })
      .state('funcionario.edit', {
        template: require('@views/funcionarios/form.html'),
        url: '/{id}',
        controller: FormController,
        controllerAs: 'vm'
      });
  }]
}

import * as angular from 'angular'

import { default as uiRouter } from '@uirouter/angularjs'
import { default as uiNotification } from 'angular-ui-notification'
import { default as inputMasks } from 'angular-input-masks'
import { default as flatpickr } from 'ng-flatpickr'
import { mainConfig } from './main/config'
import { clienteConfig } from './clientes/config'
<<<<<<< HEAD
import { funcionarioConfig } from './funcionarios/config'
=======
import { livroConfig } from './livros/config'
import { autorConfig } from './autores/config'
import { editoraConfig } from './editoras/config'
>>>>>>> 1cfc6a8752cf9e924dd0ebc9ea5c3829068e0dda
import { produtoConfig } from './produtos/config'
import { pedidoConfig } from './pedidos/config'

require('angular-i18n/angular-locale_pt-br.js')

export const appModule = 'app'

var modulo = angular.module(appModule, [uiRouter, uiNotification, inputMasks, flatpickr.name])

modulo.config(mainConfig(modulo))
      .config(funcionarioConfig(modulo))
      .config(clienteConfig(modulo))
      .config(autorConfig(modulo))
      .config(editoraConfig(modulo))
      .config(livroConfig(modulo))
      .config(produtoConfig(modulo))
      .config(pedidoConfig(modulo))

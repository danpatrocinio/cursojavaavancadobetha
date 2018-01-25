import * as angular from 'angular'

import { default as uiRouter } from '@uirouter/angularjs'
import { default as uiNotification } from 'angular-ui-notification'
import { default as inputMasks } from 'angular-input-masks'
import { default as flatpickr } from 'ng-flatpickr'
import { mainConfig } from './main/config'
import { funcionarioConfig } from './funcionarios/config'
import { clienteConfig } from './clientes/config'
import { autorConfig } from './autores/config'
import { editoraConfig } from './editoras/config'
import { generoConfig } from './generos/config'
import { livroConfig } from './livros/config'
import { emprestimoConfig } from './emprestimos/config'
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
      .config(generoConfig(modulo))
      .config(livroConfig(modulo))
      .config(emprestimoConfig(modulo))
      .config(produtoConfig(modulo))
      .config(pedidoConfig(modulo))

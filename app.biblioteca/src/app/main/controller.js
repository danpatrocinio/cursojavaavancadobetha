export default class MainController {
     constructor(EmprestimoServico, Notification) {
        this.emprestimosPendentes = []
        this._emprestimoService = EmprestimoServico
        this._notify = Notification
        this.loadEmprestimosPendentes()
    }

    loadEmprestimosPendentes() {
         this._emprestimoService.loadPendentes()
          .then(data => {
              this.emprestimosPendentes = data
          })
          .catch(error => {
              console.log(error)
          })
    }
}

MainController.$inject = ['EmprestimoServico', 'Notification']

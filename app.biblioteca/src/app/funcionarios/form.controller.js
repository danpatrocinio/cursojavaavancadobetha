export default class FormController {

    constructor($stateParams, $state, FuncionarioServico, Notification) {
        this.record = {}
        this.title = 'Novo funcionário'
        this._service = FuncionarioServico
        if ($stateParams.id) {
            this.title = 'Editando funcionário'
            this._service.findById($stateParams.id)
                .then(data => {
                    this.record = data
                })
        }
        this._state = $state
        this._notify = Notification
    }

    save() {
        this._service.save(this.record)
            .then(resp => {
                this._notify.success('Funcionário salvo com sucesso!')
                this._state.go('funcionario.list')
            }).catch(erro => {
                this._notify.error('Erro ao salvar o registro! ' + (!!erro.data && !!erro.data.message ? erro.data.message : ''))
            })
    }
}

FormController.$inject = ['$stateParams', '$state', 'FuncionarioServico', 'Notification']

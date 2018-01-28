export default class FormController {

    constructor($stateParams, $state, ClienteServico, Notification) {
        this.record = {}
        this.title = 'Novo cliente'
        this._service = ClienteServico
        if ($stateParams.id) {
            this.title = 'Editando cliente'
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
                this._notify.success('Cliente salvo com sucesso!')
                this._state.go('cliente.list')
            }).catch(erro => {
                this._notify.error('Erro ao salvar o registro! ' + (!!erro.data && !!erro.data.message ? erro.data.message : ''))
            })
    }
}

FormController.$inject = ['$stateParams', '$state', 'ClienteServico', 'Notification']

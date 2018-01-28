export default class FormController {

    constructor($stateParams, $state, GeneroServico, Notification) {
        this.record = {}
        this.title = 'Novo gênero'
        this._service = GeneroServico
        if ($stateParams.id) {
            this.title = 'Editando gênero'
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
                this._notify.success('Gênero salvo com sucesso!')
                this._state.go('genero.list')
            }).catch(erro => {
                this._notify.error('Erro ao salvar o registro! ' + (!!erro.data && !!erro.data.message ? erro.data.message : ''))
            })
    }
}

FormController.$inject = ['$stateParams', '$state', 'GeneroServico', 'Notification']

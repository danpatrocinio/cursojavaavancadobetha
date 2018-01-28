export default class FormController {

    constructor($stateParams, $state, AutorServico, Notification) {
        this.record = {}
        this.title = 'Novo autor'
        this._service = AutorServico
        if ($stateParams.id) {
            this.title = 'Editando autor'
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
                this._notify.success('Autor salvo com sucesso!')
                this._state.go('autor.list')
            }).catch(erro => {
                this._notify.error('Erro ao salvar o registro! ' + (!!erro.data && !!erro.data.message ? erro.data.message : ''))
            })
    }
}

FormController.$inject = ['$stateParams', '$state', 'AutorServico', 'Notification']

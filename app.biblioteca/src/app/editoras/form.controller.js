export default class FormController {

    constructor($stateParams, $state, EditoraServico, Notification) {
        this.record = {}
        this.title = 'Nova editora'
        this._service = EditoraServico
        if ($stateParams.id) {
            this.title = 'Editando editora'
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
                this._notify.success('Editora salvo com sucesso!')
                this._state.go('editora.list')
            }).catch(erro => {
                this._notify.error('Erro ao salvar o registro! ' + (!!erro.data && !!erro.data.message ? erro.data.message : ''))
            })
    }
}

FormController.$inject = ['$stateParams', '$state', 'EditoraServico', 'Notification']

export default class FormController {

    constructor($stateParams, $state, LivroServico, AutorServico, EditoraServico, GeneroServico, Notification) {
        this.record = {}
        this.title = 'Novo Livro'
        this._service = LivroServico
        this._autorService = AutorServico
        this._editoraService = EditoraServico
        this._generoService = GeneroServico
        this.loadChilds()
        
        if ($stateParams.id) {
            this.title = 'Editando livro'
            this._service.findById($stateParams.id)
                .then(data => {
                    this.record = data
            })
        }

        this._state = $state
        this._notify = Notification
    }

    loadChilds() {
        this._autorService.findAll('nome', '', 'nome').then(data => { this.autores = data }).catch(error => { console.log(error) })
        this._editoraService.findAll('nome', '', 'nome').then(data => { this.editoras = data  }).catch(error => { console.log(error) })
        this._generoService.findAll('descricao', '', 'descricao').then(data => { this.generos = data  }).catch(error => { console.log(error) })
    }

    save() {
        this._service.save(this.record)
            .then(resp => {
                this._notify.success('Livro salvo com sucesso!')
                this._state.go('livro.list')
            }).catch(erro => {
                this._notify.error('Erro ao salvar o registro! ' + (!!erro.data && !!erro.data.message ? erro.data.message : ''))
            })
    }
}

FormController.$inject = ['$stateParams', '$state', 'LivroServico', 'AutorServico', 'EditoraServico', 'GeneroServico', 'Notification']

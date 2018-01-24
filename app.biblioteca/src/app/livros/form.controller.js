export default class FormController {

    constructor($stateParams, $state, LivroServico, AutorServico, EditoraServico, GeneroServico, Notification) {
        this.record = {}
        this.title = 'Adicionando registro'
        this._service = LivroServico
        if ($stateParams.id) {
            this.title = 'Editando registro'
            AutorServico.findAll('nome', '', 'nome').then(data => { this.autores = data }).catch(error => { console.log(error) })
            .then(GeneroServico.findAll('descricao', '', 'descricao').then(data => { this.generos = data  }).catch(error => { console.log(error) }))
            .then(EditoraServico.findAll('nome', '', 'nome').then(data => { this.editoras = data  }).catch(error => { console.log(error) }))
            .then(
                this._service.findById($stateParams.id)
                .then(data => {
                    this.record = data
            }))
        } else {
            AutorServico.findAll('nome', '', 'nome').then(data => { this.autores = data }).catch(error => { console.log(error) })
            GeneroServico.findAll('descricao', '', 'descricao').then(data => { this.generos = data  }).catch(error => { console.log(error) })
            EditoraServico.findAll('nome', '', 'nome').then(data => { this.editoras = data  }).catch(error => { console.log(error) })
        }

        this._state = $state
        this._notify = Notification
    }

    save() {
        this._service.save(this.record)
            .then(resp => {
                this._notify.success('Registro salvo com sucesso!')
                this._state.go('livro.list')
            }).catch(erro => {
                console.log(erro.message)
                this._notify.error('Erro ao salvar o registro!')
            })
    }
}

FormController.$inject = ['$stateParams', '$state', 'LivroServico', 'AutorServico', 'EditoraServico', 'GeneroServico', 'Notification']

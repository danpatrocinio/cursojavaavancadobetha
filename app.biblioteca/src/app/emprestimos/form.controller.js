export default class FormController {

    constructor($stateParams, $state, EmprestimoServico, LivroServico, Notification) {
        this.record = {}
        this.title = 'Adicionando registro'
        this._service = EmprestimoServico
        this._livroService = LivroServico
        this.loadChilds()
        
        if ($stateParams.id) {
            this.title = 'Editando registro'
            this._service.findById($stateParams.id)
                .then(data => {
                    this.record = data
            })
        }

        this._state = $state
        this._notify = Notification
    }

    loadChilds() {
        this._livroService.findAll('titulo', '', 'titulo').then(data => { this.livros = data }).catch(error => { console.log(error) })
    }

    save() {
        this._service.save(this.record)
            .then(resp => {
                this._notify.success('Registro salvo com sucesso!')
                this._state.go('emprestimo.list')
            }).catch(erro => {
                console.log(erro.message)
                this._notify.error('Erro ao salvar o registro!')
            })
    }
}

FormController.$inject = ['$stateParams', '$state', 'EmprestimoServico', 'LivroServico', 'Notification']

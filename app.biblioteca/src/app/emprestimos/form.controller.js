export default class FormController {

    constructor($stateParams, $state, EmprestimoServico, ClienteServico, FuncionarioServico, LivroServico, Notification) {
        this.record = {}
        this.title = 'Adicionando registro'
        this._service = EmprestimoServico
        this._livroService = LivroServico
        this._clienteService = ClienteServico
        this._funcionarioService = FuncionarioServico
        this.loadChilds()
        
        if ($stateParams.id) {
            this.title = 'Devolução do empréstimo'
            this._service.findById($stateParams.id)
                .then(data => {
                    this.record = data
                    this.isDevolucao = !this.record.dataDevolucao
                    if (this.isDevolucao) {
                        var hoje = new Date()
                        var dia = '0'+(hoje.getMonth()+1)
                        this.record.dataDevolucao = hoje.getFullYear() + '-' + dia.substring(dia.Length-2, 2) + '-' + hoje.getDate()
                    }
            })
        }

        this._state = $state
        this._notify = Notification
    }

    loadChilds() {
        this._clienteService.findAll('nome', '', 'nome').then(data => { this.clientes = data }).catch(error => { console.log(error) })
        this._funcionarioService.findAll('nome', '', 'nome').then(data => { this.funcionarios = data }).catch(error => { console.log(error) })
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

FormController.$inject = ['$stateParams', '$state', 'EmprestimoServico', 'ClienteServico', 'FuncionarioServico', 'LivroServico', 'Notification']

export default class FormController {

    constructor($stateParams, $state, EmprestimoServico, ClienteServico, FuncionarioServico, LivroServico, Notification) {
        this.record = {}
        this.record.livros = []
        this.title = 'Novo empréstimo'
        this._service = EmprestimoServico
        this._livroService = LivroServico
        this._clienteService = ClienteServico
        this._funcionarioService = FuncionarioServico
        this.addLivro = this._addLivro
        this.removerLivro = this._removerLivro
        this.loadChilds()
        
        if ($stateParams.id) {
            this.title = 'Devolução do empréstimo'
            this._service.findById($stateParams.id)
                .then(data => {
                    this.record = data
                    this.isDevolucao = !this.record.dataDevolucao
                    if (this.isDevolucao) {
                        this.record.dataDevolucao = this.getHoje()
                    }
            })
        } else {
            this.loadLivrosDisponiveis()
            this.record.dataEmprestimo = this.getHoje()
        }

        this._state = $state
        this._notify = Notification
    }

    loadChilds() {
        this._clienteService.findAll('nome', '', 'nome').then(data => { this.clientes = data }).catch(error => { console.log(error) })
        this._funcionarioService.findAll('nome', '', 'nome').then(data => { this.funcionarios = data }).catch(error => { console.log(error) })
        this._livroService.findAll('titulo', '', 'titulo').then(data => { this.livros = data }).catch(error => { console.log(error) })
    }

    loadLivrosDisponiveis() {
        this._livroService.loadDisponiveis()
          .then(data => {
              this.livrosDisponiveis = data
          })
          .catch(error => {
              console.log(error)
          })
    }

    
    _addLivro(idLivro) {
        var added = false;
        this.record.livros.forEach(livro => {
            if (livro.id === idLivro) {
                this._notify.error('Livro já adicionado!')
                added = true
            }
        });
        if (!added) {
            this._livroService.findById(idLivro)
            .then(data => { 
                this.record.livros.push(data)
            }).catch(error => { console.log(error) })
        }
        this.calcPrevisaoDevolucao()
    }

    _removerLivro(livro){
        this.record.livros.splice(this.record.livros.indexOf(livro), 1)
        this.calcPrevisaoDevolucao()
    }
    
    calcPrevisaoDevolucao() {
        if (!this.record.livros) { this.record.dataPrevisaoDevolucao.delete;  return; }
        var prazo = this.record.livros.length * 10;
        this.record.dataPrevisaoDevolucao = new Date()
        this.record.dataPrevisaoDevolucao.setDate(this.record.dataPrevisaoDevolucao.getDate() + prazo)
    }

    getHoje() {
        var hoje = new Date()
        var dia = '0'+(hoje.getMonth()+1)
        return hoje.getFullYear() + '-' + dia.substring(dia.Length-2, 2) + '-' + hoje.getDate()
    }

    save() {
        this._service.save(this.record)
            .then(resp => {
                this._notify.success('Empréstimo salvo com sucesso!')
                this._state.go('emprestimo.list')
            }).catch(erro => {
                this._notify.error('Erro ao salvar o registro! ' + (!!erro.data && !!erro.data.message ? erro.data.message : ''))
            })
    }
}

FormController.$inject = ['$stateParams', '$state', 'EmprestimoServico', 'ClienteServico', 'FuncionarioServico', 'LivroServico', 'Notification']

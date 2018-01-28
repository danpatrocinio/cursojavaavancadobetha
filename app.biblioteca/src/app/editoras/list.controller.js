import swal from 'sweetalert2'

export default class ListController {

    constructor(EditoraServico, Notification) {
        this.filterField = 'nome'
        this.filterValue = ''
        this.order = 'nome'
        this.records = []
        this._service = EditoraServico
        this.title = 'Editoras'
        this._notify = Notification
        this.load()
    }

    load() {
        this._service.findAll(this.filterField, this.filterValue, this.order)
          .then(data => {
              this.records = data
          })
          .catch(error => {
              console.log(error)
          })
    }

    excluir(id) {
        swal({
            title: 'Remover registro',
            text: 'Deseja realmente remover a editora',
            type: 'warning',
            showConfirmButton: true,
            showCancelButton: true,
            confirmButtonText: 'Claro!',
            cancelButtonText: 'Não obrigado'
        }).then(resp => {
            return resp.value ? 
              this._service.remove(id) :
              Promise.reject({type: 'warning', message: 'Operação cancelada!!!'})
        }).then(response => {
            this.load()
            this._notify.success('Editora excluída com sucesso')
        }).catch(erro => {
            this._notify({message: erro.message || 'Problemas ao excluir a editora'}, erro.type || 'error')
        }) 
    }
}

ListController.$inject = ['EditoraServico', 'Notification']

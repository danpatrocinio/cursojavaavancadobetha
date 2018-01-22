package api.biblioteca.satc.service;

import api.biblioteca.satc.dao.GenericDao;
import api.biblioteca.satc.model.Entity;
import api.biblioteca.satc.model.EntitySelectable;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.validation.Valid;
import java.util.List;

public abstract class AbstractCrudService<T extends Entity> {
    public List<T> findAll(Integer pageSize, Integer pageNumber, String filterField, String filterData, String order) {
        return getDao().findAll(pageSize, pageNumber, filterField, filterData, order);
    }

    public List<T> findAllOver(String filterField, String filterData, String order) {
        return getDao().findAll(filterField, filterData, order);
    }

    public T findById(Long id) {
        return getDao().findById(id);
    }

    public List<EntitySelectable> findAllToSelectableNome() {
        return getDao().findAllToSelectableNome();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public T insert(@Valid T bean) {
        return getDao().inserir(bean);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public T update(@Valid T bean) {
        return getDao().atualizar(bean);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remove(Long id) {
        getDao().remover(id);
    }

    protected abstract GenericDao<T> getDao();
}

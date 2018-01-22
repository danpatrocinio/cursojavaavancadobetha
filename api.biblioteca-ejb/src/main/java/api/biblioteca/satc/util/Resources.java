package api.biblioteca.satc.util;

import api.biblioteca.satc.dao.GenericDao;
import api.biblioteca.satc.model.Entity;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

public class Resources {

    @Produces
    @PersistenceContext
    private EntityManager em;

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    @Produces
    public <T extends Entity> GenericDao<T> produceDao(InjectionPoint injectionPoint, EntityManager em) {
        Type[] args = ((ParameterizedType)injectionPoint.getType()).getActualTypeArguments();
        if (args.length == 0)
            throw new IllegalArgumentException("O GenericDao precisa de um tipo");
        Class<T> type = (Class<T>) args[0];
        return new GenericDao(em, type);
    }
}

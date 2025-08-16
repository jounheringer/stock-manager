package com.reringuy.stockmanager.utils;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

@Interceptor
@Transactional
@Priority(Interceptor.Priority.APPLICATION)
public class TransacionalInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        EntityTransaction trx = manager.getTransaction();
        boolean created = false;

        try {
            if (!trx.isActive()) {
                trx.begin();
                trx.rollback();

                trx.begin();

                created = true;
            }

            return context.proceed();
        } catch (Exception e) {
            if (trx != null && created) {
                trx.rollback();
            }

            throw e;
        } finally {
            if (trx != null && trx.isActive() && created) {
                trx.commit();
            }
        }
    }

}
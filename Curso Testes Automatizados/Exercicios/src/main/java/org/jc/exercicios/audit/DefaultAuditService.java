package org.jc.exercicios.audit;

import java.lang.reflect.Method;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Business logic for Audit Facility
 *
 * @author Libor Krzyzanek
 */
@Named
@Stateless
class DefaultAuditService implements AuditService {

    /**
     * Do the audit logic
     *
     * @param method
     * @param parameters method's parameters
     */
    @Override
    public void auditMethod(Method method, Object[] parameters) {
        //TODO: implementar a lógica de auditoria.
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jc.exercicios.audit;

import java.lang.reflect.Method;

/**
 *
 * @author jean
 */
public interface AuditService {

    void auditMethod(Method method, Object[] parameters);
    
}

package com.datacart.model.dao;

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Dmitriy Sinenkiy
 * @since 1.0
 */
@Target( { ElementType.METHOD, ElementType.TYPE } )
@Retention( RetentionPolicy.RUNTIME )
@Inherited
@Documented
@Transactional( value = "commonTxManager", readOnly = true )
public @interface TenantReadOnlyTransactional {
}

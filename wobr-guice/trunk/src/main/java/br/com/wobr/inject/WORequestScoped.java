package br.com.wobr.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.ScopeAnnotation;

/**
 * Apply this to implementation classes when you want one instance per request.
 * 
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
@Target( { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@ScopeAnnotation
public @interface WORequestScoped
{
}

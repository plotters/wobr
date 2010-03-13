// $LastChangedRevision: 5810 $ DO NOT EDIT.  Make changes to Instrucao.java instead.
package br.com.wobr.boleto.model;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _Instrucao extends br.com.wobr.boleto.model.AbstractInformacao {
	public static final String ENTITY_NAME = "Instrucao";

	// Attributes
	public static final String TIPO_KEY = "tipo";
	public static final String VALOR_KEY = "valor";

	// Relationships

  private static Logger LOG = Logger.getLogger(_Instrucao.class);

  public Instrucao localInstanceIn(EOEditingContext editingContext) {
    Instrucao localInstance = (Instrucao)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


  public static Instrucao createInstrucao(EOEditingContext editingContext, String valor
) {
    Instrucao eo = (Instrucao) EOUtilities.createAndInsertInstance(editingContext, _Instrucao.ENTITY_NAME);    
		eo.setValor(valor);
    return eo;
  }

  public static NSArray<Instrucao> fetchAllInstrucaos(EOEditingContext editingContext) {
    return _Instrucao.fetchAllInstrucaos(editingContext, null);
  }

  public static NSArray<Instrucao> fetchAllInstrucaos(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Instrucao.fetchInstrucaos(editingContext, null, sortOrderings);
  }

  public static NSArray<Instrucao> fetchInstrucaos(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Instrucao.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Instrucao> eoObjects = (NSArray<Instrucao>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Instrucao fetchInstrucao(EOEditingContext editingContext, String keyName, Object value) {
    return _Instrucao.fetchInstrucao(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Instrucao fetchInstrucao(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Instrucao> eoObjects = _Instrucao.fetchInstrucaos(editingContext, qualifier, null);
    Instrucao eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Instrucao)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Instrucao that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Instrucao fetchRequiredInstrucao(EOEditingContext editingContext, String keyName, Object value) {
    return _Instrucao.fetchRequiredInstrucao(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Instrucao fetchRequiredInstrucao(EOEditingContext editingContext, EOQualifier qualifier) {
    Instrucao eoObject = _Instrucao.fetchInstrucao(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Instrucao that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Instrucao localInstanceIn(EOEditingContext editingContext, Instrucao eo) {
    Instrucao localInstance = (eo == null) ? null : (Instrucao)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}

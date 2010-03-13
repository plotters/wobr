// $LastChangedRevision: 5810 $ DO NOT EDIT.  Make changes to LocalPagamento.java instead.
package br.com.wobr.boleto.model;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _LocalPagamento extends br.com.wobr.boleto.model.AbstractInformacao {
	public static final String ENTITY_NAME = "LocalPagamento";

	// Attributes
	public static final String TIPO_KEY = "tipo";
	public static final String VALOR_KEY = "valor";

	// Relationships

  private static Logger LOG = Logger.getLogger(_LocalPagamento.class);

  public LocalPagamento localInstanceIn(EOEditingContext editingContext) {
    LocalPagamento localInstance = (LocalPagamento)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


  public static LocalPagamento createLocalPagamento(EOEditingContext editingContext, String valor
) {
    LocalPagamento eo = (LocalPagamento) EOUtilities.createAndInsertInstance(editingContext, _LocalPagamento.ENTITY_NAME);    
		eo.setValor(valor);
    return eo;
  }

  public static NSArray<LocalPagamento> fetchAllLocalPagamentos(EOEditingContext editingContext) {
    return _LocalPagamento.fetchAllLocalPagamentos(editingContext, null);
  }

  public static NSArray<LocalPagamento> fetchAllLocalPagamentos(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _LocalPagamento.fetchLocalPagamentos(editingContext, null, sortOrderings);
  }

  public static NSArray<LocalPagamento> fetchLocalPagamentos(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_LocalPagamento.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<LocalPagamento> eoObjects = (NSArray<LocalPagamento>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static LocalPagamento fetchLocalPagamento(EOEditingContext editingContext, String keyName, Object value) {
    return _LocalPagamento.fetchLocalPagamento(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static LocalPagamento fetchLocalPagamento(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<LocalPagamento> eoObjects = _LocalPagamento.fetchLocalPagamentos(editingContext, qualifier, null);
    LocalPagamento eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (LocalPagamento)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one LocalPagamento that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static LocalPagamento fetchRequiredLocalPagamento(EOEditingContext editingContext, String keyName, Object value) {
    return _LocalPagamento.fetchRequiredLocalPagamento(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static LocalPagamento fetchRequiredLocalPagamento(EOEditingContext editingContext, EOQualifier qualifier) {
    LocalPagamento eoObject = _LocalPagamento.fetchLocalPagamento(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no LocalPagamento that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static LocalPagamento localInstanceIn(EOEditingContext editingContext, LocalPagamento eo) {
    LocalPagamento localInstance = (eo == null) ? null : (LocalPagamento)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}

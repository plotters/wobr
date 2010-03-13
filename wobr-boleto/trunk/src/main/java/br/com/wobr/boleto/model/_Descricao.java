// $LastChangedRevision: 5810 $ DO NOT EDIT.  Make changes to Descricao.java instead.
package br.com.wobr.boleto.model;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _Descricao extends br.com.wobr.boleto.model.AbstractInformacao {
	public static final String ENTITY_NAME = "Descricao";

	// Attributes
	public static final String TIPO_KEY = "tipo";
	public static final String VALOR_KEY = "valor";

	// Relationships

  private static Logger LOG = Logger.getLogger(_Descricao.class);

  public Descricao localInstanceIn(EOEditingContext editingContext) {
    Descricao localInstance = (Descricao)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }


  public static Descricao createDescricao(EOEditingContext editingContext, String valor
) {
    Descricao eo = (Descricao) EOUtilities.createAndInsertInstance(editingContext, _Descricao.ENTITY_NAME);    
		eo.setValor(valor);
    return eo;
  }

  public static NSArray<Descricao> fetchAllDescricaos(EOEditingContext editingContext) {
    return _Descricao.fetchAllDescricaos(editingContext, null);
  }

  public static NSArray<Descricao> fetchAllDescricaos(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Descricao.fetchDescricaos(editingContext, null, sortOrderings);
  }

  public static NSArray<Descricao> fetchDescricaos(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Descricao.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Descricao> eoObjects = (NSArray<Descricao>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Descricao fetchDescricao(EOEditingContext editingContext, String keyName, Object value) {
    return _Descricao.fetchDescricao(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Descricao fetchDescricao(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Descricao> eoObjects = _Descricao.fetchDescricaos(editingContext, qualifier, null);
    Descricao eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Descricao)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Descricao that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Descricao fetchRequiredDescricao(EOEditingContext editingContext, String keyName, Object value) {
    return _Descricao.fetchRequiredDescricao(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Descricao fetchRequiredDescricao(EOEditingContext editingContext, EOQualifier qualifier) {
    Descricao eoObject = _Descricao.fetchDescricao(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Descricao that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Descricao localInstanceIn(EOEditingContext editingContext, Descricao eo) {
    Descricao localInstance = (eo == null) ? null : (Descricao)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}

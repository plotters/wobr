// $LastChangedRevision: 5810 $ DO NOT EDIT.  Make changes to Sacado.java instead.
package br.com.wobr.boleto.model;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _Sacado extends  EOGenericRecord {
	public static final String ENTITY_NAME = "Sacado";

	// Attributes
	public static final String BAIRRO_KEY = "bairro";
	public static final String CEP_KEY = "cep";
	public static final String CIDADE_KEY = "cidade";
	public static final String CPF_KEY = "cpf";
	public static final String ENDERECO_KEY = "endereco";
	public static final String NOME_KEY = "nome";
	public static final String UF_KEY = "uf";

	// Relationships

  private static Logger LOG = Logger.getLogger(_Sacado.class);

  public Sacado localInstanceIn(EOEditingContext editingContext) {
    Sacado localInstance = (Sacado)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String bairro() {
    return (String) storedValueForKey("bairro");
  }

  public void setBairro(String value) {
    if (_Sacado.LOG.isDebugEnabled()) {
    	_Sacado.LOG.debug( "updating bairro from " + bairro() + " to " + value);
    }
    takeStoredValueForKey(value, "bairro");
  }

  public String cep() {
    return (String) storedValueForKey("cep");
  }

  public void setCep(String value) {
    if (_Sacado.LOG.isDebugEnabled()) {
    	_Sacado.LOG.debug( "updating cep from " + cep() + " to " + value);
    }
    takeStoredValueForKey(value, "cep");
  }

  public String cidade() {
    return (String) storedValueForKey("cidade");
  }

  public void setCidade(String value) {
    if (_Sacado.LOG.isDebugEnabled()) {
    	_Sacado.LOG.debug( "updating cidade from " + cidade() + " to " + value);
    }
    takeStoredValueForKey(value, "cidade");
  }

  public String cpf() {
    return (String) storedValueForKey("cpf");
  }

  public void setCpf(String value) {
    if (_Sacado.LOG.isDebugEnabled()) {
    	_Sacado.LOG.debug( "updating cpf from " + cpf() + " to " + value);
    }
    takeStoredValueForKey(value, "cpf");
  }

  public String endereco() {
    return (String) storedValueForKey("endereco");
  }

  public void setEndereco(String value) {
    if (_Sacado.LOG.isDebugEnabled()) {
    	_Sacado.LOG.debug( "updating endereco from " + endereco() + " to " + value);
    }
    takeStoredValueForKey(value, "endereco");
  }

  public String nome() {
    return (String) storedValueForKey("nome");
  }

  public void setNome(String value) {
    if (_Sacado.LOG.isDebugEnabled()) {
    	_Sacado.LOG.debug( "updating nome from " + nome() + " to " + value);
    }
    takeStoredValueForKey(value, "nome");
  }

  public String uf() {
    return (String) storedValueForKey("uf");
  }

  public void setUf(String value) {
    if (_Sacado.LOG.isDebugEnabled()) {
    	_Sacado.LOG.debug( "updating uf from " + uf() + " to " + value);
    }
    takeStoredValueForKey(value, "uf");
  }


  public static Sacado createSacado(EOEditingContext editingContext) {
    Sacado eo = (Sacado) EOUtilities.createAndInsertInstance(editingContext, _Sacado.ENTITY_NAME);    
    return eo;
  }

  public static NSArray<Sacado> fetchAllSacados(EOEditingContext editingContext) {
    return _Sacado.fetchAllSacados(editingContext, null);
  }

  public static NSArray<Sacado> fetchAllSacados(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Sacado.fetchSacados(editingContext, null, sortOrderings);
  }

  public static NSArray<Sacado> fetchSacados(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Sacado.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Sacado> eoObjects = (NSArray<Sacado>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Sacado fetchSacado(EOEditingContext editingContext, String keyName, Object value) {
    return _Sacado.fetchSacado(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Sacado fetchSacado(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Sacado> eoObjects = _Sacado.fetchSacados(editingContext, qualifier, null);
    Sacado eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Sacado)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Sacado that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Sacado fetchRequiredSacado(EOEditingContext editingContext, String keyName, Object value) {
    return _Sacado.fetchRequiredSacado(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Sacado fetchRequiredSacado(EOEditingContext editingContext, EOQualifier qualifier) {
    Sacado eoObject = _Sacado.fetchSacado(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Sacado that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Sacado localInstanceIn(EOEditingContext editingContext, Sacado eo) {
    Sacado localInstance = (eo == null) ? null : (Sacado)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}

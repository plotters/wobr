// $LastChangedRevision: 5810 $ DO NOT EDIT.  Make changes to EOEmissor.java instead.
package br.com.wobr.boleto.model;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _EOEmissor extends  EOGenericRecord {
	public static final String ENTITY_NAME = "EOEmissor";

	// Attributes
	public static final String AGENCIA_KEY = "agencia";
	public static final String CARTEIRA_KEY = "carteira";
	public static final String CEDENTE_KEY = "cedente";
	public static final String CODIGO_FORNECIDO_PELA_AGENCIA_KEY = "codigoFornecidoPelaAgencia";
	public static final String CODIGO_OPERACAO_KEY = "codigoOperacao";
	public static final String CONTA_CORRENTE_KEY = "contaCorrente";
	public static final String DIGITO_VERIFICADOR_AGENCIA_KEY = "digitoVerificadorAgencia";
	public static final String DIGITO_VERIFICADOR_CONTA_CORRENTE_KEY = "digitoVerificadorContaCorrente";
	public static final String DIGITO_VERIFICADOR_NOSSO_NUMERO_KEY = "digitoVerificadorNossoNumero";
	public static final String NOSSO_NUMERO_KEY = "nossoNumero";
	public static final String NUMERO_CONVENIO_KEY = "numeroConvenio";

	// Relationships

  private static Logger LOG = Logger.getLogger(_EOEmissor.class);

  public EOEmissor localInstanceIn(EOEditingContext editingContext) {
    EOEmissor localInstance = (EOEmissor)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public Integer agencia() {
    return (Integer) storedValueForKey("agencia");
  }

  public void setAgencia(Integer value) {
    if (_EOEmissor.LOG.isDebugEnabled()) {
    	_EOEmissor.LOG.debug( "updating agencia from " + agencia() + " to " + value);
    }
    takeStoredValueForKey(value, "agencia");
  }

  public Integer carteira() {
    return (Integer) storedValueForKey("carteira");
  }

  public void setCarteira(Integer value) {
    if (_EOEmissor.LOG.isDebugEnabled()) {
    	_EOEmissor.LOG.debug( "updating carteira from " + carteira() + " to " + value);
    }
    takeStoredValueForKey(value, "carteira");
  }

  public String cedente() {
    return (String) storedValueForKey("cedente");
  }

  public void setCedente(String value) {
    if (_EOEmissor.LOG.isDebugEnabled()) {
    	_EOEmissor.LOG.debug( "updating cedente from " + cedente() + " to " + value);
    }
    takeStoredValueForKey(value, "cedente");
  }

  public Integer codigoFornecidoPelaAgencia() {
    return (Integer) storedValueForKey("codigoFornecidoPelaAgencia");
  }

  public void setCodigoFornecidoPelaAgencia(Integer value) {
    if (_EOEmissor.LOG.isDebugEnabled()) {
    	_EOEmissor.LOG.debug( "updating codigoFornecidoPelaAgencia from " + codigoFornecidoPelaAgencia() + " to " + value);
    }
    takeStoredValueForKey(value, "codigoFornecidoPelaAgencia");
  }

  public Integer codigoOperacao() {
    return (Integer) storedValueForKey("codigoOperacao");
  }

  public void setCodigoOperacao(Integer value) {
    if (_EOEmissor.LOG.isDebugEnabled()) {
    	_EOEmissor.LOG.debug( "updating codigoOperacao from " + codigoOperacao() + " to " + value);
    }
    takeStoredValueForKey(value, "codigoOperacao");
  }

  public Long contaCorrente() {
    return (Long) storedValueForKey("contaCorrente");
  }

  public void setContaCorrente(Long value) {
    if (_EOEmissor.LOG.isDebugEnabled()) {
    	_EOEmissor.LOG.debug( "updating contaCorrente from " + contaCorrente() + " to " + value);
    }
    takeStoredValueForKey(value, "contaCorrente");
  }

  public String digitoVerificadorAgencia() {
    return (String) storedValueForKey("digitoVerificadorAgencia");
  }

  public void setDigitoVerificadorAgencia(String value) {
    if (_EOEmissor.LOG.isDebugEnabled()) {
    	_EOEmissor.LOG.debug( "updating digitoVerificadorAgencia from " + digitoVerificadorAgencia() + " to " + value);
    }
    takeStoredValueForKey(value, "digitoVerificadorAgencia");
  }

  public String digitoVerificadorContaCorrente() {
    return (String) storedValueForKey("digitoVerificadorContaCorrente");
  }

  public void setDigitoVerificadorContaCorrente(String value) {
    if (_EOEmissor.LOG.isDebugEnabled()) {
    	_EOEmissor.LOG.debug( "updating digitoVerificadorContaCorrente from " + digitoVerificadorContaCorrente() + " to " + value);
    }
    takeStoredValueForKey(value, "digitoVerificadorContaCorrente");
  }

  public String digitoVerificadorNossoNumero() {
    return (String) storedValueForKey("digitoVerificadorNossoNumero");
  }

  public void setDigitoVerificadorNossoNumero(String value) {
    if (_EOEmissor.LOG.isDebugEnabled()) {
    	_EOEmissor.LOG.debug( "updating digitoVerificadorNossoNumero from " + digitoVerificadorNossoNumero() + " to " + value);
    }
    takeStoredValueForKey(value, "digitoVerificadorNossoNumero");
  }

  public Long nossoNumero() {
    return (Long) storedValueForKey("nossoNumero");
  }

  public void setNossoNumero(Long value) {
    if (_EOEmissor.LOG.isDebugEnabled()) {
    	_EOEmissor.LOG.debug( "updating nossoNumero from " + nossoNumero() + " to " + value);
    }
    takeStoredValueForKey(value, "nossoNumero");
  }

  public Long numeroConvenio() {
    return (Long) storedValueForKey("numeroConvenio");
  }

  public void setNumeroConvenio(Long value) {
    if (_EOEmissor.LOG.isDebugEnabled()) {
    	_EOEmissor.LOG.debug( "updating numeroConvenio from " + numeroConvenio() + " to " + value);
    }
    takeStoredValueForKey(value, "numeroConvenio");
  }


  public static EOEmissor createEOEmissor(EOEditingContext editingContext) {
    EOEmissor eo = (EOEmissor) EOUtilities.createAndInsertInstance(editingContext, _EOEmissor.ENTITY_NAME);    
    return eo;
  }

  public static NSArray<EOEmissor> fetchAllEOEmissors(EOEditingContext editingContext) {
    return _EOEmissor.fetchAllEOEmissors(editingContext, null);
  }

  public static NSArray<EOEmissor> fetchAllEOEmissors(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _EOEmissor.fetchEOEmissors(editingContext, null, sortOrderings);
  }

  public static NSArray<EOEmissor> fetchEOEmissors(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOEmissor.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<EOEmissor> eoObjects = (NSArray<EOEmissor>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOEmissor fetchEOEmissor(EOEditingContext editingContext, String keyName, Object value) {
    return _EOEmissor.fetchEOEmissor(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOEmissor fetchEOEmissor(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<EOEmissor> eoObjects = _EOEmissor.fetchEOEmissors(editingContext, qualifier, null);
    EOEmissor eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOEmissor)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one EOEmissor that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOEmissor fetchRequiredEOEmissor(EOEditingContext editingContext, String keyName, Object value) {
    return _EOEmissor.fetchRequiredEOEmissor(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOEmissor fetchRequiredEOEmissor(EOEditingContext editingContext, EOQualifier qualifier) {
    EOEmissor eoObject = _EOEmissor.fetchEOEmissor(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no EOEmissor that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOEmissor localInstanceIn(EOEditingContext editingContext, EOEmissor eo) {
    EOEmissor localInstance = (eo == null) ? null : (EOEmissor)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}

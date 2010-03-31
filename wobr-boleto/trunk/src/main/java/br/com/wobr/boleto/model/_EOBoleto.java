// $LastChangedRevision: 5810 $ DO NOT EDIT.  Make changes to EOBoleto.java instead.
package br.com.wobr.boleto.model;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _EOBoleto extends  EOGenericRecord {
	public static final String ENTITY_NAME = "EOBoleto";

	// Attributes
	public static final String ACEITE_KEY = "aceite";
	public static final String BANCO_KEY = "banco";
	public static final String DATA_DOCUMENTO_KEY = "dataDocumento";
	public static final String DATA_PROCESSAMENTO_KEY = "dataProcessamento";
	public static final String DATA_VENCIMENTO_KEY = "dataVencimento";
	public static final String ESPECIE_DOCUMENTO_KEY = "especieDocumento";
	public static final String NUMERO_DOCUMENTO_KEY = "numeroDocumento";
	public static final String QUANTIDADE_MOEDA_KEY = "quantidadeMoeda";
	public static final String VALOR_KEY = "valor";
	public static final String VALOR_MOEDA_KEY = "valorMoeda";

	// Relationships
	public static final String DESCRICOES_KEY = "descricoes";
	public static final String EMISSOR_KEY = "emissor";
	public static final String INSTRUCOES_KEY = "instrucoes";
	public static final String LOCAIS_PAGAMENTO_KEY = "locaisPagamento";
	public static final String SACADO_KEY = "sacado";

  private static Logger LOG = Logger.getLogger(_EOBoleto.class);

  public EOBoleto localInstanceIn(EOEditingContext editingContext) {
    EOBoleto localInstance = (EOBoleto)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public Boolean aceite() {
    return (Boolean) storedValueForKey("aceite");
  }

  public void setAceite(Boolean value) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
    	_EOBoleto.LOG.debug( "updating aceite from " + aceite() + " to " + value);
    }
    takeStoredValueForKey(value, "aceite");
  }

  public br.com.wobr.boleto.model.BancoEnum banco() {
    return (br.com.wobr.boleto.model.BancoEnum) storedValueForKey("banco");
  }

  public void setBanco(br.com.wobr.boleto.model.BancoEnum value) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
    	_EOBoleto.LOG.debug( "updating banco from " + banco() + " to " + value);
    }
    takeStoredValueForKey(value, "banco");
  }

  public NSTimestamp dataDocumento() {
    return (NSTimestamp) storedValueForKey("dataDocumento");
  }

  public void setDataDocumento(NSTimestamp value) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
    	_EOBoleto.LOG.debug( "updating dataDocumento from " + dataDocumento() + " to " + value);
    }
    takeStoredValueForKey(value, "dataDocumento");
  }

  public NSTimestamp dataProcessamento() {
    return (NSTimestamp) storedValueForKey("dataProcessamento");
  }

  public void setDataProcessamento(NSTimestamp value) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
    	_EOBoleto.LOG.debug( "updating dataProcessamento from " + dataProcessamento() + " to " + value);
    }
    takeStoredValueForKey(value, "dataProcessamento");
  }

  public NSTimestamp dataVencimento() {
    return (NSTimestamp) storedValueForKey("dataVencimento");
  }

  public void setDataVencimento(NSTimestamp value) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
    	_EOBoleto.LOG.debug( "updating dataVencimento from " + dataVencimento() + " to " + value);
    }
    takeStoredValueForKey(value, "dataVencimento");
  }

  public String especieDocumento() {
    return (String) storedValueForKey("especieDocumento");
  }

  public void setEspecieDocumento(String value) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
    	_EOBoleto.LOG.debug( "updating especieDocumento from " + especieDocumento() + " to " + value);
    }
    takeStoredValueForKey(value, "especieDocumento");
  }

  public String numeroDocumento() {
    return (String) storedValueForKey("numeroDocumento");
  }

  public void setNumeroDocumento(String value) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
    	_EOBoleto.LOG.debug( "updating numeroDocumento from " + numeroDocumento() + " to " + value);
    }
    takeStoredValueForKey(value, "numeroDocumento");
  }

  public java.math.BigDecimal quantidadeMoeda() {
    return (java.math.BigDecimal) storedValueForKey("quantidadeMoeda");
  }

  public void setQuantidadeMoeda(java.math.BigDecimal value) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
    	_EOBoleto.LOG.debug( "updating quantidadeMoeda from " + quantidadeMoeda() + " to " + value);
    }
    takeStoredValueForKey(value, "quantidadeMoeda");
  }

  public java.math.BigDecimal valor() {
    return (java.math.BigDecimal) storedValueForKey("valor");
  }

  public void setValor(java.math.BigDecimal value) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
    	_EOBoleto.LOG.debug( "updating valor from " + valor() + " to " + value);
    }
    takeStoredValueForKey(value, "valor");
  }

  public java.math.BigDecimal valorMoeda() {
    return (java.math.BigDecimal) storedValueForKey("valorMoeda");
  }

  public void setValorMoeda(java.math.BigDecimal value) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
    	_EOBoleto.LOG.debug( "updating valorMoeda from " + valorMoeda() + " to " + value);
    }
    takeStoredValueForKey(value, "valorMoeda");
  }

  public br.com.wobr.boleto.model.EOEmissor emissor() {
    return (br.com.wobr.boleto.model.EOEmissor)storedValueForKey("emissor");
  }

  public void setEmissorRelationship(br.com.wobr.boleto.model.EOEmissor value) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
      _EOBoleto.LOG.debug("updating emissor from " + emissor() + " to " + value);
    }
    if (value == null) {
    	br.com.wobr.boleto.model.EOEmissor oldValue = emissor();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "emissor");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "emissor");
    }
  }
  
  public br.com.wobr.boleto.model.EOSacado sacado() {
    return (br.com.wobr.boleto.model.EOSacado)storedValueForKey("sacado");
  }

  public void setSacadoRelationship(br.com.wobr.boleto.model.EOSacado value) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
      _EOBoleto.LOG.debug("updating sacado from " + sacado() + " to " + value);
    }
    if (value == null) {
    	br.com.wobr.boleto.model.EOSacado oldValue = sacado();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "sacado");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "sacado");
    }
  }
  
  public NSArray<br.com.wobr.boleto.model.EODescricao> descricoes() {
    return (NSArray<br.com.wobr.boleto.model.EODescricao>)storedValueForKey("descricoes");
  }

  public NSArray<br.com.wobr.boleto.model.EODescricao> descricoes(EOQualifier qualifier) {
    return descricoes(qualifier, null);
  }

  public NSArray<br.com.wobr.boleto.model.EODescricao> descricoes(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<br.com.wobr.boleto.model.EODescricao> results;
      results = descricoes();
      if (qualifier != null) {
        results = (NSArray<br.com.wobr.boleto.model.EODescricao>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<br.com.wobr.boleto.model.EODescricao>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToDescricoesRelationship(br.com.wobr.boleto.model.EODescricao object) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
      _EOBoleto.LOG.debug("adding " + object + " to descricoes relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "descricoes");
  }

  public void removeFromDescricoesRelationship(br.com.wobr.boleto.model.EODescricao object) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
      _EOBoleto.LOG.debug("removing " + object + " from descricoes relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "descricoes");
  }

  public br.com.wobr.boleto.model.EODescricao createDescricoesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("EODescricao");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "descricoes");
    return (br.com.wobr.boleto.model.EODescricao) eo;
  }

  public void deleteDescricoesRelationship(br.com.wobr.boleto.model.EODescricao object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "descricoes");
    editingContext().deleteObject(object);
  }

  public void deleteAllDescricoesRelationships() {
    Enumeration objects = descricoes().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteDescricoesRelationship((br.com.wobr.boleto.model.EODescricao)objects.nextElement());
    }
  }

  public NSArray<br.com.wobr.boleto.model.EOInstrucao> instrucoes() {
    return (NSArray<br.com.wobr.boleto.model.EOInstrucao>)storedValueForKey("instrucoes");
  }

  public NSArray<br.com.wobr.boleto.model.EOInstrucao> instrucoes(EOQualifier qualifier) {
    return instrucoes(qualifier, null);
  }

  public NSArray<br.com.wobr.boleto.model.EOInstrucao> instrucoes(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<br.com.wobr.boleto.model.EOInstrucao> results;
      results = instrucoes();
      if (qualifier != null) {
        results = (NSArray<br.com.wobr.boleto.model.EOInstrucao>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<br.com.wobr.boleto.model.EOInstrucao>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToInstrucoesRelationship(br.com.wobr.boleto.model.EOInstrucao object) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
      _EOBoleto.LOG.debug("adding " + object + " to instrucoes relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "instrucoes");
  }

  public void removeFromInstrucoesRelationship(br.com.wobr.boleto.model.EOInstrucao object) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
      _EOBoleto.LOG.debug("removing " + object + " from instrucoes relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "instrucoes");
  }

  public br.com.wobr.boleto.model.EOInstrucao createInstrucoesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("EOInstrucao");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "instrucoes");
    return (br.com.wobr.boleto.model.EOInstrucao) eo;
  }

  public void deleteInstrucoesRelationship(br.com.wobr.boleto.model.EOInstrucao object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "instrucoes");
    editingContext().deleteObject(object);
  }

  public void deleteAllInstrucoesRelationships() {
    Enumeration objects = instrucoes().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteInstrucoesRelationship((br.com.wobr.boleto.model.EOInstrucao)objects.nextElement());
    }
  }

  public NSArray<br.com.wobr.boleto.model.EOLocalPagamento> locaisPagamento() {
    return (NSArray<br.com.wobr.boleto.model.EOLocalPagamento>)storedValueForKey("locaisPagamento");
  }

  public NSArray<br.com.wobr.boleto.model.EOLocalPagamento> locaisPagamento(EOQualifier qualifier) {
    return locaisPagamento(qualifier, null);
  }

  public NSArray<br.com.wobr.boleto.model.EOLocalPagamento> locaisPagamento(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<br.com.wobr.boleto.model.EOLocalPagamento> results;
      results = locaisPagamento();
      if (qualifier != null) {
        results = (NSArray<br.com.wobr.boleto.model.EOLocalPagamento>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<br.com.wobr.boleto.model.EOLocalPagamento>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToLocaisPagamentoRelationship(br.com.wobr.boleto.model.EOLocalPagamento object) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
      _EOBoleto.LOG.debug("adding " + object + " to locaisPagamento relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "locaisPagamento");
  }

  public void removeFromLocaisPagamentoRelationship(br.com.wobr.boleto.model.EOLocalPagamento object) {
    if (_EOBoleto.LOG.isDebugEnabled()) {
      _EOBoleto.LOG.debug("removing " + object + " from locaisPagamento relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "locaisPagamento");
  }

  public br.com.wobr.boleto.model.EOLocalPagamento createLocaisPagamentoRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("EOLocalPagamento");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "locaisPagamento");
    return (br.com.wobr.boleto.model.EOLocalPagamento) eo;
  }

  public void deleteLocaisPagamentoRelationship(br.com.wobr.boleto.model.EOLocalPagamento object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "locaisPagamento");
    editingContext().deleteObject(object);
  }

  public void deleteAllLocaisPagamentoRelationships() {
    Enumeration objects = locaisPagamento().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteLocaisPagamentoRelationship((br.com.wobr.boleto.model.EOLocalPagamento)objects.nextElement());
    }
  }


  public static EOBoleto createEOBoleto(EOEditingContext editingContext, Boolean aceite
, br.com.wobr.boleto.model.EOEmissor emissor, br.com.wobr.boleto.model.EOSacado sacado) {
    EOBoleto eo = (EOBoleto) EOUtilities.createAndInsertInstance(editingContext, _EOBoleto.ENTITY_NAME);    
		eo.setAceite(aceite);
    eo.setEmissorRelationship(emissor);
    eo.setSacadoRelationship(sacado);
    return eo;
  }

  public static NSArray<EOBoleto> fetchAllEOBoletos(EOEditingContext editingContext) {
    return _EOBoleto.fetchAllEOBoletos(editingContext, null);
  }

  public static NSArray<EOBoleto> fetchAllEOBoletos(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _EOBoleto.fetchEOBoletos(editingContext, null, sortOrderings);
  }

  public static NSArray<EOBoleto> fetchEOBoletos(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_EOBoleto.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<EOBoleto> eoObjects = (NSArray<EOBoleto>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static EOBoleto fetchEOBoleto(EOEditingContext editingContext, String keyName, Object value) {
    return _EOBoleto.fetchEOBoleto(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOBoleto fetchEOBoleto(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<EOBoleto> eoObjects = _EOBoleto.fetchEOBoletos(editingContext, qualifier, null);
    EOBoleto eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (EOBoleto)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one EOBoleto that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOBoleto fetchRequiredEOBoleto(EOEditingContext editingContext, String keyName, Object value) {
    return _EOBoleto.fetchRequiredEOBoleto(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static EOBoleto fetchRequiredEOBoleto(EOEditingContext editingContext, EOQualifier qualifier) {
    EOBoleto eoObject = _EOBoleto.fetchEOBoleto(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no EOBoleto that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static EOBoleto localInstanceIn(EOEditingContext editingContext, EOBoleto eo) {
    EOBoleto localInstance = (eo == null) ? null : (EOBoleto)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}

// $LastChangedRevision: 5810 $ DO NOT EDIT.  Make changes to Boleto.java instead.
package br.com.wobr.boleto.model;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _Boleto extends  EOGenericRecord {
	public static final String ENTITY_NAME = "Boleto";

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
	public static final String INSTRUCOES_KEY = "instrucoes";
	public static final String LOCAIS_PAGAMENTO_KEY = "locaisPagamento";

  private static Logger LOG = Logger.getLogger(_Boleto.class);

  public Boleto localInstanceIn(EOEditingContext editingContext) {
    Boleto localInstance = (Boleto)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public Boolean aceite() {
    return (Boolean) storedValueForKey("aceite");
  }

  public void setAceite(Boolean value) {
    if (_Boleto.LOG.isDebugEnabled()) {
    	_Boleto.LOG.debug( "updating aceite from " + aceite() + " to " + value);
    }
    takeStoredValueForKey(value, "aceite");
  }

  public br.com.wobr.boleto.model.BancoEnum banco() {
    return (br.com.wobr.boleto.model.BancoEnum) storedValueForKey("banco");
  }

  public void setBanco(br.com.wobr.boleto.model.BancoEnum value) {
    if (_Boleto.LOG.isDebugEnabled()) {
    	_Boleto.LOG.debug( "updating banco from " + banco() + " to " + value);
    }
    takeStoredValueForKey(value, "banco");
  }

  public NSTimestamp dataDocumento() {
    return (NSTimestamp) storedValueForKey("dataDocumento");
  }

  public void setDataDocumento(NSTimestamp value) {
    if (_Boleto.LOG.isDebugEnabled()) {
    	_Boleto.LOG.debug( "updating dataDocumento from " + dataDocumento() + " to " + value);
    }
    takeStoredValueForKey(value, "dataDocumento");
  }

  public NSTimestamp dataProcessamento() {
    return (NSTimestamp) storedValueForKey("dataProcessamento");
  }

  public void setDataProcessamento(NSTimestamp value) {
    if (_Boleto.LOG.isDebugEnabled()) {
    	_Boleto.LOG.debug( "updating dataProcessamento from " + dataProcessamento() + " to " + value);
    }
    takeStoredValueForKey(value, "dataProcessamento");
  }

  public NSTimestamp dataVencimento() {
    return (NSTimestamp) storedValueForKey("dataVencimento");
  }

  public void setDataVencimento(NSTimestamp value) {
    if (_Boleto.LOG.isDebugEnabled()) {
    	_Boleto.LOG.debug( "updating dataVencimento from " + dataVencimento() + " to " + value);
    }
    takeStoredValueForKey(value, "dataVencimento");
  }

  public String especieDocumento() {
    return (String) storedValueForKey("especieDocumento");
  }

  public void setEspecieDocumento(String value) {
    if (_Boleto.LOG.isDebugEnabled()) {
    	_Boleto.LOG.debug( "updating especieDocumento from " + especieDocumento() + " to " + value);
    }
    takeStoredValueForKey(value, "especieDocumento");
  }

  public String numeroDocumento() {
    return (String) storedValueForKey("numeroDocumento");
  }

  public void setNumeroDocumento(String value) {
    if (_Boleto.LOG.isDebugEnabled()) {
    	_Boleto.LOG.debug( "updating numeroDocumento from " + numeroDocumento() + " to " + value);
    }
    takeStoredValueForKey(value, "numeroDocumento");
  }

  public java.math.BigDecimal quantidadeMoeda() {
    return (java.math.BigDecimal) storedValueForKey("quantidadeMoeda");
  }

  public void setQuantidadeMoeda(java.math.BigDecimal value) {
    if (_Boleto.LOG.isDebugEnabled()) {
    	_Boleto.LOG.debug( "updating quantidadeMoeda from " + quantidadeMoeda() + " to " + value);
    }
    takeStoredValueForKey(value, "quantidadeMoeda");
  }

  public java.math.BigDecimal valor() {
    return (java.math.BigDecimal) storedValueForKey("valor");
  }

  public void setValor(java.math.BigDecimal value) {
    if (_Boleto.LOG.isDebugEnabled()) {
    	_Boleto.LOG.debug( "updating valor from " + valor() + " to " + value);
    }
    takeStoredValueForKey(value, "valor");
  }

  public java.math.BigDecimal valorMoeda() {
    return (java.math.BigDecimal) storedValueForKey("valorMoeda");
  }

  public void setValorMoeda(java.math.BigDecimal value) {
    if (_Boleto.LOG.isDebugEnabled()) {
    	_Boleto.LOG.debug( "updating valorMoeda from " + valorMoeda() + " to " + value);
    }
    takeStoredValueForKey(value, "valorMoeda");
  }

  public NSArray<br.com.wobr.boleto.model.Descricao> descricoes() {
    return (NSArray<br.com.wobr.boleto.model.Descricao>)storedValueForKey("descricoes");
  }

  public NSArray<br.com.wobr.boleto.model.Descricao> descricoes(EOQualifier qualifier) {
    return descricoes(qualifier, null);
  }

  public NSArray<br.com.wobr.boleto.model.Descricao> descricoes(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<br.com.wobr.boleto.model.Descricao> results;
      results = descricoes();
      if (qualifier != null) {
        results = (NSArray<br.com.wobr.boleto.model.Descricao>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<br.com.wobr.boleto.model.Descricao>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToDescricoesRelationship(br.com.wobr.boleto.model.Descricao object) {
    if (_Boleto.LOG.isDebugEnabled()) {
      _Boleto.LOG.debug("adding " + object + " to descricoes relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "descricoes");
  }

  public void removeFromDescricoesRelationship(br.com.wobr.boleto.model.Descricao object) {
    if (_Boleto.LOG.isDebugEnabled()) {
      _Boleto.LOG.debug("removing " + object + " from descricoes relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "descricoes");
  }

  public br.com.wobr.boleto.model.Descricao createDescricoesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Descricao");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "descricoes");
    return (br.com.wobr.boleto.model.Descricao) eo;
  }

  public void deleteDescricoesRelationship(br.com.wobr.boleto.model.Descricao object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "descricoes");
    editingContext().deleteObject(object);
  }

  public void deleteAllDescricoesRelationships() {
    Enumeration objects = descricoes().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteDescricoesRelationship((br.com.wobr.boleto.model.Descricao)objects.nextElement());
    }
  }

  public NSArray<br.com.wobr.boleto.model.Instrucao> instrucoes() {
    return (NSArray<br.com.wobr.boleto.model.Instrucao>)storedValueForKey("instrucoes");
  }

  public NSArray<br.com.wobr.boleto.model.Instrucao> instrucoes(EOQualifier qualifier) {
    return instrucoes(qualifier, null);
  }

  public NSArray<br.com.wobr.boleto.model.Instrucao> instrucoes(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<br.com.wobr.boleto.model.Instrucao> results;
      results = instrucoes();
      if (qualifier != null) {
        results = (NSArray<br.com.wobr.boleto.model.Instrucao>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<br.com.wobr.boleto.model.Instrucao>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToInstrucoesRelationship(br.com.wobr.boleto.model.Instrucao object) {
    if (_Boleto.LOG.isDebugEnabled()) {
      _Boleto.LOG.debug("adding " + object + " to instrucoes relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "instrucoes");
  }

  public void removeFromInstrucoesRelationship(br.com.wobr.boleto.model.Instrucao object) {
    if (_Boleto.LOG.isDebugEnabled()) {
      _Boleto.LOG.debug("removing " + object + " from instrucoes relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "instrucoes");
  }

  public br.com.wobr.boleto.model.Instrucao createInstrucoesRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Instrucao");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "instrucoes");
    return (br.com.wobr.boleto.model.Instrucao) eo;
  }

  public void deleteInstrucoesRelationship(br.com.wobr.boleto.model.Instrucao object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "instrucoes");
    editingContext().deleteObject(object);
  }

  public void deleteAllInstrucoesRelationships() {
    Enumeration objects = instrucoes().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteInstrucoesRelationship((br.com.wobr.boleto.model.Instrucao)objects.nextElement());
    }
  }

  public NSArray<br.com.wobr.boleto.model.LocalPagamento> locaisPagamento() {
    return (NSArray<br.com.wobr.boleto.model.LocalPagamento>)storedValueForKey("locaisPagamento");
  }

  public NSArray<br.com.wobr.boleto.model.LocalPagamento> locaisPagamento(EOQualifier qualifier) {
    return locaisPagamento(qualifier, null);
  }

  public NSArray<br.com.wobr.boleto.model.LocalPagamento> locaisPagamento(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    NSArray<br.com.wobr.boleto.model.LocalPagamento> results;
      results = locaisPagamento();
      if (qualifier != null) {
        results = (NSArray<br.com.wobr.boleto.model.LocalPagamento>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<br.com.wobr.boleto.model.LocalPagamento>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    return results;
  }
  
  public void addToLocaisPagamentoRelationship(br.com.wobr.boleto.model.LocalPagamento object) {
    if (_Boleto.LOG.isDebugEnabled()) {
      _Boleto.LOG.debug("adding " + object + " to locaisPagamento relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "locaisPagamento");
  }

  public void removeFromLocaisPagamentoRelationship(br.com.wobr.boleto.model.LocalPagamento object) {
    if (_Boleto.LOG.isDebugEnabled()) {
      _Boleto.LOG.debug("removing " + object + " from locaisPagamento relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "locaisPagamento");
  }

  public br.com.wobr.boleto.model.LocalPagamento createLocaisPagamentoRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("LocalPagamento");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "locaisPagamento");
    return (br.com.wobr.boleto.model.LocalPagamento) eo;
  }

  public void deleteLocaisPagamentoRelationship(br.com.wobr.boleto.model.LocalPagamento object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "locaisPagamento");
    editingContext().deleteObject(object);
  }

  public void deleteAllLocaisPagamentoRelationships() {
    Enumeration objects = locaisPagamento().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteLocaisPagamentoRelationship((br.com.wobr.boleto.model.LocalPagamento)objects.nextElement());
    }
  }


  public static Boleto createBoleto(EOEditingContext editingContext, Boolean aceite
, br.com.wobr.boleto.model.BancoEnum banco
, NSTimestamp dataVencimento
, String especieDocumento
, String numeroDocumento
, java.math.BigDecimal valor
) {
    Boleto eo = (Boleto) EOUtilities.createAndInsertInstance(editingContext, _Boleto.ENTITY_NAME);    
		eo.setAceite(aceite);
		eo.setBanco(banco);
		eo.setDataVencimento(dataVencimento);
		eo.setEspecieDocumento(especieDocumento);
		eo.setNumeroDocumento(numeroDocumento);
		eo.setValor(valor);
    return eo;
  }

  public static NSArray<Boleto> fetchAllBoletos(EOEditingContext editingContext) {
    return _Boleto.fetchAllBoletos(editingContext, null);
  }

  public static NSArray<Boleto> fetchAllBoletos(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Boleto.fetchBoletos(editingContext, null, sortOrderings);
  }

  public static NSArray<Boleto> fetchBoletos(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Boleto.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Boleto> eoObjects = (NSArray<Boleto>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Boleto fetchBoleto(EOEditingContext editingContext, String keyName, Object value) {
    return _Boleto.fetchBoleto(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Boleto fetchBoleto(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Boleto> eoObjects = _Boleto.fetchBoletos(editingContext, qualifier, null);
    Boleto eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Boleto)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Boleto that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Boleto fetchRequiredBoleto(EOEditingContext editingContext, String keyName, Object value) {
    return _Boleto.fetchRequiredBoleto(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Boleto fetchRequiredBoleto(EOEditingContext editingContext, EOQualifier qualifier) {
    Boleto eoObject = _Boleto.fetchBoleto(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Boleto that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Boleto localInstanceIn(EOEditingContext editingContext, Boleto eo) {
    Boleto localInstance = (eo == null) ? null : (Boleto)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}

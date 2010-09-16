package br.com.wobr.base;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.webobjects.eoaccess.EOAttribute;
import com.webobjects.eoaccess.EOEntity;
import com.webobjects.eoaccess.EOModel;
import com.webobjects.eoaccess.EORelationship;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class TestLocalizerKeyGenerator
{
	private LocalizerKeyGenerator generator;

	private EOEntity mockEntity;

	private EOModel mockModel;

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void exceptionIfKeypathIsCompletellyInvalid() throws Exception
	{
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(is("Cannot generate key. The keypath 'invalid.invalid' is invalid."));

		generator.generateKey(mockEntity, "invalid.invalid");
	}

	@Test
	public void keyForEntityAndOneLevelKeypath() throws Exception
	{
		String result = generator.generateKey(mockEntity, "property");

		assertThat(result, is("model.model_name.EntityName.property"));
	}

	@Test
	public void keyForEntityAndTwoLevelKeypath() throws Exception
	{
		EOEntity mockEntity2 = Mockito.mock(EOEntity.class);

		EOAttribute mockAttribute = Mockito.mock(EOAttribute.class);

		Mockito.when(mockEntity2._attributeForPath("property1.property2")).thenReturn(mockAttribute);

		Mockito.when(mockAttribute.entity()).thenReturn(mockEntity);
		Mockito.when(mockAttribute.name()).thenReturn("property2");

		String result = generator.generateKey(mockEntity2, "property1.property2");

		assertThat(result, is("model.model_name.EntityName.property2"));
	}

	@Test
	public void keyForInvalidKeypathOnLastPath() throws Exception
	{
		EORelationship mockRelationship = mock(EORelationship.class);

		when(mockEntity._attributeForPath("valid.valid.valid.invalid")).thenReturn(null);
		when(mockEntity._relationshipForPath("valid.valid.valid")).thenReturn(mockRelationship);

		EOEntity mockEntity2 = mock(EOEntity.class);

		when(mockRelationship.destinationEntity()).thenReturn(mockEntity2);
		when(mockEntity2.name()).thenReturn("AnotherEntity");
		when(mockEntity2.model()).thenReturn(mockModel);

		String result = generator.generateKey(mockEntity, "valid.valid.valid.invalid");

		assertThat(result, is("model.model_name.AnotherEntity.invalid"));
	}

	@Test
	public void keyForNullEntityAndNullKeypath() throws Exception
	{
		String result = generator.generateKey(null, null);

		assertThat(result, nullValue());
	}

	@Test
	public void keyForNullKeypath() throws Exception
	{
		String result = generator.generateKey(mockEntity, null);

		assertThat(result, is("model.model_name.EntityName"));
	}

	@Before
	public void setup()
	{
		generator = new LocalizerKeyGenerator();

		mockModel = Mockito.mock(EOModel.class);

		Mockito.when(mockModel.name()).thenReturn("model_name");

		mockEntity = Mockito.mock(EOEntity.class);

		Mockito.when(mockEntity.model()).thenReturn(mockModel);
		Mockito.when(mockEntity.name()).thenReturn("EntityName");
	}

}

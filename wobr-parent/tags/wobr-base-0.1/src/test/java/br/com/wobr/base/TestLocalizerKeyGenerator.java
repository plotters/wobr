package br.com.wobr.base;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.webobjects.eoaccess.EOAttribute;
import com.webobjects.eoaccess.EOEntity;
import com.webobjects.eoaccess.EOModel;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class TestLocalizerKeyGenerator
{
	protected LocalizerKeyGenerator generator;

	private EOEntity mockEntity;

	protected EOModel mockModel;

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

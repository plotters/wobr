package br.com.wobr.guice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.webobjects.appserver.WOSession;
import com.webobjects.foundation.NSKeyValueCoding;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class TestWOSessionScope
{
	private static final String KEY_NAME = "Key[type=java.lang.Object, annotation=[none]]";

	@Mock
	protected Provider<Object> mockCreator;

	protected Key<Object> mockKey;

	@Mock
	protected Object mockObject;

	@Mock
	protected WOSession mockSession;

	protected WOSessionScope scope;

	@Test
	public void createAndSaveObjectOnFirstCall() throws Exception
	{
		Provider<Object> result = scope.scope(mockKey, mockCreator);

		assertThat(result.get(), is(mockObject));

		Mockito.verify(mockCreator).get();
		Mockito.verify(mockSession).setObjectForKey(mockObject, KEY_NAME);
	}

	@Test
	public void retrieveTheSameObjectOnSubSequentCalls() throws Exception
	{
		for(int i = 0; i < 10; i++)
		{
			Provider<Object> result = scope.scope(mockKey, mockCreator);

			assertThat(result.get(), is(mockObject));
		}

		Mockito.verify(mockCreator, Mockito.times(1)).get();
		Mockito.verify(mockSession, Mockito.times(1)).setObjectForKey(mockObject, KEY_NAME);
	}

	@Test
	public void returnNullOnSubsequentCalls() throws Exception
	{
		Mockito.when(mockSession.objectForKey(KEY_NAME)).thenReturn(null, NSKeyValueCoding.NullValue);
		Mockito.when(mockCreator.get()).thenReturn(null);

		for(int i = 0; i < 10; i++)
		{
			Provider<Object> result = scope.scope(mockKey, mockCreator);

			assertThat(result.get(), nullValue());
		}

		Mockito.verify(mockCreator, Mockito.times(1)).get();
		Mockito.verify(mockSession, Mockito.times(1)).setObjectForKey(NSKeyValueCoding.NullValue, KEY_NAME);
	}

	@Test
	public void setNullValueIfProvidesReturnsNull() throws Exception
	{
		Mockito.when(mockCreator.get()).thenReturn(null);

		Provider<Object> result = scope.scope(mockKey, mockCreator);

		assertThat(result.get(), nullValue());

		Mockito.verify(mockSession).setObjectForKey(NSKeyValueCoding.NullValue, KEY_NAME);
	}

	@Before
	public void setup()
	{
		scope = Mockito.spy(new WOSessionScope());

		Mockito.doReturn(mockSession).when(scope).session();

		mockKey = Key.get(Object.class);

		Mockito.when(mockSession.objectForKey(KEY_NAME)).thenReturn(null, mockObject);

		Mockito.when(mockCreator.get()).thenReturn(mockObject);
	}
}

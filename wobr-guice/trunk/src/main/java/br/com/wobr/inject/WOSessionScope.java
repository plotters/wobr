package br.com.wobr.inject;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.webobjects.appserver.WOSession;
import com.webobjects.foundation.NSKeyValueCoding;

import er.extensions.appserver.ERXSession;

/**
 * WOSession scope.
 * 
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
class WOSessionScope implements Scope
{
	/*
	 * (non-Javadoc)
	 * @see com.google.inject.Scope#scope(com.google.inject.Key,
	 * com.google.inject.Provider)
	 */
	public <T> Provider<T> scope(final Key<T> key, final Provider<T> creator)
	{
		final String name = key.toString();

		return new Provider<T>()
		{
			public T get()
			{
				WOSession session = session();

				synchronized(session)
				{
					Object object = session.objectForKey(name);

					if(object == NSKeyValueCoding.NullValue)
					{
						return null;
					}

					@SuppressWarnings("unchecked")
					T t = (T) object;

					if(t == null)
					{
						t = creator.get();

						session.setObjectForKey(t != null ? t : NSKeyValueCoding.NullValue, name);
					}

					return t;
				}
			}
		};
	}

	protected WOSession session()
	{
		return ERXSession.session();
	}
}

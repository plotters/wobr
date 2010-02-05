package br.com.wobr.guice;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;

public class WOSessionScope implements Scope
{
	public <T> Provider<T> scope(Key<T> arg0, Provider<T> arg1)
	{
		return null;
	}
}

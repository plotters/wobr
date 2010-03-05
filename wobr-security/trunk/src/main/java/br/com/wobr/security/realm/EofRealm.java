package br.com.wobr.security.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;

import er.extensions.foundation.ERXProperties;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class EofRealm extends AuthorizingRealm
{
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException
	{
		return null;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0)
	{
		return null;
	}

	protected SimpleAccount getAccount(String username)
	{
		EOEditingContext editingContext = null;

		String entityName = ERXProperties.stringForKey("security.realm.entityName");

		String usernameKey = ERXProperties.stringForKey("security.realm.usernameKey");

		// TODO: Testar o caso de nao ter um usuario com esse username
		// TODO: Testar o caso de ter mais de um usuario com esse login
		EOEnterpriseObject user = EOUtilities.objectMatchingKeyAndValue(editingContext, entityName, usernameKey, username);

		String passwordKey = ERXProperties.stringForKey("security.realm.passwordKey");

		String password = (String) user.valueForKey(passwordKey);

		// TODO: Fazer algo com o password

		return null;
	}
}

package br.com.wobr.base;

import com.webobjects.eoaccess.EOAttribute;
import com.webobjects.eoaccess.EOEntity;

/**
 * Generator for keys used for localization. The generated keys can be used
 * together with an <code>ERXLocalizer</code> object to provide localized values
 * for entity names and attribute names.
 * 
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class LocalizerKeyGenerator
{
	/**
	 * Generates a key based on the entity and key path provided.
	 * <p>
	 * Example:
	 * <p>
	 * model.ModelName.EntityName.attributeName
	 * <p>
	 * The model name and the entity name are corresponding to the last
	 * attribute in the keypath. The attribute suffix is not appended if the
	 * keypath is <code>null</code>.
	 * 
	 * @param entity
	 *            An EOEntity object
	 * @param keypath
	 *            A <code>String</code> representing a chain of relations until
	 *            the attribute
	 * @return The key used for localization corresponding the entity's
	 *         attribute or <code>null</code> if the entity is <code>null</code>
	 */
	public String generateKey(EOEntity entity, String keypath)
	{
		if(entity == null)
		{
			return null;
		}

		StringBuilder buffer = new StringBuilder();

		EOEntity entityForLastProperty = entity;
		String attributeName = keypath;

		if(keypath != null && keypath.contains("."))
		{
			EOAttribute attribute = entity._attributeForPath(keypath);

			entityForLastProperty = attribute.entity();
			attributeName = attribute.name();
		}

		buffer.append("model.").append(entityForLastProperty.model().name()).append(".").append(entityForLastProperty.name());

		if(attributeName != null)
		{
			buffer.append(".").append(attributeName);
		}

		return buffer.toString();
	}
}

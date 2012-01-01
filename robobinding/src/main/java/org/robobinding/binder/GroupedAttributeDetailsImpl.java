/**
 * Copyright 2012 Cheng Wei, Robert Taylor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.robobinding.binder;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.robobinding.internal.com_google_common.collect.Lists;
import org.robobinding.internal.org_apache_commons_lang3.builder.EqualsBuilder;
import org.robobinding.internal.org_apache_commons_lang3.builder.HashCodeBuilder;
import org.robobinding.viewattribute.GroupedAttributeDetails;

/**
 *
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Cheng Wei
 */
public class GroupedAttributeDetailsImpl implements GroupedAttributeDetails
{
	private String[] supportedAttributes;
	private Map<String, String> presentAttributeMappings; 
	public GroupedAttributeDetailsImpl(String[] attributes)
	{
		this.supportedAttributes = attributes;
	}

	public String[] getSupportedAttributes()
	{
		return supportedAttributes;
	}

	public void addPresentAttribute(String attribute, String attributeValue)
	{
		presentAttributeMappings.put(attribute, attributeValue);
	}

	public Collection<String> getPresentAttributes()
	{
		return presentAttributeMappings.keySet();
	}
	
	@Override
	public boolean hasAbsentAttributes(String... attributes)
	{
		for (String attribute : attributes)
		{
			if(!presentAttributeMappings.containsKey(attribute))
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Collection<String> findAbsentAttributes(String... attributes)
	{
		List<String> absentAttributes = Lists.newArrayList();
		for (String attribute : attributes)
		{
			if(!presentAttributeMappings.containsKey(attribute))
			{
				absentAttributes.add(attribute);
			}
		}
		return absentAttributes;
	}
	
	@Override
	public String attributeValueFor(String attribute)
	{
		return presentAttributeMappings.get(attribute);
	}
	
	@Override
	public boolean hasAttribute(String attribute)
	{
		return presentAttributeMappings.containsKey(attribute);
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (this==other) return true;
		if (!(other instanceof GroupedAttributeDetailsImpl)) return false;
		
		final GroupedAttributeDetailsImpl that = (GroupedAttributeDetailsImpl)other;
		return new EqualsBuilder()
				.append(supportedAttributes, that.supportedAttributes)
				.isEquals();
	}
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder()
			.append(supportedAttributes)
			.toHashCode();
	}
}

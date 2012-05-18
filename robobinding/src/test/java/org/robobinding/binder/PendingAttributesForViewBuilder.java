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

import java.util.Map;

import org.robobinding.PendingAttributesForView;
import org.robobinding.PendingAttributesForViewImpl;

import android.view.View;

import com.google.common.collect.Maps;

/**
 *
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Cheng Wei
 */
public class PendingAttributesForViewBuilder
{
	private View view;
	private Map<String, String> pendingAttributes;
	private PendingAttributesForViewBuilder(View view)
	{
		this.view = view;
		pendingAttributes = Maps.newHashMap();
	}
	
	public PendingAttributesForViewBuilder withAttriubte(String name, String value)
	{
		pendingAttributes.put(name, value);
		return this;
	}
	
	public PendingAttributesForView build()
	{
		return new PendingAttributesForViewImpl(view, pendingAttributes);
	}
	
	public static PendingAttributesForViewBuilder aPendingAttributesForView(View view)
	{
		return new PendingAttributesForViewBuilder(view);
	}
}

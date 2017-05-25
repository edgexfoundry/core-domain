/*******************************************************************************
 * Copyright 2016-2017 Dell Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @microservice:  core-domain library
 * @author: Jim White, Dell
 * @version: 1.0.0
 *******************************************************************************/
package org.edgexfoundry.domain.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuppressWarnings("serial")
public class ValueDescriptor extends DescribedObject {

	@Indexed(unique = true)
	// non-database identifier for a value descriptor- must be unique
	private String name;

	// minimum allowed value
	private Object min;

	// maximum allowed value
	private Object max;

	// should be either string, int, float, boolean (S, I, F, B)
	private IoTType type;

	// unit of measure label
	private String uomLabel;

	// default value
	private Object defaultValue;

	// following printf convention - see
	// en.wikipedia.org/wiki/Printf_format_string
	private String formatting;

	// tags identifying the value or value purpose
	private String[] labels;

	public ValueDescriptor() {
	}

	public ValueDescriptor(String name, Object min, Object max, IoTType type, String uomLabel, Object defaultValue,
			String formatting, String[] labels, String description) {
		super();
		this.name = name;
		this.min = min;
		this.max = max;
		this.type = type;
		this.uomLabel = uomLabel;
		this.defaultValue = defaultValue;
		this.formatting = formatting;
		this.labels = labels;
		this.setDescription(description);
	}

	public static List<String> getNames(List<ValueDescriptor> valueDescriptors) {
		List<String> names = new ArrayList<String>();
		for (ValueDescriptor valueDescriptor : valueDescriptors) {
			names.add(valueDescriptor.getName());
		}
		return names;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getMin() {
		return min;
	}

	public void setMin(Object min) {
		this.min = min;
	}

	public Object getMax() {
		return max;
	}

	public void setMax(Object max) {
		this.max = max;
	}

	public IoTType getType() {
		return type;
	}

	public void setType(IoTType type) {
		this.type = type;
	}

	public String getUomLabel() {
		return uomLabel;
	}

	public void setUomLabel(String uomLabel) {
		this.uomLabel = uomLabel;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getFormatting() {
		return formatting;
	}

	public void setFormatting(String formatting) {
		this.formatting = formatting;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	@Override
	public String toString() {
		return "ValueDescriptor [name=" + name + ", min=" + min + ", max=" + max + ", type=" + type + ", uomLabel="
				+ uomLabel + ", defaultValue=" + defaultValue + ", formatting=" + formatting + ", labels="
				+ Arrays.toString(labels) + "]";
	}

}

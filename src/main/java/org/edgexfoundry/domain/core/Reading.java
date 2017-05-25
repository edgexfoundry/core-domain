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
package org.edgexfoundry.domain.core;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.edgexfoundry.domain.common.BaseObject;

@SuppressWarnings("serial")
@Document
public class Reading extends BaseObject implements Serializable {

	// timestamp of when pushed or exported out of EdgeX. Set to 0 if not pushed
	// yet
	private long pushed;

	// reference to ValueDescriptor name.
	private String name;

	// device/sensor data value
	private String value;

	// device name - replicated from event to allow easier reading queries
	// this should be considered an optional property, but will be filled when
	// the device is set on an event is created.
	private String device;

	public Reading() {
	}

	public Reading(String value) {
		this.value = value;
	}

	public Reading(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getPushed() {
		return pushed;
	}

	public void setPushed(long pushed) {
		this.pushed = pushed;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	@Override
	public String toString() {
		return "Reading [pushed=" + pushed + ", name=" + name + ", value=" + value + ", device=" + device + "]";
	}

}

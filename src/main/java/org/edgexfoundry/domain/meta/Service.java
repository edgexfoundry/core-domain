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
package org.edgexfoundry.domain.meta;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import org.edgexfoundry.domain.common.DescribedObject;

@SuppressWarnings("serial")
public abstract class Service extends DescribedObject implements Asset {

	// non-database identifier for a device service must be unique
	@Indexed(unique = true)
	private String name;

	// time in milliseconds that the device last provided any feedback or
	// responded to any request
	private long lastConnected;

	// time in milliseconds that the device last reported data to the core
	private long lastReported;

	// operational state - either enabled or disabled
	private OperatingState operatingState;
	
	// tags or other labels applied to the device service for search or other
	// identification needs
	private String[] labels;
	
	// address (MQTT topic, HTTP address, serial bus, etc.) for reaching the
	// service
	@DBRef
	private Addressable addressable;

	@Override
	public Addressable getAddressable() {
		return addressable;
	}

	@Override
	public void setAddressable(Addressable addressable) {
		this.addressable = addressable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getLastConnected() {
		return lastConnected;
	}

	public void setLastConnected(long lastConnected) {
		this.lastConnected = lastConnected;
	}

	public long getLastReported() {
		return lastReported;
	}

	public void setLastReported(long lastReported) {
		this.lastReported = lastReported;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public OperatingState getOperatingState() {
		return operatingState;
	}

	public void setOperatingState(OperatingState operatingState) {
		this.operatingState = operatingState;
	}

	@Override
	public String toString() {
		return "Service [operatingState=" + getOperatingState() + ", addressable="
				+ getAddressable() + "]";
	}
}

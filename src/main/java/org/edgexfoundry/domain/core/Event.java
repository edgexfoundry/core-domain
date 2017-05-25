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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import org.edgexfoundry.domain.common.BaseObject;

@SuppressWarnings("serial")
@Document
public class Event extends BaseObject implements Serializable {

	// timestamp of when pushed or exported out of EdgeX. Set to 0 if not pushed
	// yet
	private long pushed;

	// the generated database device id or device name identifier as a string
	// core data doesn't care how the event is associated to the event but
	// downstream systems must know what to look for
	private String device;

	@DBRef
	private List<Reading> readings;

	public Event(String device, List<Reading> readings) {
		super();
		this.readings = readings;
		this.device = device;
		updateReadingsDevice();
	}

	public Event(String device) {
		super();
		this.device = device;
	}

	// needed for Mongodb
	@SuppressWarnings("unused")
	private Event() {
	}

	public List<Reading> getReadings() {
		return readings;
	}

	public void setReadings(List<Reading> readings) {
		this.readings = readings;
		if (readings != null)
			readings.stream().forEach(r -> r.setDevice(this.device));
	}

	public void addReading(Reading reading) {
		if (this.readings == null)
			setReadings(new ArrayList<Reading>());
		this.readings.add(reading);
		reading.setDevice(this.device);
	}

	public void addReadings(List<Reading> readings) {
		if (this.readings == null)
			setReadings(new ArrayList<Reading>());
		this.readings.addAll(readings);
		readings.stream().forEach(r -> r.setDevice(this.device));
	}

	public void addReadings(Reading[] readings) {
		addReadings(Arrays.asList(readings));
	}

	public boolean removeReading(Reading reading) {
		if (this.readings == null)
			setReadings(new ArrayList<Reading>());
		return this.readings.remove(reading);
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
		updateReadingsDevice();
	}

	public long getPushed() {
		return pushed;
	}

	public void setPushed(long pushed) {
		this.pushed = pushed;
	}

	/**
	 * Set the pushed timestamp for this event and cascade the same timestamp to
	 * the associated readings
	 * 
	 * @param pushed
	 *            - timestamp
	 */
	public void markPushed(long pushed) {
		setPushed(pushed);
		for (Reading reading : readings) {
			reading.setPushed(pushed);
		}
	}

	private void updateReadingsDevice() {
		if ((this.readings != null) && (this.readings.size() > 0))
			this.readings.stream().forEach(r -> r.setDevice(this.device));
	}

	@Override
	public String toString() {
		return "Event [pushed=" + pushed + ", device=" + device + ", readings=" + readings + ", toString()="
				+ super.toString() + "]";
	}

}

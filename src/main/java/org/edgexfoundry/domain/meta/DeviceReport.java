/*******************************************************************************
 * Copyright 2016-2017 Dell Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * @microservice: core-domain library
 * @author: Jim White, Dell
 * @version: 1.0.0
 *******************************************************************************/

package org.edgexfoundry.domain.meta;

import java.util.Arrays;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.edgexfoundry.domain.common.BaseObject;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuppressWarnings("serial")
public class DeviceReport extends BaseObject {

  // non-database identifier for a device report - must be unique
  @Indexed(unique = true)
  private String name;

  // associated device name - should be a valid and unique device name
  private String device;

  // associated schedule event name - should be a valid and unique schedule event name
  private String event;

  // array of value descriptor names describing the types of data captured in the report
  private String[] expected;

  public DeviceReport(String name, String device, String event, String[] expected) {
    super();
    this.name = name;
    this.device = device;
    this.event = event;
    this.expected = expected;
  }

  @SuppressWarnings("unused")
  // used by spring container
  private DeviceReport() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getDevice() {
    return device;
  }

  public void setDevice(String device) {
    this.device = device;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public String[] getExpected() {
    return expected;
  }

  public void setExpected(String[] expected) {
    this.expected = expected;
  }

  @Override
  public String toString() {
    return "DeviceReport [name=" + name + ", device=" + device + ", event=" + event + ", expected="
        + Arrays.toString(expected) + ", toString()=" + super.toString() + "]";
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(name).append(device)
        .append(event).append(expected).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj))
      return false;
    DeviceReport other = (DeviceReport) obj;
    return propertyMatch(other);
  }

  private boolean propertyMatch(DeviceReport other) {
    if (!stringPropertyMatch(this.name, other.name))
      return false;
    if (!device.equals(other.device))
      return false;
    if (!event.equals(other.event))
      return false;
    return stringArrayPropertyMatch(expected, other.expected);
  }

}

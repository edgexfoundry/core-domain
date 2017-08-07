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

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.edgexfoundry.domain.common.DescribedObject;

@Document
@SuppressWarnings("serial")
public class Device extends DescribedObject implements Asset {

  // TODO - someday have a naming service for all types (Device, Service,
  // Profile, Addressable, ValueDescriptor, etc.). This naming service makes
  // sure the name is unique - potentially even across EdgeX instances in a
  // cluster - and can even generate a name with some help from the
  // originating service. Need to look into how Docker provides names to
  // containers and use a similar approach.

  // non-database identifier for a device - must be unique
  @Indexed(unique = true)
  private String name;

  // administrative state - either locked or unlocked (as reported by devices
  // or device services)
  private AdminState adminState;

  // operational state - either enabled or disabled (set by humans or systems)
  private OperatingState operatingState;

  // address (MQTT topic, HTTP address, serial bus, etc.) for the device
  @DBRef
  private Addressable addressable;

  // time in milliseconds that the device last provided any feedback or
  // responded to any request
  private long lastConnected;

  // time in milliseconds that the device last reported data to the core
  private long lastReported;

  // tags or other labels applied to the device for search or other
  // identification needs
  private String[] labels;

  // device service specific location information (such as a lat-long)
  private Object location;

  // owning device service (each device can have only one owning service)
  @DBRef
  private DeviceService service;

  // associated device profile that describes the device
  @DBRef
  private DeviceProfile profile;

  @Override
  public AdminState getAdminState() {
    return this.adminState;
  }

  @Override
  public void setAdminState(AdminState adminState) {
    this.adminState = adminState;

  }

  @Override
  public OperatingState getOperatingState() {
    return this.operatingState;
  }

  @Override
  public void setOperatingState(OperatingState opState) {
    this.operatingState = opState;

  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String name) {
    this.name = name;

  }

  @Override
  public long getLastConnected() {
    return this.lastConnected;
  }

  @Override
  public void setLastConnected(long lastConnected) {
    this.lastConnected = lastConnected;
  }

  @Override
  public long getLastReported() {
    return this.lastReported;
  }

  @Override
  public void setLastReported(long lastReported) {
    this.lastReported = lastReported;

  }

  @Override
  public Addressable getAddressable() {
    return this.addressable;
  }

  @Override
  public void setAddressable(Addressable addressable) {
    this.addressable = addressable;
  }

  public String[] getLabels() {
    return labels;
  }

  public void setLabels(String[] labels) {
    this.labels = labels;
  }

  public Object getLocation() {
    return location;
  }

  public void setLocation(Object location) {
    this.location = location;
  }

  public DeviceService getService() {
    return service;
  }

  public void setService(DeviceService service) {
    this.service = service;
  }

  public DeviceProfile getProfile() {
    return profile;
  }

  public void setProfile(DeviceProfile profile) {
    this.profile = profile;
  }

  @Override
  public String toString() {
    return "Device [name=" + name + ", adminState=" + adminState + ", operatingState="
        + operatingState + ", addressable=" + addressable + ", lastConnected=" + lastConnected
        + ", lastReported=" + lastReported + ", labels=" + Arrays.toString(labels) + ", location="
        + location + ", service=" + service + ", profile=" + profile + "]";
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(name).append(adminState)
        .append(operatingState).append(addressable).append(lastConnected).append(lastReported)
        .append(labels).append(location).append(service).append(profile).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj))
      return false;
    Device other = (Device) obj;
    return propertyMatch(other);
  }

  private boolean propertyMatch(Device other) {
    if (!stringPropertyMatch(this.name, other.name))
      return false;
    if (adminState != other.adminState)
      return false;
    if (operatingState != other.operatingState)
      return false;
    if (!objectPropertyMatch(this.addressable, other.addressable))
      return false;
    if (lastConnected != other.lastConnected)
      return false;
    if (lastReported != other.lastReported)
      return false;
    if (!stringArrayPropertyMatch(labels, other.labels))
      return false;
    if (!location.equals(other.location))
      return false;
    if (!objectPropertyMatch(this.service, other.service))
      return false;
    return objectPropertyMatch(this.profile, other.profile);
  }

}

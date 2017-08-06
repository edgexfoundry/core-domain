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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.edgexfoundry.domain.common.BaseObject;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuppressWarnings("serial")
public class ProvisionWatcher extends BaseObject {

  // non-database identifier for a provision watcher - must be unique
  @Indexed(unique = true)

  // unique name and identifier of the addressable
  private String name;

  /**
   * / set of key value pairs that identify type of of address (MAC, HTTP,...) and address to watch
   * for (00-05-1B-A1-99-99, 10.0.0.1,...)
   */
  private Map<String, String> identifiers;

  @DBRef
  // device profile that should be applied to the devices available at the
  // identifier addresses
  private DeviceProfile profile;

  @DBRef
  // device service that owns the watcher
  private DeviceService service;

  // operational state - either enabled or disabled
  private OperatingState operatingState = OperatingState.ENABLED;

  public ProvisionWatcher() {}

  public ProvisionWatcher(String name) {
    this.name = name;
  }

  public ProvisionWatcher(String name, DeviceProfile profile, Map<String, String> identifiers) {
    this(name);
    this.profile = profile;
    this.identifiers = identifiers;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Map<String, String> getIdentifiers() {
    if (identifiers == null)
      this.identifiers = new HashMap<>();
    return identifiers;
  }

  public void setIdentifiers(Map<String, String> identifiers) {
    this.identifiers = identifiers;
  }

  public DeviceProfile getProfile() {
    return profile;
  }

  public void setProfile(DeviceProfile profile) {
    this.profile = profile;
  }

  public void addIdentifier(String key, String value) {
    if (identifiers == null)
      this.identifiers = new HashMap<>();
    this.identifiers.put(key, value);
  }

  public void removeIdentifier(String key) {
    if (identifiers != null)
      this.identifiers.remove(key);
  }

  public DeviceService getService() {
    return service;
  }

  public void setService(DeviceService service) {
    this.service = service;
  }

  public OperatingState getOperatingState() {
    return operatingState;
  }

  public void setOperatingState(OperatingState operatingState) {
    this.operatingState = operatingState;
  }

  @Override
  public String toString() {
    return "ProvisionWatcher [id=" + this.getId() + ", name=" + name + ", identifiers="
        + identifiers + ", service=" + service + ", profile=" + profile + ", operatingState="
        + operatingState.name() + "]";
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(name).append(identifiers)
        .append(service).append(profile).append(operatingState).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj))
      return false;
    ProvisionWatcher other = (ProvisionWatcher) obj;
    return propertyMatch(other);
  }

  private boolean propertyMatch(ProvisionWatcher other) {
    if (!stringPropertyMatch(name, other.name))
      return false;
    if (!objectPropertyMatch(identifiers, other.identifiers))
      return false;
    if (!objectPropertyMatch(service, other.service))
      return false;
    if (!objectPropertyMatch(profile, other.profile))
      return false;
    return (operatingState == other.operatingState);
  }

}

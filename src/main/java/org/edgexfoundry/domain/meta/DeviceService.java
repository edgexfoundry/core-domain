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

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@SuppressWarnings("serial")
public class DeviceService extends Service {

  // administrative state - either locked or unlocked
  private AdminState adminState;

  public AdminState getAdminState() {
    return adminState;
  }

  public void setAdminState(AdminState adminState) {
    this.adminState = adminState;
  }

  @Override
  public String toString() {
    return "DeviceService [adminState=" + adminState + ", operatingState=" + getOperatingState()
        + ", addressable=" + getAddressable() + "]";
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(adminState).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj))
      return false;
    DeviceService other = (DeviceService) obj;
    return propertyMatch(other);
  }

  private boolean propertyMatch(DeviceService other) {
    return adminState == other.adminState;
  }

}

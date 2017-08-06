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

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @deprecated (because it was unfinished for the rest of EdgeX)
 */
@Deprecated
@Document
@SuppressWarnings("serial")
public class DeviceManager extends Device {

  // indicator whether this manager also collects data and acts like a device as well as a manager
  private boolean deviceToo;

  // list of associated devices under management
  @DBRef
  private List<Device> devices;

  // list of associated device managers also under management
  @DBRef
  private List<DeviceManager> managers;

  public boolean isDeviceToo() {
    return deviceToo;
  }

  public void setDeviceToo(boolean deviceToo) {
    this.deviceToo = deviceToo;
  }

  public List<Device> getDevices() {
    return devices;
  }

  public void setDevices(List<Device> devices) {
    this.devices = devices;
  }

  public void addDevice(Device device) {
    if (this.devices == null)
      setDevices(new ArrayList<Device>());
    this.devices.add(device);
  }

  public boolean removeDevice(Device device) {
    if (this.devices == null)
      setDevices(new ArrayList<Device>());
    return this.devices.remove(device);
  }

  public List<DeviceManager> getManagers() {
    return managers;
  }

  public void setManagers(List<DeviceManager> managers) {
    this.managers = managers;
  }

  public void addManager(DeviceManager manager) {
    if (this.managers == null)
      setManagers(new ArrayList<DeviceManager>());
    this.managers.add(manager);
  }

  public boolean removeManager(DeviceManager manager) {
    if (this.managers == null)
      setManagers(new ArrayList<DeviceManager>());
    return this.managers.remove(manager);
  }

  @Override
  public String toString() {
    return "DeviceManager [deviceToo=" + deviceToo + ", devices=" + devices + ", managers="
        + managers + ", toString()=" + super.toString() + "]";
  }

}

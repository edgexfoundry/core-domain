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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.edgexfoundry.domain.meta.AdminState;
import org.edgexfoundry.domain.meta.Device;
import org.edgexfoundry.domain.meta.OperatingState;
import org.junit.Before;
import org.junit.Test;

public class DeviceTest {

  static final String TEST_NAME = "TEST_DEVICE.NAME";
  static final String TEST_DESCRIPTION = "TEST_DESCRIPTION";
  static final AdminState TEST_ADMIN = AdminState.UNLOCKED;
  static final OperatingState TEST_OP = OperatingState.ENABLED;
  static final long TEST_LAST_CONNECTED = 1000000;
  static final long TEST_LAST_REPORTED = 1000000;
  static final String[] TEST_LABELS = {"MODBUS", "TEMP"};
  static final String TEST_LOCATION = "{40lat;45long}";

  private Device d, d2;

  @Before
  public void setup() {
    d = new Device();
    d.setAdminState(TEST_ADMIN);
    d.setDescription(TEST_DESCRIPTION);
    d.setLabels(TEST_LABELS);
    d.setLastConnected(TEST_LAST_CONNECTED);
    d.setLastReported(TEST_LAST_REPORTED);
    d.setLocation(TEST_LOCATION);
    d.setName(TEST_NAME);
    d.setOperatingState(TEST_OP);
    d.setAddressable(new Addressable("z", Protocol.HTTP, "b", "c", 0));
    d.setService(new DeviceService());
    d.getService().setName("foo");
    d.setProfile(new DeviceProfile());
    d.getProfile().setName("boo");
    d2 = new Device();
    d2.setAdminState(TEST_ADMIN);
    d2.setDescription(TEST_DESCRIPTION);
    d2.setLabels(TEST_LABELS);
    d2.setLastConnected(TEST_LAST_CONNECTED);
    d2.setLastReported(TEST_LAST_REPORTED);
    d2.setLocation(TEST_LOCATION);
    d2.setName(TEST_NAME);
    d2.setOperatingState(TEST_OP);
    d2.setAddressable(new Addressable("z", Protocol.HTTP, "b", "c", 0));
    d2.setService(new DeviceService());
    d2.getService().setName("foo");
    d2.setProfile(new DeviceProfile());
    d2.getProfile().setName("boo");
  }

  @Test
  public void testEquals() {
    assertTrue("Different devices with same values not equal", d.equals(d2));
  }

  @Test
  public void testEqualsWithSame() {
    assertTrue("Same devices are not equal", d.equals(d));
  }

  @Test
  public void testNotEquals() {
    d.setCreated(3456L);
    assertFalse("Devices with different base values are equal", d.equals(d2));
  }

  @Test
  public void testEqualWithDifferentName() {
    d2.setName("foo");
    assertFalse("Devices with different names values are equal", d.equals(d2));
  }

  @Test
  public void testEqualWithDifferentAdminState() {
    d2.setAdminState(AdminState.LOCKED);
    assertFalse("Devices with different admin states values are equal", d.equals(d2));
  }

  @Test
  public void testEqualWithDifferentOpState() {
    d2.setOperatingState(OperatingState.DISABLED);
    assertFalse("Devices with different op states values are equal", d.equals(d2));
  }

  @Test
  public void testEqualsWithBothAddressableNull() {
    d.setAddressable(null);
    d2.setAddressable(null);
    assertTrue("Devices with null addressables are not equal", d.equals(d2));
  }

  @Test
  public void testEqualWithOneNonNullAddressable() {
    d.setAddressable(null);
    assertFalse("Devices with null addressable are equal to device with non null addressable",
        d.equals(d2));
  }

  @Test
  public void testEqualWithDifferentAddressable() {
    d.setAddressable(new Addressable("a", Protocol.HTTP, "b", "c", 0));
    assertFalse("Devices with different addressables are equal", d.equals(d2));
  }

  @Test
  public void testEqualWitDifferentLastConnected() {
    d2.setLastConnected(5678L);
    assertFalse("Devices with different last connected values are equal", d.equals(d2));
  }

  @Test
  public void testEqualWitDifferentLastReported() {
    d2.setLastReported(5678L);
    assertFalse("Devices with different last reported values are equal", d.equals(d2));
  }

  @Test
  public void testEqualsWithDifferntLabels() {
    String[] newLabels = {"newlabel"};
    d2.setLabels(newLabels);
    assertFalse("Devices with different labels seen as equal", d.equals(d2));
  }

  @Test
  public void testEqualWitDifferentLocation() {
    d2.setLocation("foobar");
    assertFalse("Devices with different location values are equal", d.equals(d2));
  }

  @Test
  public void testEqualsWithBothServicesNull() {
    d.setService(null);
    d2.setService(null);
    assertTrue("Devices with null as services are not equal", d.equals(d2));
  }

  @Test
  public void testEqualsWithOneNonNullService() {
    d.setService(null);
    assertFalse("Devices with null service are equal to device with non null servce", d.equals(d2));
  }

  @Test
  public void testEqualWithDifferentServices() {
    d.setService(new DeviceService());
    d.getService().setName("boo");
    assertFalse("Devices with different services are equal", d.equals(d2));
  }

  @Test
  public void testEqualsWithBothNullProfiles() {
    d.setProfile(null);
    d2.setProfile(null);
    assertTrue("Devices with null profiles are not equal", d.equals(d2));
  }

  @Test
  public void testEqualsWithOneNonNullProfile() {
    d.setProfile(null);
    assertFalse("Devices with null profile are equal to device with non null profile",
        d.equals(d2));
  }

  @Test
  public void testEqualWithDifferentProfiles() {
    d2.getProfile().setName("foo");
    assertFalse("Devices with different profiles are equal", d.equals(d2));
  }

  @Test
  public void testHashCode() {
    assertTrue("hashcode not hashing properly", d.hashCode() != 0);
  }

  @Test
  public void testToString() {
    d.toString();
  }

}

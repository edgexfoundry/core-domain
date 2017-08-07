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

import org.junit.Before;
import org.junit.Test;

public class ServiceTest {

  private static final String TEST_SERVICE_NAME = "test service";
  private static final OperatingState TEST_OP = OperatingState.ENABLED;
  private static final long TEST_LAST_CONNECTED = 1000000;
  private static final long TEST_LAST_REPORTED = 1000000;
  private static final String[] TEST_LABELS = {"MODBUS", "TEMP"};
  private static final String TEST_ADDR_NAME = "TEST_ADDR.NAME";
  private static final Protocol TEST_PROTOCOL = Protocol.HTTP;
  private static final String TEST_ADDRESS = "localhost";
  private static final int TEST_PORT = 48089;
  private static final String TEST_PATH = "/api/v1/device";

  private Service s, s2;
  private Addressable a;

  @Before
  public void setup() {
    a = new Addressable(TEST_ADDR_NAME, TEST_PROTOCOL, TEST_ADDRESS, TEST_PATH, TEST_PORT);
    // must use DeviceService since Service is abstract
    s = new DeviceService();
    s.setName(TEST_SERVICE_NAME);
    s.setLastConnected(TEST_LAST_CONNECTED);
    s.setLastReported(TEST_LAST_REPORTED);
    s.setOperatingState(TEST_OP);
    s.setLabels(TEST_LABELS);
    s.setAddressable(a);
    s2 = new DeviceService();
    s2 = new DeviceService();
    s2.setName(TEST_SERVICE_NAME);
    s2.setLastConnected(TEST_LAST_CONNECTED);
    s2.setLastReported(TEST_LAST_REPORTED);
    s2.setOperatingState(TEST_OP);
    s2.setLabels(TEST_LABELS);
    s2.setAddressable(a);
  }

  @Test
  public void testEquals() {
    assertTrue("Different service with same values not equal", s.equals(s2));
  }

  @Test
  public void testEqualsWithSame() {
    assertTrue("Same service are not equal", s.equals(s));
  }

  @Test
  public void testNotEquals() {
    s.setCreated(3456L);
    assertFalse("Services with different base values are equal", s.equals(s2));
  }

  @Test
  public void testEqualWithDifferentName() {
    s2.setName("foo");
    assertFalse("Services with different names values are equal", s.equals(s2));
  }

  @Test
  public void testEqualWithDifferentOpState() {
    s2.setOperatingState(OperatingState.DISABLED);
    assertFalse("Services with different op state values are equal", s.equals(s2));
  }

  @Test
  public void testEqualWithDifferentAddressable() {
    Addressable a2 = new Addressable("foo", TEST_PROTOCOL, TEST_ADDRESS, TEST_PATH, TEST_PORT);
    s2.setAddressable(a2);
    assertFalse("Services with different addressable are equal", s.equals(s2));
  }

  @Test
  public void testEqualWitDifferentLastConnected() {
    s2.setLastConnected(5678L);
    assertFalse("Services with different last connected values are equal", s.equals(s2));
  }

  @Test
  public void testEqualWitDifferentLastReported() {
    s2.setLastReported(5678L);
    assertFalse("Services with different last reported values are equal", s.equals(s2));
  }

  @Test
  public void testEqualsWithDifferntLabels() {
    String[] newLabels = {"newlabel"};
    s2.setLabels(newLabels);
    assertFalse("Services with different labels seen as equal", s.equals(s2));
  }

  @Test
  public void testHashCode() {
    assertTrue("hashcode not hashing properly", s.hashCode() != 0);
  }

}

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

public class DeviceReportTest {

  private static final String TEST_RPT_NAME = "Test Report.NAME";
  private static final String TEST_DEVICE = "Test device";
  private static final String TEST_EVENT = "Test event";
  private static final String[] TEST_EXPECTED = {"vD1", "vD2"};

  DeviceReport d, d2;

  @Before
  public void setup() {
    d = new DeviceReport(TEST_RPT_NAME, TEST_DEVICE, TEST_EVENT, TEST_EXPECTED);
    d2 = new DeviceReport(TEST_RPT_NAME, TEST_DEVICE, TEST_EVENT, TEST_EXPECTED);
  }

  @Test
  public void testEquals() {
    assertTrue("Different reports with same values not equal", d.equals(d2));
  }

  @Test
  public void testEqualsWithSame() {
    assertTrue("Same reports are not equal", d.equals(d));
  }

  @Test
  public void testNotEquals() {
    d.setCreated(3456L);
    assertFalse("Reports with different base values are equal", d.equals(d2));
  }

  @Test
  public void testEqualWithDifferentName() {
    d2.setName("foo");
    assertFalse("Reports with different names values are equal", d.equals(d2));
  }

  @Test
  public void testEqualWithDifferentDevices() {
    d2.setDevice("foo");
    assertFalse("Reports with different devices values are equal", d.equals(d2));
  }

  @Test
  public void testEqualWithDifferentEvents() {
    d2.setEvent("foo");
    assertFalse("Reports with different events values are equal", d.equals(d2));
  }

  @Test
  public void testEqualWithDifferentExpected() {
    String[] expected = {"foo"};
    d2.setExpected(expected);
    assertFalse("Reports with different expected values are equal", d.equals(d2));
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

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

public class ScheduleEventTest {

  private static final String TEST_SERVICE_NAME = "test service";
  private static final String TEST_SCHEDULE_EVENT_NAME = "test schedule event";
  private static final String TEST_SCHEDULE_NAME = "test schedule";
  private static final String TEST_PARAMS = "{key1:value1}";
  static final String TEST_ADDR_NAME = "TEST_ADDR.NAME";
  static final Protocol TEST_PROTOCOL = Protocol.HTTP;
  static final String TEST_ADDRESS = "localhost";
  static final int TEST_PORT = 48089;
  static final String TEST_PATH = "/api/v1/device";

  private ScheduleEvent s;
  private ScheduleEvent s2;
  private Addressable a;

  @Before
  public void setup() {
    a = new Addressable(TEST_ADDR_NAME, TEST_PROTOCOL, TEST_ADDRESS, TEST_PATH, TEST_PORT);
    s = new ScheduleEvent(TEST_SCHEDULE_EVENT_NAME, a, TEST_PARAMS, TEST_SCHEDULE_NAME,
        TEST_SERVICE_NAME);
    s2 = new ScheduleEvent(TEST_SCHEDULE_EVENT_NAME, a, TEST_PARAMS, TEST_SCHEDULE_NAME,
        TEST_SERVICE_NAME);
  }

  @Test
  public void testEquals() {
    assertTrue("Different schedule events with same values not equal", s.equals(s2));
  }

  @Test
  public void testEqualsWithSame() {
    assertTrue("Same schedule events are not equal", s.equals(s));
  }

  @Test
  public void testNotEquals() {
    s.setCreated(3456L);
    assertFalse("Schedule Events with different base values are equal", s.equals(s2));
  }

  @Test
  public void testEqualWithDifferentName() {
    s2.setName("foo");
    assertFalse("Schedule Events with different names values are equal", s.equals(s2));
  }

  @Test
  public void testEqualWithDifferentAddressable() {
    Addressable a2 = new Addressable("foo", TEST_PROTOCOL, TEST_ADDRESS, TEST_PATH, TEST_PORT);
    s2.setAddressable(a2);
    assertFalse("Schedule Events with different addressable are equal", s.equals(s2));
  }

  @Test
  public void testEqualWithDifferentParams() {
    s2.setParameters("foo");
    assertFalse("Schedule Events with different parameter values are equal", s.equals(s2));
  }

  @Test
  public void testEqualWithDifferentService() {
    s2.setService("foo");
    assertFalse("Schedule Events with different service are equal", s.equals(s2));
  }

  @Test
  public void testEqualWithDifferentSchedule() {
    s2.setSchedule("foo");
    assertFalse("Schedule Events with different schedule are equal", s.equals(s2));
  }

  @Test
  public void testHashCode() {
    assertTrue("hashcode not hashing properly", s.hashCode() != 0);
  }

  @Test
  public void testToString() {
    s.toString();
  }

}

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

public class DeviceServiceTest {

  DeviceService s;
  DeviceService s2;
  Addressable a;

  @Before
  public void setup() {
    s = new DeviceService();
    s.setAdminState(AdminState.UNLOCKED);
    s2 = new DeviceService();
    s2.setAdminState(AdminState.UNLOCKED);
  }

  @Test
  public void testEquals() {
    assertTrue("Different services with same values not equal", s.equals(s2));
  }

  @Test
  public void testEqualsWithSame() {
    assertTrue("Same services are not equal", s.equals(s));
  }

  @Test
  public void testNotEquals() {
    s.setCreated(3456L);
    assertFalse("services with different base values are equal", s.equals(s2));
  }

  @Test
  public void testEqualWithDifferentAdminState() {
    s2.setAdminState(AdminState.LOCKED);
    assertFalse("Services with different admin state values are equal", s.equals(s2));
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

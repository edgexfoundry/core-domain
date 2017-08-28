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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ProvisionWatcherTest {

  private static final String TEST_NAME = "test pw";

  private ProvisionWatcher w;
  private ProvisionWatcher w2;

  @Before
  public void setup() {
    Map<String, String> ids = new HashMap<>();
    ids.put("z", "y");
    DeviceProfile p = new DeviceProfile();
    DeviceService s = new DeviceService();
    w = new ProvisionWatcher(TEST_NAME);
    w.setIdentifiers(ids);
    w.setProfile(p);
    w.setService(s);
    w.setOperatingState(OperatingState.ENABLED);
    w2 = new ProvisionWatcher(TEST_NAME);
    w2.setIdentifiers(ids);
    w2.setProfile(p);
    w2.setService(s);
    w2.setOperatingState(OperatingState.ENABLED);
  }


  @Test
  public void testAddIdentifierStartingWithNull() {
    w.setIdentifiers(null);
    w.addIdentifier("a", "b");
    assertEquals("ProvisionWatcher identifers not added correctly", "b",
        w.getIdentifiers().get("a"));
  }

  @Test
  public void testAddIdentifier() {
    w.addIdentifier("a", "b");
    assertEquals("ProvisionWatcher identifers not added correctly", "b",
        w.getIdentifiers().get("a"));
  }

  @Test
  public void testRemoveIdentifierStartingWithNull() {
    w.setIdentifiers(null);
    w.removeIdentifier("z");
    assertTrue("ProvisionWatcher remove identifier not working properly",
        w.getIdentifiers().isEmpty());
  }

  @Test
  public void testRemoveIdentifier() {
    w.removeIdentifier("z");
    assertTrue("ProvisionWatcher remove identifier not working properly",
        w.getIdentifiers().isEmpty());
  }

  @Test
  public void testEquals() {
    assertTrue("Different watchers with same values not equal", w.equals(w2));
  }

  @Test
  public void testEqualsWithSame() {
    assertTrue("Same watcher are not equal", w.equals(w));
  }

  @Test
  public void testNotEquals() {
    w2.setCreated(3456L);
    assertFalse("Watchers with different base values are equal", w.equals(w2));
  }

  @Test
  public void testEqualWithDifferentName() {
    w2.setName("foo");
    assertFalse("Watchers with different names values are equal", w.equals(w2));
  }

  @Test
  public void testEqualWithDifferentIdentifiers() {
    Map<String, String> ids = new HashMap<>();
    ids.put("a", "b");
    w2.setIdentifiers(ids);
    assertFalse("Watchers with different identifer values are equal", w.equals(w2));
  }

  @Test
  public void testEqualWithDifferentProfiles() {
    DeviceProfile p = new DeviceProfile();
    p.setName("foo");
    w2.setProfile(p);
    assertFalse("Watchers with different profiles are equal", w.equals(w2));
  }

  @Test
  public void testEqualWithDifferentServices() {
    DeviceService s = new DeviceService();
    s.setName("foo");
    w2.setService(s);
    assertFalse("Watchers with different services are equal", w.equals(w2));
  }

  @Test
  public void testEqualWithDifferentOpState() {
    w2.setOperatingState(OperatingState.DISABLED);
    assertFalse("Watchers with different op states are equal", w.equals(w2));
  }

  @Test
  public void testHashCode() {
    assertTrue("hashcode not hashing properly", w.hashCode() != 0);
  }

  @Test
  public void testToString() {
    w.toString();
  }

}

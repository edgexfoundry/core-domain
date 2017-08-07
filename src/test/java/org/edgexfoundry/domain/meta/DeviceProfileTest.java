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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DeviceProfileTest {

  private static final String TEST_PROFILE_NAME = "Test Profile.NAME";
  private static final String TEST_MAUFACTURER = "Test Manufacturer";
  private static final String TEST_MODEL = "Test Model";
  private static final String[] TEST_LABELS = {"labe1", "label2"};
  private static final String TEST_DESCRIPTION = "Test Description";
  private static final String TEST_OBJ = "{key1:value1, key2:value2}";
  private static final List<DeviceObject> TEST_DEVICE_RES = new ArrayList<DeviceObject>();

  private Command command;
  private DeviceProfile profile;
  private DeviceProfile profile2;

  @Before
  public void setup() {
    command = new Command();
    profile = new DeviceProfile();
    profile.setDescription(TEST_DESCRIPTION);
    profile.setLabels(TEST_LABELS);
    profile.setManufacturer(TEST_MAUFACTURER);
    profile.setModel(TEST_MODEL);
    profile.setName(TEST_PROFILE_NAME);
    profile.setObjects(TEST_OBJ);
    profile2 = new DeviceProfile();
    profile2.setDescription(TEST_DESCRIPTION);
    profile2.setLabels(TEST_LABELS);
    profile2.setManufacturer(TEST_MAUFACTURER);
    profile2.setModel(TEST_MODEL);
    profile2.setName(TEST_PROFILE_NAME);
    profile2.setObjects(TEST_OBJ);
    profile2.setDeviceResources(TEST_DEVICE_RES);
  }

  @Test
  public void testAddCommand() {
    assertNull(profile.getCommands());
    profile.addCommand(command);
    assertTrue(profile.getCommands().contains(command));
    assertEquals("Add command failed to preserve collection", 1, profile.getCommands().size());
  }

  @Test
  public void testAddCommandsWhereCommandsNotNull() {
    profile.setCommands(new ArrayList<Command>());
    assertNotNull(profile.getCommands());
    profile.addCommand(command);
    assertTrue(profile.getCommands().contains(command));
    assertEquals("Add command failed to preserve collection", 1, profile.getCommands().size());
  }

  @Test
  public void testRemoveCommand() {
    profile.addCommand(command);
    assertTrue(profile.getCommands().contains(command));
    profile.removeCommand(command);
    assertTrue("Remove command failed to preserve collection", profile.getCommands().isEmpty());
  }

  @Test
  public void testRemoveCommandWithNoCommands() {
    assertNull(profile.getCommands());
    profile.removeCommand(command);
    assertTrue("Remove command failed to preserve collection", profile.getCommands().isEmpty());
  }

  @Test
  public void testHashCode() {
    assertTrue("hashcode not hashing properly", profile.hashCode() != 0);
  }

  @Test
  public void testEquals() {
    assertTrue("Different profiles with same values not equal", profile.equals(profile2));
  }

  @Test
  public void testEqualsWithSame() {
    assertTrue("Same profiles are not equal", profile.equals(profile));
  }

  @Test
  public void testNotEquals() {
    profile.setCreated(3456L);
    assertFalse("Profiles with different base values are equal", profile.equals(profile2));
  }

  @Test
  public void testNotEqualBasedOnNullCommands() {
    profile2.setCommands(new ArrayList<Command>());
    assertFalse("profile with null commands is equal to profile with command list",
        profile.equals(profile2));
  }

  @Test
  public void testNotEqualBasedOnCommandLists() {
    List<Command> commands = new ArrayList<Command>();
    commands.add(command);
    profile.setCommands(commands);
    assertFalse("profiles are equal with different command sets", profile.equals(profile2));
  }

  @Test
  public void testNotEqualWithDifferentLabels() {
    String[] newLabels = {"test"};
    profile2.setLabels(newLabels);
    assertFalse("profiles with different labels are equal", profile.equals(profile2));
  }

  @Test
  public void testNotEqualsWithNullManufacturer() {
    profile.setManufacturer(null);
    assertFalse("profile with null as manufacturer is equal to profile with manufacture",
        profile.equals(profile2));
  }

  @Test
  public void testNotEqualsWithDifferentManufacturer() {
    profile.setManufacturer("different");
    assertFalse("profiles with different manufacturers are equal", profile.equals(profile2));
  }

  @Test
  public void testNotEqualsWithNullModel() {
    profile.setModel(null);
    assertFalse("profile with null as model is equal to profile with model",
        profile.equals(profile2));
  }

  @Test
  public void testNotEqualsWithDifferentModel() {
    profile.setModel("different");
    assertFalse("profiles with different models are equal", profile.equals(profile2));
  }

  @Test
  public void testNotEqualsWithNullName() {
    profile.setName(null);
    assertFalse("profile with null as name is equal to profile with name",
        profile.equals(profile2));
  }

  @Test
  public void testNotEqualsWithDifferentName() {
    profile.setName("different");
    assertFalse("profiles with different names are equal", profile.equals(profile2));
  }

  @Test
  public void testNotEqualWithNullObjects() {
    profile.setObjects(null);
    assertFalse("profiles with different null objects are equal to profile with objects",
        profile.equals(profile2));
  }

  @Test
  public void testNotEqualWithDifferentObjects() {
    String[] newObjects = {"test"};
    profile2.setObjects(newObjects);
    assertFalse("profiles with different objects are equal", profile.equals(profile2));
  }

  @Test
  public void testToString() {
    profile.toString();
  }
}

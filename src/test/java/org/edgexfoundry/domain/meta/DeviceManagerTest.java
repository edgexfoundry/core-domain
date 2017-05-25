/*******************************************************************************
 * Copyright 2016-2017 Dell Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @microservice:  core-domain library
 * @author: Jim White, Dell
 * @version: 1.0.0
 *******************************************************************************/
package org.edgexfoundry.domain.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DeviceManagerTest {

	private DeviceManager manager1, manager2;
	private Device device;

	@Before
	public void setup() {
		manager1 = new DeviceManager();
		manager2 = new DeviceManager();
		device = new Device();
	}

	@Test
	public void testAddManager() {
		assertNull(manager1.getManagers());
		manager1.addManager(manager2);
		assertTrue(manager1.getManagers().contains(manager2));
		assertEquals("Add manager failed to preserve collection", 1, manager1.getManagers().size());
	}

	@Test
	public void testRemoveManager() {
		manager1.addManager(manager2);
		assertTrue(manager1.getManagers().contains(manager2));
		manager1.removeManager(manager2);
		assertTrue("Remove manager failed to preserve collection", manager1.getManagers().isEmpty());
	}

	@Test
	public void testAddDevice() {
		assertNull(manager1.getDevices());
		manager1.addDevice(device);
		assertTrue(manager1.getDevices().contains(device));
		assertEquals("Add device failed to preserve collection", 1, manager1.getDevices().size());
	}

	@Test
	public void testRemoveDevice() {
		manager1.addDevice(device);
		assertTrue(manager1.getDevices().contains(device));
		manager1.removeDevice(device);
		assertTrue("Remove device failed to preserve collection", manager1.getDevices().isEmpty());
	}
}

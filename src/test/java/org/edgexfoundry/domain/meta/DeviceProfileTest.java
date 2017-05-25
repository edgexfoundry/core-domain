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

public class DeviceProfileTest {
	
	Command command;
	DeviceProfile profile;
	
	@Before
	public void setup(){
		command = new Command();
		profile = new DeviceProfile();
	}
	
	@Test
	public void testAddCommand(){
		assertNull(profile.getCommands());
		profile.addCommand(command);
		assertTrue(profile.getCommands().contains(command));
		assertEquals("Add command failed to preserve collection", 1, profile.getCommands().size());
	}
	
	@Test
	public void testRemoveCommand(){
		profile.addCommand(command);
		assertTrue(profile.getCommands().contains(command));
		profile.removeCommand(command);
		assertTrue("Remove command failed to preserve collection", profile.getCommands().isEmpty());
	}

}

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
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import org.edgexfoundry.domain.meta.ProvisionWatcher;

public class ProvisionWatcherTest {

	private ProvisionWatcher watcher;

	@Before
	public void setup() {
		Map<String, String> ids = new HashMap<>();
		ids.put("z", "y");
		watcher = new ProvisionWatcher("testpw");
		watcher.setIdentifiers(ids);
	}

	@Test
	public void testAddIdentifier() {
		watcher.addIdentifier("a", "b");
		assertEquals("ProvisionWatcher identifers not added correctly", "b", watcher.getIdentifiers().get("a"));
	}

	@Test
	public void testRemoveIdentifier() {
		watcher.removeIdentifier("z");
		assertTrue("ProvisionWatcher remove identifier not working properly", watcher.getIdentifiers().isEmpty());
	}

}

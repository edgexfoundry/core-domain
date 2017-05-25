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
package org.edgexfoundry.domain.common;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import org.edgexfoundry.domain.core.Event;

/**
 * Unit test methods that go beyond simple getter/setter in BaseObject
 */
public class BaseObjectTest {

	private BaseObject bo1;
	private BaseObject bo2;
	private BaseObject bo3;

	@Before
	public void setup() {
		// create an instance of Event - since BaseObject is abstract
		bo1 = new Event("test device");
		bo2 = new Event("test device");
		bo3 = new Event("test device");
		bo1.setCreated(System.currentTimeMillis());
		bo3.setCreated(System.currentTimeMillis());
		bo2.setCreated(System.currentTimeMillis() + 1000);
	}

	@Test
	public void testCompare() {
		assertEquals("Base object compare to method not working propery", 1, bo1.compareTo(bo2));
		assertEquals("Base object compare to method not working propery", -1, bo2.compareTo(bo1));
		assertEquals("Base object compare to method not working propery", -1, bo1.compareTo(bo3));
	}

}

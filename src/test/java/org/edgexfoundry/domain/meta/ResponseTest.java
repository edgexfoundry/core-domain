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

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ResponseTest {

	private static final String TEST_CODE = "200";
	private static final String TEST_DECRIPTION = "ok";
	private static final String TEST_VALUE1 = "temperature";
	private static final String TEST_VALUE2 = "humidity";
	private Response r;

	@Before
	public void setup() {
		List<String> expValues = new ArrayList<>();
		expValues.add(TEST_VALUE1);
		expValues.add(TEST_VALUE2);
		r = new Response(TEST_CODE, TEST_DECRIPTION, expValues);
	}

	@Test
	public void testAddExpectedValue() {
		r.addExpectedValue("moisture");
		assertEquals("Expected values list was not added to appropriately", 3, r.getExpectedValues().size());
	}

	@Test
	public void testRemoveExpectedValue() {
		r.removeExpectedValue(TEST_VALUE1);
		assertEquals("Expected value was not removed from list appropriately", 1, r.getExpectedValues().size());
	}

}

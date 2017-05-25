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

public class ActionTest {

	private static final String TEST_CODE = "200";
	private static final String TEST_DECRIPTION = "ok";
	private static final String TEST_VALUE1 = "temperature";
	private static final String TEST_VALUE2 = "humidity";
	private static final String TEST_CODE2 = "404";
	private static final String TEST_DECRIPTION2 = "not found";

	private Response r1, r2;
	private Action a;

	@Before
	public void setup() {
		r1 = new Response(TEST_CODE, TEST_DECRIPTION);
		r1.addExpectedValue(TEST_VALUE1);
		r1.addExpectedValue(TEST_VALUE2);
		r2 = new Response(TEST_CODE2, TEST_DECRIPTION2);
		a = new Get();
		List<Response> responses = new ArrayList<>();
		responses.add(r1);
		responses.add(r2);
		a.setResponses(responses);
	}

	@Test
	public void testAddResponse() {
		a.addResponse(new Response("foo", "bar"));
		assertEquals("Response not added to action correctly", 3, a.getResponses().size());
	}

	@Test
	public void testRemoveResponse() {
		a.removeResponse(r1);
		assertEquals("Response was not removed from action correctly", 1, a.getResponses().size());
	}

}

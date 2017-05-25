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

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PutTest {

	private static final String PARAM1 = "param1";
	private static final String PARAM2 = "param2";
	private Put p;

	@Before
	public void setup() {
		p = new Put();
		List<String> params = new ArrayList<>();
		params.add(PARAM1);
		p.setParameterNames(params);
	}

	@Test
	public void testAddParameterName() {
		p.addParameterName(PARAM2);
		assertEquals("Put parameters not adding correctly", 2, p.getParameterNames().size());
	}

	@Test
	public void testRemoveParemeterName() {
		p.removeParemeterName(PARAM1);
		assertTrue("Put remove parameter not working correctly", p.getParameterNames().isEmpty());
	}

	@Test
	public void testGetParameterNames() {
		assertEquals("Get parameter working properly", PARAM1, p.getParameterNames().get(0));
	}

}

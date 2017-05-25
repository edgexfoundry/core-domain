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

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ValueDescriptorTest {

	static final String TEST_NAME1 = "Temperature";
	static final String TEST_NAME2 = "Temp";
	static final int TEST_MIN = -70;
	static final int TEST_MAX = 140;
	static final IoTType TEST_TYPE = IoTType.I;
	static final String TEST_UOMLABEL = "C";
	static final int TEST_DEF_VALUE = 32;
	static final String TEST_FORMATTING = "%d";
	static final String[] TEST_LABELS = { "temp", "room temp" };
	static final String TEST_DESCRIPTION = "test description";

	private List<ValueDescriptor> descriptors;

	@Before
	public void setup() {
		descriptors = new ArrayList<ValueDescriptor>();
		ValueDescriptor valDesc1 = new ValueDescriptor(TEST_NAME1, TEST_MIN, TEST_MAX, TEST_TYPE, TEST_UOMLABEL,
				TEST_DEF_VALUE, TEST_FORMATTING, TEST_LABELS, TEST_DESCRIPTION);
		descriptors.add(valDesc1);
		ValueDescriptor valDesc2 = new ValueDescriptor(TEST_NAME2, TEST_MIN, TEST_MAX, TEST_TYPE, TEST_UOMLABEL,
				TEST_DEF_VALUE, TEST_FORMATTING, TEST_LABELS, TEST_DESCRIPTION);
		descriptors.add(valDesc2);
	}

	@Test
	public void testGetNames() {
		String[] names = { TEST_NAME1, TEST_NAME2 };
		assertArrayEquals("ValueDescriptor names list does not match expected list", names,
				ValueDescriptor.getNames(descriptors).toArray(new String[0]));
	}

}

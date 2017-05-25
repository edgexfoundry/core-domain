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
package org.edgexfoundry.domain.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test methods that go beyond simple getter/setter in Event
 */
public class EventTest {

	private Reading r1;
	private Reading r2;
	private Event e;

	@Before
	public void setup() {
		r1 = new Reading("10");
		r2 = new Reading("20");
		e = new Event("test device");
	}

	@Test
	public void testMarkPushed() {
		long mockTS = 33333;
		e.addReading(r1);
		e.addReading(r2);
		e.markPushed(mockTS);
		assertEquals("Event pushed does not equal set timestamp", mockTS, e.getPushed());
		assertEquals("Reading pushed does not equal set timestamp", mockTS, r1.getPushed());
		assertEquals("Reading pushed does not equal set timestamp", mockTS, r2.getPushed());
	}

	@Test
	public void testAddReading() {
		// check adding to null list initializes list and adds
		e.addReading(r1);
		assertTrue("Add reading to event with no readings failed", e.getReadings().contains(r1));
		// check adding to a non-null list adds the new element
		e.addReading(r2);
		assertTrue(e.getReadings().contains(r2));
		assertEquals("Add reading failed to preserve collection", 2, e.getReadings().size());
		// check that device name is in reading
		assertTrue("Event device not associated to reading", r1.getDevice().equals(e.getDevice()));
		assertTrue("Event device not associated to reading", r2.getDevice().equals(e.getDevice()));

	}

	@Test
	public void testRemoveReading() {
		e.addReading(r1);
		assertTrue(e.getReadings().contains(r1));
		e.removeReading(r1);
		assertTrue("Delete reading from event failed", e.getReadings().isEmpty());
		assertFalse(e.getReadings().remove(r2));
	}

	@Test
	public void testAddReadingsArray() {
		Reading[] readings = { r1, r2 };
		e.addReadings(readings);
		List<Reading> rs = e.getReadings();
		assertEquals(2, rs.size());
		assertTrue(rs.contains(r1));
		assertTrue(rs.contains(r2));
		// check that device name is in reading
		assertTrue("Event device not associated to reading", r1.getDevice().equals(e.getDevice()));
		assertTrue("Event device not associated to reading", r2.getDevice().equals(e.getDevice()));

	}

	@Test
	public void testAddReadings() {
		List<Reading> readings = new ArrayList<Reading>();
		readings.add(r1);
		readings.add(r2);
		e.addReadings(readings);
		List<Reading> rs = e.getReadings();
		assertEquals(2, rs.size());
		assertTrue(rs.contains(r1));
		assertTrue(rs.contains(r2));
		// check that device name is in reading
		assertTrue("Event device not associated to reading", r1.getDevice().equals(e.getDevice()));
		assertTrue("Event device not associated to reading", r2.getDevice().equals(e.getDevice()));
	}

	@Test
	public void testSetReadingsNull() {
		List<Reading> readings = null;
		e.setReadings(readings);
		assertNull("Attempting to add null readings resulted in change to property", e.getReadings());
	}

}

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

  private final static String DEVICE_NAME = "test device";

  private Reading r1;
  private Reading r2;
  private Event e, e2;

  @Before
  public void setup() {
    r1 = new Reading("10");
    r2 = new Reading("20");
    e = new Event(DEVICE_NAME);
    e2 = new Event(DEVICE_NAME);
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
  public void testRemoveReadingsWithNoReadings() {
    e.removeReading(r1);
    assertTrue("Delete reading from event failed", e.getReadings().isEmpty());
  }

  @Test
  public void testAddReadingsArray() {
    Reading[] readings = {r1, r2};
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
  public void testAddReadingsWithExistingReadings() {
    List<Reading> readings = new ArrayList<Reading>();
    readings.add(r1);
    Event e = new Event(DEVICE_NAME, readings);
    List<Reading> moreReadings = new ArrayList<Reading>();
    moreReadings.add(r2);
    e.addReadings(moreReadings);

    List<Reading> rs = e.getReadings();
    assertEquals(2, rs.size());
    assertTrue(rs.contains(r1));
    assertTrue(rs.contains(r2));
    // check that device name is in reading
    assertTrue("Event device not associated to reading", r1.getDevice().equals(e.getDevice()));
    assertTrue("Event device not associated to reading", r2.getDevice().equals(e.getDevice()));
  }

  @Test
  public void testSetReadingsNotNull() {
    List<Reading> readings = new ArrayList<Reading>();
    readings.add(r1);
    readings.add(r2);
    e.setReadings(readings);
    assertEquals("readings not set on the event", readings, e.getReadings());
  }

  @Test
  public void testSetReadingsNull() {
    List<Reading> readings = null;
    e.setReadings(readings);
    assertNull("Attempting to add null readings resulted in change to property", e.getReadings());
  }

  @Test
  public void testSetDeviceWithReadings() {
    List<Reading> readings = new ArrayList<Reading>();
    readings.add(r1);
    readings.add(r2);
    e.setReadings(readings);
    e.setDevice(DEVICE_NAME);

    List<Reading> rs = e.getReadings();
    assertEquals(2, rs.size());
    assertTrue(rs.contains(r1));
    assertTrue(rs.contains(r2));
    // check that device name is in reading
    assertTrue("Event device not associated to reading", r1.getDevice().equals(DEVICE_NAME));
    assertTrue("Event device not associated to reading", r2.getDevice().equals(DEVICE_NAME));
  }

  @Test
  public void testSetDeviceWithNullReadings() {
    e.setDevice(DEVICE_NAME);
    assertEquals("Device not changed", e.getDevice(), DEVICE_NAME);
  }

  @Test
  public void testSetDeviceWithEmptyReadings() {
    e.setReadings(new ArrayList<Reading>());
    e.setDevice(DEVICE_NAME);
    assertEquals("Device not changed", e.getDevice(), DEVICE_NAME);
  }

  @Test
  public void testEquals() {
    assertTrue("Different events with same values not equal", e.equals(e2));
  }

  @Test
  public void testEqualsWithSame() {
    assertTrue("Same event is not equal", e.equals(e));
  }

  @Test
  public void testNotEquals() {
    e2.setCreated(1234L);
    assertFalse("Events with different base values are equal", e.equals(e2));
  }

  @Test
  public void testEqualWithDifferentPushed() {
    e2.setPushed(1234L);
    assertFalse("Events with different pushed values are equal", e.equals(e2));
  }

  @Test
  public void testEqualWithDifferentDevice() {
    e2.setDevice("differentName");
    assertFalse("Events with different device name values are equal", e.equals(e2));
  }

  @Test
  public void testEqualWithDifferentReadings() {
    e.addReading(r1);
    e2.addReading(r2);
    assertFalse("Events with different readings are equal", e.equals(e2));
  }

  @Test
  public void testEqualWithReadingsNull() {
    e.setReadings(null);
    e2.addReading(r2);
    assertFalse("Event with null readings is equal to event with readings", e.equals(e2));
  }

  @Test
  public void testEqualBothReadingsNull() {
    e.setReadings(null);
    e2.setReadings(null);
    assertTrue("Events with null readings are not equal", e.equals(e2));
  }

  @Test
  public void testHashCode() {
    assertTrue("hashcode not hashing properly", e.hashCode() != 0);
  }

  @Test
  public void testToString() {
    e.toString();
  }

}

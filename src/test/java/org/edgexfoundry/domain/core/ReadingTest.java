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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.edgexfoundry.domain.core.Reading;

/**
 * Unit test methods that go beyond simple getter/setter in Reading
 */
public class ReadingTest {

  private static final String TEST_VALUE = "10";
  private static final String TEST_NAME = "Temperature";
  private static final Long TEST_PUSHED = 1234L;

  private Reading r, r2;

  @Before
  public void setUp() {
    r = new Reading(TEST_NAME, TEST_VALUE);
    r2 = new Reading(TEST_NAME, TEST_VALUE);
    r.setPushed(TEST_PUSHED);
    r2.setPushed(TEST_PUSHED);
  }

  @Test
  public void testChangeValueType() {
    r.setValue("Foo");
    assertEquals("Foo", r.getValue());
  }

  @Test
  public void testEquals() {
    assertTrue("Different readings with same values not equal", r.equals(r2));
  }

  @Test
  public void testEqualsWithSame() {
    assertTrue("Same readings are not equal", r.equals(r));
  }

  @Test
  public void testNotEquals() {
    r2.setCreated(3456L);
    assertFalse("Readings with different base values are equal", r.equals(r2));
  }

  @Test
  public void testEqualWithDifferentPushed() {
    r2.setPushed(4567L);
    assertFalse("Readings with different pushed values are equal", r.equals(r2));
  }

  @Test
  public void testEqualWithDifferentNames() {
    r2.setName("foo");
    assertFalse("Readings with different name values are equal", r.equals(r2));
  }

  @Test
  public void testEqualWithDifferentValues() {
    r2.setValue("foo");
    assertFalse("Readings with different value values are equal", r.equals(r2));
  }

  @Test
  public void testHashCode() {
    assertTrue("hashcode not hashing properly", r.hashCode() != 0);
  }

  @Test
  public void testToString() {
    r.toString();
  }
}

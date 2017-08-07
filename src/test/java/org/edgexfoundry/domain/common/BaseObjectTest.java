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

package org.edgexfoundry.domain.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    bo1.setOrigin(System.currentTimeMillis());
    bo3.setOrigin(System.currentTimeMillis());
    bo2.setCreated(System.currentTimeMillis() + 1000);
  }

  @Test
  public void testEqualsSameObject() {
    assertTrue("Same object not equals", bo1.equals(bo1));
  }

  @Test
  public void testEqualsDifferentObject() {
    assertTrue("Same object not equals", bo1.equals(bo3));
  }

  @Test
  public void testNotEqualsNull() {
    assertFalse("base object was equal to null", bo1.equals(null));
  }

  @SuppressWarnings("unlikely-arg-type")
  @Test
  public void testNotEqualsWrongType() {
    assertFalse("base object was equal when compared to object of type string",
        bo1.equals("string"));
  }

  @Test
  public void testNotEqualsWrongCreated() {
    assertFalse("base objects with different created time are equal", bo1.equals(bo2));
  }

  @Test
  public void testNotEqualsWrongIds() {
    bo1.setId("234");
    bo3.setId("123");
    assertFalse("base ojbect with id was equal to base object with different id", bo1.equals(bo3));
  }
  
  @Test
  public void testEqualWithIds() {
    bo1.setId("234");
    bo3.setId("234");
    assertTrue("base ojbect with same ids were not equal", bo1.equals(bo3));
  }

  @Test
  public void testNotEqualsNoId() {
    bo1.setId(null);
    bo3.setId("123");
    assertFalse("base ojbect with no id was equal to base object with id", bo1.equals(bo3));
  }

  @Test
  public void testNotEqualsWrongOrigin() {
    bo3.setOrigin(1234L);
    assertFalse("Objects with different origin times are equal", bo1.equals(bo3));
  }
  
  @Test
  public void testHashCode() {
    assertTrue("hashcode not hashing properly",bo1.hashCode() != 0);
  }

  @Test
  public void testCompareTo() {
    assertEquals("Base object compare to method not working propery", 1, bo1.compareTo(bo2));
    assertEquals("Base object compare to method not working propery", -1, bo2.compareTo(bo1));
    assertEquals("Base object compare to method not working propery", -1, bo1.compareTo(bo3));
  }
  
  @Test
  public void testStringArrayPropertyMatchWithBothNulls() {
    assertTrue("string array comparison with nulls were not equal", bo1.stringArrayPropertyMatch(null, null));
  }

  @Test
  public void testStringArrayPropertyMatchWithNull() {
    String[] emptyArray = {};
    assertFalse("string array comparison with null and empty array were equal", bo1.stringArrayPropertyMatch(null, emptyArray));
  }

  @Test
  public void testStringArrayPropertyMatchWithDifferentSizes() {
    String[] one = {"one"};
    String[] two = {"one", "two"};
    assertFalse("string array comparison with different size arrays was equal", bo1.stringArrayPropertyMatch(one, two));
  }
  
  @Test
  public void testStringArrayPropertyMatchWithDifferentElements() {
    String[] one = {"one"};
    String[] two = {"three"};
    assertFalse("string array comparison with different arrays was equal", bo1.stringArrayPropertyMatch(one, two));
  }

  @Test
  public void testStringArrayPropertyMatch() {
    String[] one = {"one","two"};
    String[] two = {"one","two"};
    assertTrue("string array comparison with same arrays was not equal", bo1.stringArrayPropertyMatch(one, two));
  }
  
  @Test
  public void testObjectPropertyMatchWithNulls() {
    assertTrue("Object comparison with nulls fails", bo1.objectPropertyMatch(null, null));
  }

  @Test
  public void testObjectPropertyMatchWithOneNull() {
    assertFalse("Object comparison with one null passed", bo1.objectPropertyMatch(null, "test"));
  }  

  @Test
  public void testObjectPropertyMatchWithNoNulls() {
    assertTrue("Object comparison with no nulls fails", bo1.objectPropertyMatch("test", "test"));
  }  
  
  @Test
  public void testToString() {
    bo1.toString();
  }
  
}

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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DescribedObjectTest {

  private static final String TEST_DESC = "test description";

  private DescribedObject obj = new DescribedObject();

  @Before
  public void setup() {
    obj = new DescribedObject();
    obj.setDescription(TEST_DESC);
  }
  
  @Test
  public void testHashCode() {
    assertTrue("hashcode not hashing properly",obj.hashCode() != 0);
  }
  
  @Test
  public void testEqualsOnSameObject() {
    assertTrue("same object not equal to itself", obj.equals(obj));
  }

  @Test
  public void testEquals() {
    DescribedObject obj2 = new DescribedObject();
    obj2.setDescription(TEST_DESC);
    assertTrue("equal not working properly", obj.equals(obj2));
  }
  
  @Test
  public void testEqualsBasedOnDescriptionNull() {
    DescribedObject obj2 = new DescribedObject();
    obj.setDescription(null);
    assertTrue("equal not working when comparing different objects with null description",obj.equals(obj2));
  }
  
  @Test
  public void testNotEqualsBasedOnSuper() {
    DescribedObject obj2 = new DescribedObject();
    obj2.setDescription(TEST_DESC);
    obj2.setOrigin(1234L);
    assertFalse("equal not working at super level", obj.equals(obj2));
  }
  
  @Test
  public void testNotEqualsBasedOnDescription() {
    DescribedObject obj2 = new DescribedObject();
    obj2.setDescription("another description");
    assertFalse("equal not work when comparing different objects with different description",obj.equals(obj2));
  }
  
  @Test
  public void testEqualsBasedOnNullVsNotNullDescription() {
    DescribedObject obj2 = new DescribedObject();
    obj2.setDescription("some description");
    obj.setDescription(null);
    assertFalse("equal not working when comparing null to different description",obj.equals(obj2));
  }

}

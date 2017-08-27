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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ValueDescriptorTest {

  static final String TEST_NAME = "Temperature";
  static final int TEST_MIN = -70;
  static final int TEST_MAX = 140;
  static final IoTType TEST_TYPE = IoTType.I;
  static final String TEST_UOMLABEL = "C";
  static final int TEST_DEF_VALUE = 32;
  static final String TEST_FORMATTING = "%d";
  static final String[] TEST_LABELS = {"temp", "room temp"};
  static final String TEST_DESCRIPTION = "test description";

  private List<ValueDescriptor> descriptors;
  private ValueDescriptor valDesc1;
  private ValueDescriptor valDesc2;

  @Before
  public void setup() {
    descriptors = new ArrayList<ValueDescriptor>();
    valDesc1 = new ValueDescriptor(TEST_NAME, TEST_MIN, TEST_MAX, TEST_TYPE, TEST_UOMLABEL,
        TEST_DEF_VALUE, TEST_FORMATTING, TEST_LABELS, TEST_DESCRIPTION);
    descriptors.add(valDesc1);
    valDesc2 = new ValueDescriptor(TEST_NAME, TEST_MIN, TEST_MAX, TEST_TYPE, TEST_UOMLABEL,
        TEST_DEF_VALUE, TEST_FORMATTING, TEST_LABELS, TEST_DESCRIPTION);
    descriptors.add(valDesc2);
  }

  @Test
  public void testGetNames() {
    valDesc2.setName("Humidity");
    String[] names = {TEST_NAME, "Humidity"};
    assertArrayEquals("ValueDescriptor names list does not match expected list", names,
        ValueDescriptor.getNames(descriptors).toArray(new String[0]));
  }

  @Test
  public void testToString() {
    valDesc1.toString();
  }

  @Test
  public void testHashCode() {
    assertTrue("hashcode not hashing properly", valDesc1.hashCode() != 0);
  }

  @Test
  public void testEqualsWithSame() {
    assertTrue("same value descriptor not seen as equal", valDesc1.equals(valDesc1));
  }

  @Test
  public void testEqualsWithDifferntVDs() {
    assertTrue("value descriptors with same values seen as not equal", valDesc1.equals(valDesc2));
  }

  @Test
  public void testNotEquals() {
    valDesc1.setCreated(1234L);
    assertFalse("value descriptors with different base values seen as equal",
        valDesc1.equals(valDesc2));
  }

  @Test
  public void testEqualsWithDifferntName() {
    valDesc2.setName("differentName");
    assertFalse("value descriptors with different names seen as equal", valDesc1.equals(valDesc2));
  }

  @Test
  public void testEqualsWithDifferntMin() {
    valDesc2.setMin(TEST_MIN + 1);
    assertFalse("value descriptors with different mins seen as equal", valDesc1.equals(valDesc2));
  }

  @Test
  public void testEqualsWithDifferntMax() {
    valDesc2.setMax(TEST_MAX + 1);
    assertFalse("value descriptors with different max seen as equal", valDesc1.equals(valDesc2));
  }

  @Test
  public void testEqualsWithDifferntType() {
    valDesc2.setType(IoTType.S);
    assertFalse("value descriptors with different types seen as equal", valDesc1.equals(valDesc2));
  }

  @Test
  public void testEqualsWithDifferntLabels() {
    String[] newLabels = {"newlabel"};
    valDesc2.setLabels(newLabels);
    assertFalse("value descriptors with different labels seen as equal", valDesc1.equals(valDesc2));
  }

  @Test
  public void testEqualsWithDifferntDefaultValue() {
    valDesc2.setDefaultValue("default");
    assertFalse("value descriptors with different default values seen as equal",
        valDesc1.equals(valDesc2));
  }

  @Test
  public void testEqualsWithNullDefaultValue() {
    valDesc1.setDefaultValue(null);
    assertFalse("value descriptors with null default values seen as equal",
        valDesc1.equals(valDesc2));
  }

  @Test
  public void testEqualsWithNullDefaultValues() {
    valDesc1.setDefaultValue(null);
    valDesc2.setDefaultValue(null);
    assertTrue("value descriptors both with null default values seen as not equal",
        valDesc1.equals(valDesc2));
  }

  @Test
  public void testEqualsWithDifferntUomLabels() {
    valDesc2.setUomLabel("newUoM");
    assertFalse("value descriptors with different default values seen as equal",
        valDesc1.equals(valDesc2));
  }

  @Test
  public void testEqualsWithDifferntFormatting() {
    valDesc2.setFormatting("newformat");
    assertFalse("value descriptors with different formatting seen as equal",
        valDesc1.equals(valDesc2));
  }

}

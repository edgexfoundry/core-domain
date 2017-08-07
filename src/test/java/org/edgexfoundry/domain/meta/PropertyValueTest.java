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

package org.edgexfoundry.domain.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class PropertyValueTest {

  private PropertyValue pv;

  @Before
  public void setup() {
    pv = new PropertyValue();
  }

  @Test
  public void testToBigInteger() {
    pv.setMask("0x00");
    assertEquals("Propety Value convert to big int not working properly", pv.toBigInteger("0x01"),
        BigInteger.ONE);
  }

  @Test
  public void testToBigIntegerWithUnknownMask() {
    pv.setMask("foo");
    assertEquals("Propety Value convert to big int not working properly", pv.toBigInteger("10"),
        BigInteger.TEN);
  }

  @Test
  public void testShift() {
    pv.setShift("0x08");
    assertEquals("Propety Value shift not working properly", pv.shift(), new Integer(8));
  }

  @Test
  public void testShiftWithNoMask() {
    pv.setShift("10");
    assertEquals("Propety Value shift not working properly", pv.shift(), new Integer(10));
  }

  @Test
  public void testScale() {
    pv.setScale("0x08");
    assertEquals("Propety Value scale not working properly", pv.scale(), new Float(8));
  }

  @Test
  public void testScaletWithNoMask() {
    pv.setScale("10");
    assertEquals("Propety Value scale not working properly", pv.scale(), new Float(10));
  }

  @Test
  public void testOffset() {
    pv.setOffset("0x08");
    assertEquals("Propety Value offset not working properly", pv.offset(), new Float(8));
  }

  @Test
  public void testOffsetWithNoMask() {
    pv.setOffset("10");
    assertEquals("Propety Value offset not working properly", pv.offset(), new Float(10));
  }

  @Test
  public void testAssertion() {
    pv.setAssertion("0x01");
    pv.setMask("foo");
    assertEquals("Propety Value assertion not working properly", pv.assertion(), BigInteger.ONE);
  }

  @Test
  public void testAssertionWithNull() {
    pv.setAssertion(null);
    assertNull("Propety Value assertion not working properly", pv.assertion());
  }

  @Test
  public void testAssertionWithNoMask() {
    pv.setAssertion("10");
    pv.setMask("foo");
    assertEquals("Propety Value assertion not working properly", pv.assertion(), BigInteger.TEN);
  }

  @Test
  public void testToString() {
    pv.toString();
  }
}

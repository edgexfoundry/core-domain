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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class CommandTest {

  private static final String TEST_NAME = "test_command_name";
  private static final String TEST_PATH = "/api/v1/device/{deviceId}/temp";
  static final String TEST_CODE = "200";
  static final String TEST_DESCRIPTION = "ok";
  static final String TEST_EXPECTED_VALUE1 = "temperature";
  static final String TEST_EXPECTED_VALUE2 = "humidity";
  static final String TEST_PARAM1 = "Temperature";
  static final String TEST_PARAM2 = "Humidity";

  private Command c;
  private Command c2;

  @Before
  public void setup() {
    List<String> expected = new ArrayList<>();
    expected.add(TEST_EXPECTED_VALUE1);
    expected.add(TEST_EXPECTED_VALUE2);
    Response r = new Response(TEST_CODE, TEST_DESCRIPTION, expected);
    c = new Command();
    c.setName(TEST_NAME);
    Get g = new Get();
    g.setPath(TEST_PATH);
    g.addResponse(r);
    c.setGet(g);
    Put p = new Put();
    p.setPath(TEST_PATH);
    List<String> params = new ArrayList<>();
    params.add(TEST_PARAM1);
    params.add(TEST_PARAM2);
    p.setParameterNames(params);
    c.setPut(p);
    c2 = new Command();
    c2.setName(TEST_NAME);
    c2.setGet(g);
    c2.setPut(p);
  }

  @Test
  public void testAssociatedValueDescriptorsWithGetAndPut() {
    Set<String> vds = c.associatedValueDescriptors();
    assertTrue("Command does not have put param value descriptors", vds.contains(TEST_PARAM1));
    assertTrue("Command does not have put param value descriptors", vds.contains(TEST_PARAM2));
    assertTrue("Command does not have expected get value descriptors",
        vds.contains(TEST_EXPECTED_VALUE1));
    assertTrue("Command does not have expected get value descriptors",
        vds.contains(TEST_EXPECTED_VALUE2));
  }

  @Test
  public void testAssociatedValueDescriptorsWithNoGetNoPut() {
    c.setPut(null);
    c.setGet(null);
    Set<String> vds = new HashSet<String>();
    assertEquals("Command should not have value descriptors with no gets or puts",
        c.associatedValueDescriptors(), vds);
  }

  @Test
  public void testAssociatedValueDescriptorsWithNoGet() {
    c.setGet(null);
    Set<String> vds = c.associatedValueDescriptors();
    assertTrue("Command does not have put param value descriptors", vds.contains(TEST_PARAM1));
    assertTrue("Command does not have put param value descriptors", vds.contains(TEST_PARAM2));
  }

  @Test
  public void testAssociatedValueDescriptorsWithNoPut() {
    c.setPut(null);
    Set<String> vds = c.associatedValueDescriptors();
    assertTrue("Command does not have expected get value descriptors",
        vds.contains(TEST_EXPECTED_VALUE1));
    assertTrue("Command does not have expected get value descriptors",
        vds.contains(TEST_EXPECTED_VALUE2));
  }

  @Test
  public void testEquals() {
    assertTrue("Different command with same values not equal", c.equals(c2));
  }

  @Test
  public void testEqualsWithSame() {
    assertTrue("Same commands are not equal", c.equals(c));
  }

  @Test
  public void testNotEquals() {
    c.setCreated(3456L);
    assertFalse("Commands with different base values are equal", c.equals(c2));
  }

  @Test
  public void testEqualWithDifferentName() {
    c2.setName("foo");
    assertFalse("Commands with different names values are equal", c.equals(c2));
  }

  @Test
  public void testEqualWithDifferentGets() {
    c2.setGet(null);
    assertFalse("Commands with different get values are equal", c.equals(c2));
  }

  @Test
  public void testEqualWithDifferentPuts() {
    c2.setPut(null);
    assertFalse("Commands with different put values are equal", c.equals(c2));
  }

  @Test
  public void testHashCode() {
    assertTrue("hashcode not hashing properly", c.hashCode() != 0);
  }

  @Test
  public void testToString() {
    c.toString();
  }

}

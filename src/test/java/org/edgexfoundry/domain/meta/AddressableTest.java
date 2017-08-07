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

import org.edgexfoundry.domain.common.HTTPMethod;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class AddressableTest {

  private static final String TEST_NAME = "test_addressable";
  private static final Protocol TEST_HTTP_PROTOCOL = Protocol.HTTP;
  private static final Protocol TEST_MQTT_PROTOCOL = Protocol.TCP;
  private static final String TEST_ADDR = "localhost";
  private static final String TEST_PATH = "/pathtosomething";
  private static final int TEST_PORT = 49888;
  private static final String TEST_PUBLISHER = "test_publisher";
  private static final String TEST_TOPIC = "test_topic";
  private static final String TEST_USER = "fred";
  private static final String TEST_PASS = "password";
  private static final HTTPMethod TEST_METHOD = HTTPMethod.POST;

  private Addressable a, a2;
  private Addressable aForMQTT;

  @Before
  public void setup() {
    a = new Addressable(TEST_NAME, TEST_HTTP_PROTOCOL, TEST_ADDR, TEST_PATH, TEST_PORT);
    a.setMethod(TEST_METHOD);
    a2 = new Addressable(TEST_NAME, TEST_HTTP_PROTOCOL, TEST_ADDR, TEST_PATH, TEST_PORT);
    a2.setMethod(TEST_METHOD);
    aForMQTT = new Addressable(TEST_NAME, TEST_MQTT_PROTOCOL, TEST_ADDR, TEST_PORT, TEST_PUBLISHER,
        TEST_USER, TEST_PASS, TEST_TOPIC);
    aForMQTT.setPath(TEST_PATH);
  }

  @Test
  public void testGetBaseURL() {
    assertEquals("base url for addressable not correct",
        TEST_HTTP_PROTOCOL + "://" + TEST_ADDR + ":" + TEST_PORT, a.getBaseURL());
  }

  @Test
  public void testGetURL() {
    assertEquals("url for addressable not correct",
        TEST_HTTP_PROTOCOL + "://" + TEST_ADDR + ":" + TEST_PORT + TEST_PATH, a.getURL());
  }

  @Test
  public void testGetURLWithTopicAndPublisher() {
    assertEquals("url for addressable not correct",
        TEST_MQTT_PROTOCOL + "://" + TEST_ADDR + ":" + TEST_PORT + TEST_PATH, aForMQTT.getURL());
  }

  @Test
  public void testGetURLWithTopicAndNoPublisher() {
    aForMQTT.setPublisher(null);
    assertEquals("url for addressable not correct",
        TEST_MQTT_PROTOCOL + "://" + TEST_ADDR + ":" + TEST_PORT + TEST_TOPIC + "/" + TEST_PATH,
        aForMQTT.getURL());
  }

  @Test
  public void testGetURLWithNoTopicAndNoPublisher() {
    aForMQTT.setPublisher(null);
    aForMQTT.setTopic(null);
    assertEquals("url for addressable not correct",
        TEST_MQTT_PROTOCOL + "://" + TEST_ADDR + ":" + TEST_PORT + TEST_PATH, aForMQTT.getURL());
  }

  @Test
  public void testEquals() {
    assertTrue("Different addressable with same values not equal", a.equals(a2));
  }

  @Test
  public void testEqualsWithSame() {
    assertTrue("Same addressable are not equal", a.equals(a));
  }

  @Test
  public void testNotEquals() {
    a.setCreated(3456L);
    assertFalse("Addressable with different base values are equal", a.equals(a2));
  }

  @Test
  public void testEqualWithDifferentName() {
    a2.setName("foo");
    assertFalse("Addressable with different names values are equal", a.equals(a2));
  }

  @Test
  public void testEqualWithDifferentProtocol() {
    a2.setProtocol(Protocol.MAC);
    assertFalse("Addressable with different protocol values are equal", a.equals(a2));
  }

  @Test
  public void testEqualWithDifferentAddress() {
    a2.setAddress("differentAddress");
    assertFalse("Addressable with different address values are equal", a.equals(a2));
  }

  @Test
  public void testEqualWithDifferentPort() {
    a2.setPort(99);
    assertFalse("Addressable with different port values are equal", a.equals(a2));
  }

  @Test
  public void testEqualWithDifferentPublisher() {
    a2.setPublisher("newPub");
    assertFalse("Addressable with different publisher values are equal", a.equals(a2));
  }

  @Test
  public void testEqualWithDifferentUser() {
    a2.setUser("newuser");
    assertFalse("Addressable with different user values are equal", a.equals(a2));
  }

  @Test
  public void testEqualWithDifferentPassword() {
    a2.setPassword("newPass");
    assertFalse("Addressable with different password values are equal", a.equals(a2));
  }

  @Test
  public void testEqualWithDifferentTopic() {
    a2.setTopic("newTopic");
    assertFalse("Addressable with different topic values are equal", a.equals(a2));
  }

  @Test
  public void testHashCode() {
    assertTrue("hashcode not hashing properly", a.hashCode() != 0);
  }

  @Test
  public void testToString() {
    a.toString();
  }

}

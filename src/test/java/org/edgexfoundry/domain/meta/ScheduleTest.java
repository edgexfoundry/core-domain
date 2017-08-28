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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ScheduleTest {

  private static final String TEST_SCHEDULE_NAME = "test schedule";
  private static final String TEST_START = ""; // defaults to now
  private static final String TEST_END = ""; // defaults to ZDT MAX
  private static final String TEST_FREQUENCY = "PT2S";
  private static final String TEST_CRON = "0 0 12 * * ?";
  private static final boolean TEST_RUN_ONCE = false;

  private Schedule s;
  private Schedule s2;

  @Before
  public void setup() {
    s = new Schedule(TEST_SCHEDULE_NAME, TEST_START, TEST_END, TEST_FREQUENCY, TEST_CRON,
        TEST_RUN_ONCE);
    s2 = new Schedule(TEST_SCHEDULE_NAME, TEST_START, TEST_END, TEST_FREQUENCY, TEST_CRON,
        TEST_RUN_ONCE);
  }

  @Test
  public void testEquals() {
    assertTrue("Different schedules with same values not equal", s.equals(s2));
  }

  @Test
  public void testEqualsWithSame() {
    assertTrue("Same schedules are not equal", s.equals(s));
  }

  @Test
  public void testNotEquals() {
    s.setCreated(3456L);
    assertFalse("Schedules with different base values are equal", s.equals(s2));
  }

  @Test
  public void testEqualWithDifferentName() {
    s2.setName("foo");
    assertFalse("Schedules with different names values are equal", s.equals(s2));
  }

  @Test
  public void testEqualWithDifferentStart() {
    s2.setStart("start");
    assertFalse("Schedules with different start values are equal", s.equals(s2));
  }

  @Test
  public void testEqualWithDifferentEnd() {
    s2.setEnd("end");
    assertFalse("Schedules with different ends values are equal", s.equals(s2));
  }

  @Test
  public void testEqualWithDifferentFreq() {
    s2.setFrequency("frequency");
    assertFalse("Schedules with different frequency values are equal", s.equals(s2));
  }

  @Test
  public void testEqualWithDifferentCron() {
    s2.setCron("cron");
    assertFalse("Schedules with different cron values are equal", s.equals(s2));
  }

  @Test
  public void testEqualWithDifferentRunOnce() {
    s2.setRunOnce(true);
    assertFalse("Schedules with different run once values are equal", s.equals(s2));
  }

  @Test
  public void testHashCode() {
    assertTrue("hashcode not hashing properly", s.hashCode() != 0);
  }

  @Test
  public void testToString() {
    s.toString();
  }

}

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

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.edgexfoundry.domain.common.BaseObject;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@SuppressWarnings("serial")
public class Schedule extends BaseObject {

  // TODO - make protected after changes to test package
  public static final String[] DATETIME_FORMATS = {"yyyyMMdd'T'HHmmss"};

  // non-database identifier for a schedule- must be unique
  @Indexed(unique = true)
  private String name;

  // Start time in ISO 8601 format YYYYMMDD'T'HHmmss
  // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyymmdd'T'HHmmss")
  private String start;

  // Start time in ISO 8601 format YYYYMMDD'T'HHmmss
  // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyymmdd'T'HHmmss")
  private String end;

  // how frequently should the event occur
  private String frequency;

  // cron styled regular expression indicating how often the action under schedule should occur. Use
  // either runOnce, frequency or cron and not all.
  private String cron;

  // boolean indicating that this schedules runs one time - at the time indicated by the start
  private boolean runOnce;

  public Schedule(String name, String start, String end, String frequency, String cron,
      boolean runOnce) {
    super();
    this.name = name;
    this.start = start;
    this.end = end;
    this.frequency = frequency;
    this.cron = cron;
    this.runOnce = runOnce;
  }

  @SuppressWarnings("unused")
  // used by spring container
  private Schedule() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public String getEnd() {
    return end;
  }

  public void setEnd(String end) {
    this.end = end;
  }

  public String getFrequency() {
    return frequency;
  }

  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }

  public String getCron() {
    return cron;
  }

  public void setCron(String cron) {
    this.cron = cron;
  }

  public boolean getRunOnce() {
    return runOnce;
  }

  public void setRunOnce(boolean runOnce) {
    this.runOnce = runOnce;
  }

  @Override
  public String toString() {
    return "Schedule [name=" + name + ", start=" + start + ", end=" + end + ", frequency="
        + frequency + ", cron=" + cron + ", runOnce=" + runOnce + ", toString()=" + super.toString()
        + "]";
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(name).append(start)
        .append(end).append(frequency).append(cron).append(runOnce).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj))
      return false;
    Schedule other = (Schedule) obj;
    return propertyMatch(other);
  }

  private boolean propertyMatch(Schedule other) {
    if (!stringPropertyMatch(name, other.name))
      return false;
    if (!stringPropertyMatch(start, other.start))
      return false;
    if (!stringPropertyMatch(end, other.end))
      return false;
    if (!stringPropertyMatch(frequency, other.frequency))
      return false;
    if (!stringPropertyMatch(cron, other.cron))
      return false;
    return (runOnce == other.runOnce);
  }

}

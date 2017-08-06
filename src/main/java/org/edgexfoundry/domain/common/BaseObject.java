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

import java.io.Serializable;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

@SuppressWarnings("serial")
public abstract class BaseObject implements Serializable, Comparable<BaseObject> {

  // TODO - someday, we might want the created, modified, and origin
  // timestamps be of ZonedDateTime (following ISO-8601). This allows the
  // devices to be in different time zones than the core.

  // the database generated identifier
  @Id
  private String id;

  // the timestamp of when the record was added to the database
  @CreatedDate
  private long created;

  // the timestamp of the last modification to the record
  @LastModifiedDate
  private long modified;

  // the timestamp of when the record was created
  private long origin;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public long getCreated() {
    return created;
  }

  public void setCreated(long created) {
    this.created = created;
  }

  public long getModified() {
    return modified;
  }

  public void setModified(long modified) {
    this.modified = modified;
  }

  public long getOrigin() {
    return origin;
  }

  public void setOrigin(long origin) {
    this.origin = origin;
  }

  @Override
  public String toString() {
    return "BaseObject [id=" + id + ", created=" + created + ", modified=" + modified + ", origin="
        + origin + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    final int primeMult = 53;

    return new HashCodeBuilder(prime, primeMult).append(created).append(id).append(origin)
        .toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BaseObject other = (BaseObject) obj;
    return propertyMatch(other);
  }

  private boolean propertyMatch(BaseObject other) {
    if (created != other.created)
      return false;
    if (!stringPropertyMatch(this.id, other.id))
      return false;
    return (origin == other.origin);
  }

  protected boolean stringPropertyMatch(String thisString, String otherString) {
    if (thisString == null) {
      if (otherString != null)
        return false;
    } else if (!thisString.equals(otherString))
      return false;
    return true;
  }

  protected boolean stringArrayPropertyMatch(String[] thisArray, String[] otherArray) {
    if (thisArray == null) {
      if (otherArray != null)
        return false;
    } else {
      if (thisArray.length != otherArray.length)
        return false;
      int i = 0;
      for (String x : thisArray) {
        if (!x.equals(otherArray[i]))
          return false;
        i++;
      }
    }
    return true;
  }

  protected boolean objectPropertyMatch(Object thisObj, Object otherObj) {
    if (thisObj == null) {
      if (otherObj != null)
        return false;
    } else
      return thisObj.equals(otherObj);
    return true;
  }

  @Override
  public int compareTo(BaseObject in) {
    if (in.getCreated() > this.getCreated())
      return 1;
    else
      return -1;
  }

}

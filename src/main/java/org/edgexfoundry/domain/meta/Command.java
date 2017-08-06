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

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.edgexfoundry.domain.common.BaseObject;

@Document
@SuppressWarnings("serial")
public class Command extends BaseObject {

  // command name which should be unique on a profile but not necessarily
  // unique for all -profiles
  private String name;

  // get command details
  private Get get;

  // put command details
  private Put put;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Get getGet() {
    return get;
  }

  public void setGet(Get get) {
    this.get = get;
  }

  public Put getPut() {
    return put;
  }

  public void setPut(Put put) {
    this.put = put;
  }

  @Override
  public String toString() {
    return "Command [name=" + name + ", get=" + get + ", put=" + put + ", " + super.toString()
        + "]";
  }

  public Set<String> associatedValueDescriptors() {
    Set<String> assocVD = new HashSet<>();
    if (this.getGet() != null)
      assocVD.addAll(this.getGet().allAssociatedValueDescriptors());
    if (this.getPut() != null)
      assocVD.addAll(this.getPut().allAssociatedValueDescriptors());
    return assocVD;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(name).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj))
      return false;
    Command other = (Command) obj;
    return propertyMatch(other);
  }

  private boolean propertyMatch(Command other) {
    if (!stringPropertyMatch(this.name, other.name))
      return false;
    if (!objectPropertyMatch(get, other.get))
      return false;
    if (!objectPropertyMatch(put, other.put))
      return false;
    return true;
  }

}

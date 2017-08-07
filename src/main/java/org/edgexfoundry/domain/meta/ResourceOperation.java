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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceOperation {

  private String index;
  private String operation;
  private String object;
  private String property;
  private String parameter;
  private String resource;
  private List<String> secondary = new ArrayList<>();
  private Map<String, String> mappings = new HashMap<>();

  public ResourceOperation() {}

  public ResourceOperation(String operation, String object) {
    this.operation = operation;
    this.object = object;
    this.property = "value";
    this.parameter = object;
    this.index = "1";
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public String getObject() {
    return object;
  }

  public void setObject(String object) {
    this.object = object;
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public String getParameter() {
    return parameter;
  }

  public void setParameter(String parameter) {
    this.parameter = parameter;
  }

  public String getIndex() {
    return index;
  }

  public void setIndex(String index) {
    this.index = index;
  }

  @Override
  public String toString() {
    return "ResourceOperation [operation=" + operation + ", object=" + object + ", property="
        + property + ", parameter=" + parameter + ", mappings=" + mappings + ", index=" + index
        + "]";
  }

  public List<String> getSecondary() {
    return secondary;
  }

  public void setSecondary(List<String> secondary) {
    this.secondary = secondary;
  }

  public Map<String, String> getMappings() {
    return mappings;
  }

  public void setMappings(Map<String, String> mappings) {
    this.mappings = mappings;
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

}

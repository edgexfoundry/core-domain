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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Response implements Serializable{

  // status code provided with the response (usually an HTTP status code for
  // REST calls)
  private String code;

  // information about response - error description or good response
  // information
  private String description;

  // value descriptors indicating the values returned as part of the response.
  private List<String> expectedValues;

  public Response(String code, String description) {
    super();
    this.code = code;
    this.description = description;
  }

  public Response(String code, String description, List<String> expectedValues) {
    super();
    this.code = code;
    this.description = description;
    this.expectedValues = expectedValues;
  }

  // used by spring container
  @SuppressWarnings("unused")
  private Response() {}

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getExpectedValues() {
    if (this.expectedValues == null)
      this.expectedValues = new ArrayList<>();
    return expectedValues;
  }

  public void setExpectedValues(List<String> expectedValues) {
    this.expectedValues = expectedValues;
  }

  public void addExpectedValue(String expectedValue) {
    if (this.expectedValues == null)
      this.expectedValues = new ArrayList<>();
    expectedValues.add(expectedValue);
  }

  public boolean removeExpectedValue(String expectedValue) {
    if (this.expectedValues == null)
      this.expectedValues = new ArrayList<>();
    return this.expectedValues.remove(expectedValue);
  }

  @Override
  public String toString() {
    return "Response [code=" + code + ", description=" + description + ", expectedValues="
        + expectedValues + "]";
  }

}

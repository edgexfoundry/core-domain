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
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public abstract class Action implements Serializable{

  // path used by service for action on a device or sensor
  private String path;

  // responses from get or put request to service
  private List<Response> responses;

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public List<Response> getResponses() {
    return responses;
  }

  public void setResponses(List<Response> responses) {
    this.responses = responses;
  }

  public void addResponse(Response response) {
    if (responses == null)
      responses = new ArrayList<>();
    responses.add(response);
  }

  public void removeResponse(Response response) {
    if (responses == null)
      responses = new ArrayList<>();
    responses.remove(response);
  }

  /**
   * Convenience method to return a list of all expected values from all the associated responses.
   * 
   * @return List of expected value string names
   */
  public List<String> allExpectedValues() {
    if (responses == null)
      return new ArrayList<>();
    return responses.stream().map((Response r) -> r.getExpectedValues()).flatMap(l -> l.stream())
        .collect(Collectors.toList());
  }

  /**
   * Convenience method to return the names of all associated value descriptor names. For Get this
   * is just expected value names. For Put, this is expected value names plus parameter names.
   * 
   * @return
   */
  public List<String> allAssociatedValueDescriptors() {
    return allExpectedValues();
  }

  @Override
  public String toString() {
    return "Action [path=" + path + ", responses=" + responses + "]";
  }

}

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

import java.util.List;

public class ProfileResource {

  private String name;
  private List<ResourceOperation> get;
  private List<ResourceOperation> set;

  public String getName() {
    return name;
  }

  public void setName(String key) {
    this.name = key;
  }

  public List<ResourceOperation> getGet() {
    return get;
  }

  public void setGet(List<ResourceOperation> get) {
    this.get = get;
  }

  public List<ResourceOperation> getSet() {
    return set;
  }

  public void setSet(List<ResourceOperation> set) {
    this.set = set;
  }

}

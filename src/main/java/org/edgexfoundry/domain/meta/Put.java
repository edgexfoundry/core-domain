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
import java.util.List;

@SuppressWarnings("serial")
public class Put extends Action {

  // ValueDescriptor names indicating the type and shape of the parameter
  // value
  private List<String> parameterNames;

  public void addParameterName(String param) {
    if (parameterNames == null)
      parameterNames = new ArrayList<>();
    parameterNames.add(param);
  }

  public void removeParemeterName(String param) {
    if (parameterNames != null)
      parameterNames.remove(param);
  }

  public List<String> getParameterNames() {
    if (parameterNames == null)
      return new ArrayList<>();
    return parameterNames;
  }

  public void setParameterNames(List<String> parameterNames) {
    this.parameterNames = parameterNames;
  }

  @Override
  public String toString() {
    return "Put [parameterNames=" + parameterNames + "]";
  }

  @Override
  public List<String> allAssociatedValueDescriptors() {
    List<String> assocValueDescriptors = super.allAssociatedValueDescriptors();
    assocValueDescriptors.addAll(getParameterNames());
    return assocValueDescriptors;
  }
}

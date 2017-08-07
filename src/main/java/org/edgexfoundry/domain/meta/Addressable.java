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
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import org.edgexfoundry.domain.common.BaseObject;
import org.edgexfoundry.domain.common.HTTPMethod;

@Document
@SuppressWarnings("serial")
public class Addressable extends BaseObject {

  // non-database identifier for a addressable - must be unique
  @Indexed(unique = true)

  // unique name and identifier of the addressable
  private String name;

  // HTTP method used with the addressable (default is POST)
  private HTTPMethod method;

  // protocol used using the addressable
  private Protocol protocol;

  // address (in either tcp or http format) of the addressable
  private String address;

  // port on the address for the addressable
  private int port;

  // for callbacks
  private String path;

  // for MQTT or other message bus addressables
  private String publisher;

  // user id for authentication to addressable
  private String user;

  // password for the user for authentication to addressable
  private String password;

  // topic for MQTT message bus addressables or routing token for REST calls
  private String topic;

  public Addressable(String name, Protocol protocol, String address, String path, int port) {
    super();
    this.name = name;
    this.protocol = protocol;
    this.address = address;
    this.path = path;
    this.port = port;
    this.method = HTTPMethod.POST;
  }

  public Addressable(String name, Protocol protocol, String address, int port, String publisher,
      String user, String password, String topic) {
    super();
    this.name = name;
    this.protocol = protocol;
    this.address = address;
    this.port = port;
    this.publisher = publisher;
    this.user = user;
    this.password = password;
    this.topic = topic;
    this.method = HTTPMethod.POST;
  }

  @SuppressWarnings("unused")
  // used by spring container
  private Addressable() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Protocol getProtocol() {
    return protocol;
  }

  public void setProtocol(Protocol protocol) {
    this.protocol = protocol;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public HTTPMethod getMethod() {
    return method;
  }

  public void setMethod(HTTPMethod method) {
    this.method = method;
  }

  @JsonIgnore
  public String getBaseURL() {
    StringBuilder builder = new StringBuilder(protocol.toString());
    builder.append("://");
    builder.append(address);
    builder.append(":");
    builder.append(port);
    return builder.toString();
  }

  // TODO - is this method used anywhere? If so, it does not appear to be setup correctly and makes
  // no sense. Remove if found to not be used
  /**
   * @deprecated removed if not used and if it is used, make sure it returns the right information
   */
  @JsonIgnore
  @Deprecated
  public String getURL() {
    StringBuilder builder = new StringBuilder(getBaseURL());
    if (publisher == null && topic != null) {
      builder.append(topic);
      builder.append("/");
    }
    builder.append(path);
    return builder.toString();
  }

  @Override
  public String toString() {
    return "Addressable [name=" + name + ", protocol=" + protocol + ", address=" + address
        + ", port=" + port + ", path=" + path + ", publisher=" + publisher + ", user=" + user
        + ", password=" + password + ", topic=" + topic + ", toString()=" + super.toString() + "]";
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().appendSuper(super.hashCode()).append(name).append(protocol)
        .append(address).append(port).append(path).append(publisher).append(user).append(password)
        .append(topic).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (!super.equals(obj))
      return false;
    Addressable other = (Addressable) obj;
    return propertyMatch(other);
  }

  private boolean propertyMatch(Addressable other) {
    if (!stringPropertyMatch(this.name, other.name))
      return false;
    if (protocol != other.protocol)
      return false;
    if (!stringPropertyMatch(this.address, other.address))
      return false;
    if (this.port != other.port)
      return false;
    if (!stringPropertyMatch(this.publisher, other.publisher))
      return false;
    if (!stringPropertyMatch(this.user, other.user))
      return false;
    if (!stringPropertyMatch(this.password, other.password))
      return false;
    return stringPropertyMatch(this.topic, other.topic);
  }

}

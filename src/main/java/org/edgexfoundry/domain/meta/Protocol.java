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

// TODO - someday, we may need to refactor addressable based on needs of protocol. Other is a
// placeholder today.
// HTTP - Can be used for REST communications and SNMP
// TCP - for MQTT and other general TCP based communications
// MAC - MAC address - low level (example serial) communications
// ZMQ - Zero MQ communications
// OTHER - Can be used for Modbus
// SSL - for TLS encrypted sockets
public enum Protocol {
  HTTP, TCP, MAC, ZMQ, OTHER, SSL
}

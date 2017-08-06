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

import java.math.BigInteger;

import org.codehaus.jackson.annotate.JsonIgnore;

public class PropertyValue {

  // ValueDescriptor Type of property after transformations
  private String type;

  // Read/Write Permissions set for this property
  private String readWrite;

  // Minimum value that can be get/set from this property
  private String minimum;

  // Maximum value that can be get/set from this property
  private String maximum;

  // Default value set to this property if no argument is passed
  private String defaultValue;

  // Size of this property in its type
  // (i.e. bytes for numeric types, characters for string types)
  private String size;

  // Required precision of the property
  private String precision;

  // Word size of property used for endianness
  private String word = "2";

  // Endianness setting for a property
  private String LSB;

  // Mask to be applied prior to get/set of property
  private String mask = "0x00";

  // Shift to be applied after masking, prior to get/set of property
  private String shift = "0";

  // Multiplicative factor to be applied after shifting, prior to get/set of property
  private String scale = "1.0";

  // Additive factor to be applied after multiplying, prior to get/set of property
  private String offset = "0.0";

  // Base for property to be applied to, leave 0 for no power operation
  // (i.e. base ^ property: 2 ^ 10)
  private String base = "0";

  // Required value of the property, set for checking error state.
  // Failing an assertion condition will mark the device with an error state
  private String assertion = null;

  // Treat the property as a signed or unsigned value
  private Boolean signed = true;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getReadWrite() {
    return readWrite;
  }

  public void setReadWrite(String readWrite) {
    this.readWrite = readWrite;
  }

  public String getMinimum() {
    return minimum;
  }

  public void setMinimum(String minimum) {
    this.minimum = minimum;
  }

  public String getMaximum() {
    return maximum;
  }

  public void setMaximum(String maximum) {
    this.maximum = maximum;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  public Integer size() {
    return new Integer(size);
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public BigInteger toBigInteger(String s) {
    BigInteger big;
    if (mask.contains("0x")) {
      big = new BigInteger(s.substring(2), 16);
    } else {
      big = new BigInteger(s);
    }
    return big;
  }

  public BigInteger mask() {
    return toBigInteger(mask);
  }

  public String getMask() {
    return mask;
  }

  public void setMask(String mask) {
    this.mask = mask;
  }

  public Integer shift() {
    if (shift.contains("0x")) {
      return Integer.parseUnsignedInt(shift.substring(2), 16);
    } else {
      return Integer.parseUnsignedInt(shift);
    }
  }

  public String getShift() {
    return shift;
  }

  public void setShift(String shift) {
    this.shift = shift;
  }

  public Float scale() {
    if (scale.contains("0x")) {
      return Float.valueOf(Integer.parseUnsignedInt(scale.substring(2), 16));
    } else {
      return Float.parseFloat(scale);
    }
  }

  public String getScale() {
    return scale;
  }

  public void setScale(String scale) {
    this.scale = scale;
  }

  public Float offset() {
    if (offset.contains("0x")) {
      return Float.valueOf(Integer.parseUnsignedInt(offset.substring(2), 16));
    } else {
      return Float.parseFloat(offset);
    }
  }

  public String getOffset() {
    return offset;
  }

  public void setOffset(String offset) {
    this.offset = offset;
  }

  @JsonIgnore
  public boolean LSB() {
    return new Boolean(LSB);
  }

  public String getLSB() {
    return LSB;
  }

  public void setLSB(String LSB) {
    this.LSB = LSB;
  }

  public Integer base() {
    return new Integer(base);
  }

  public String getBase() {
    return base;
  }

  public void setBase(String base) {
    this.base = base;
  }

  public BigInteger assertion() {
    if (assertion == null)
      return null;
    if (assertion.contains("0x")) {
      return toBigInteger(assertion.substring(2));
    } else {
      return toBigInteger(assertion);
    }
  }

  public String getAssertion() {
    return assertion;
  }

  public void setAssertion(String assertion) {
    this.assertion = assertion;
  }

  public Integer word() {
    return Integer.parseInt(word);
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public Boolean getSigned() {
    return signed;
  }

  public void setSigned(Boolean signed) {
    this.signed = signed;
  }

  public String getPrecision() {
    return precision;
  }

  public void setPrecision(String precision) {
    this.precision = precision;
  }

  public Float precision() {
    return Float.parseFloat(precision);
  }

  @Override
  public String toString() {
    return "PropertyValue{" + "readWrite:" + readWrite + ", minimum:" + minimum + ", maximum:"
        + maximum + ", defaultValue:" + defaultValue + ", size:" + size + ", precision:" + precision
        + ", word:" + word + ", LSB:" + LSB + ", mask:" + mask + ", shift:" + shift + ", scale:"
        + scale + ", offset:" + offset + ", base:" + base + ", assertion:" + assertion + ", signed:"
        + signed + '}';
  }

}

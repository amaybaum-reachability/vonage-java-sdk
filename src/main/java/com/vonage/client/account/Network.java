/*
 *   Copyright 2023 Vonage
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.vonage.client.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Network {
    private Type type;
    private BigDecimal price;
    private String currency, mcc, mnc, code, name;

    public Type getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getMcc() {
        return mcc;
    }

    public String getMnc() {
        return mnc;
    }

    @JsonProperty("networkCode")
    public String getCode() {
        return code;
    }

    @JsonProperty("networkName")
    public String getName() {
        return name;
    }

    enum Type {
        MOBILE, LANDLINE, PAGER, LANDLINE_TOLLFREE, UNKNOWN;

        @Override
        @JsonValue
        public String toString() {
            return name().toLowerCase();
        }

        @JsonCreator
        public static Type fromString(String type) {
            try {
                return Type.valueOf(type.toUpperCase());
            }
            catch (IllegalArgumentException ex) {
                return UNKNOWN;
            }
        }
    }
}

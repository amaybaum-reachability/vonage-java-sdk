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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vonage.client.VonageUnexpectedException;
import java.io.IOException;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PrefixPricingResponse {
    private int count;
    private List<PricingResponse> countries;

    public int getCount() {
        return count;
    }

    public List<PricingResponse> getCountries() {
        return countries;
    }

    public static PrefixPricingResponse fromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, PrefixPricingResponse.class);
        } catch (IOException jpe) {
            throw new VonageUnexpectedException("Failed to produce PrefixPricingResponse from json.", jpe);
        }
    }
}

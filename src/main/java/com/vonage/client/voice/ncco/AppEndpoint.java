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
package com.vonage.client.voice.ncco;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents an app endpoint used in a {@link ConnectAction}. See
 * <a href=https://developer.vonage.com/voice/voice-api/ncco-reference#app-endpoint>the documentation</a>
 * for an example.
 *
 * @since 5.4.0
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AppEndpoint implements Endpoint {
    private static final String TYPE = "app";

    private final String user;

    private AppEndpoint(Builder builder) {
        this.user = builder.user;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public String getUser() {
        return user;
    }

    public static Builder builder(String user) {
        return new Builder(user);
    }

    public static class Builder {
        private String user;

        Builder(String user) {
            this.user = user;
        }

        public Builder user(String user) {
            this.user = user;
            return this;
        }

        public AppEndpoint build() {
            return new AppEndpoint(this);
        }
    }
}
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
 * Represents a phone endpoint used in a {@link ConnectAction}. See
 * <a href=https://developer.vonage.com/voice/voice-api/ncco-reference#phone-endpoint>the documentation</a>
 * for an example.
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PhoneEndpoint implements Endpoint {
    private static final String TYPE = "phone";

    private final String number, dtmfAnswer;
    private final OnAnswer onAnswer;

    private PhoneEndpoint(Builder builder) {
        this.number = builder.number;
        this.dtmfAnswer = builder.dtmfAnswer;
        this.onAnswer = (builder.onAnswerUrl != null) ? new OnAnswer(builder.onAnswerUrl, builder.onAnswerRingback) : null;
    }

    public String getType() {
        return TYPE;
    }

    public String getNumber() {
        return number;
    }

    public String getDtmfAnswer() {
        return dtmfAnswer;
    }

    public OnAnswer getOnAnswer() {
        return onAnswer;
    }

    public static Builder builder(String number) {
        return new Builder(number);
    }

    public static class Builder {
        private String number, dtmfAnswer, onAnswerUrl, onAnswerRingback;

        Builder(String number) {
            this.number = number;
        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Builder dtmfAnswer(String dtmfAnswer) {
            this.dtmfAnswer = dtmfAnswer;
            return this;
        }

        public Builder onAnswer(String url) {
            this.onAnswerUrl = url;
            return this;
        }

        public Builder onAnswer(String url, String ringback) {
            this.onAnswerUrl = url;
            this.onAnswerRingback = ringback;
            return this;
        }

        public PhoneEndpoint build() {
            return new PhoneEndpoint(this);
        }
    }

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    public static class OnAnswer {
        private final String url, ringback;

        private OnAnswer(String url, String ringback) {
            this.url = url;
            this.ringback = ringback;
        }

        public String getUrl() {
            return url;
        }

        public String getRingback() {
            return ringback;
        }
    }
}

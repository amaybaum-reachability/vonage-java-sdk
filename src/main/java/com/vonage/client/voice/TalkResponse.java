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
package com.vonage.client.voice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vonage.client.VonageUnexpectedException;
import java.io.IOException;

/**
 * Response from successfully sending a synthesized speech message or stopping a message to an active {@link Call}.
 * <p>
 * This would be returned by {@link VoiceClient#startTalk(String, TalkPayload)} or {@link VoiceClient#stopTalk(String)}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TalkResponse {
    private String uuid, message;

    public String getUuid() {
        return uuid;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Creates an instance of this class from a JSON payload.
     *
     * @param json The JSON string to parse.
     *
     * @return An instance of this class with the fields populated, if present.
     */
    public static TalkResponse fromJson(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, TalkResponse.class);
        }
        catch (IOException jpe) {
            throw new VonageUnexpectedException("Failed to parse json for TalkResponse object.", jpe);
        }
    }
}

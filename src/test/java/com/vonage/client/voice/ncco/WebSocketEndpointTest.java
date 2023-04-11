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

import org.junit.Assert;
import org.junit.Test;
import java.util.Collections;

public class WebSocketEndpointTest {

    @Test
    public void testAllFields() {
        WebSocketEndpoint endpoint = WebSocketEndpoint.builder("wss://example.com", "some-content")
                .uri("wss://example.net")
                .contentType("some-content-type")
                .headers(Collections.singletonMap("keyOne", Collections.singletonList(1)))
                .build();
        ConnectAction connect = ConnectAction.builder(endpoint).build();

        String expectedJson = "[{\"endpoint\":[{\"uri\":\"wss://example.net\",\"headers\":{\"keyOne\":[1]},\"type\":\"websocket\",\"content-type\":\"some-content-type\"}],\"action\":\"connect\"}]";
        Assert.assertEquals(expectedJson, new Ncco(connect).toJson());
    }
}

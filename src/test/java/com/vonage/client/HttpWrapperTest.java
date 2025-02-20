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
package com.vonage.client;

import com.vonage.client.auth.AuthCollection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HttpWrapperTest {
    private static final String EXPECTED_DEFAULT_API_BASE_URI = "https://api.nexmo.com";
    private static final String EXPECTED_DEFAULT_REST_BASE_URI = "https://rest.nexmo.com";
    private static final String EXPECTED_DEFAULT_SNS_BASE_URI = "https://sns.nexmo.com";

    private HttpWrapper wrapper;

    @Before
    public void setUp() {
        wrapper = new HttpWrapper(new AuthCollection());
    }

    @Test
    public void basicTest() {
        assertNotNull(wrapper.getHttpClient());
    }

    @Test
    public void testAuthMethodAccessors() {
        AuthCollection auths = new AuthCollection();
        wrapper.setAuthCollection(auths);
        assertEquals(auths, wrapper.getAuthCollection());
    }

    @Test
    public void testHttpConfigAccessor() {
        assertNotNull(wrapper.getHttpConfig());
    }

    @Test
    public void testDefaultConstructorSetsDefaultConfigValues() {
        HttpConfig config = wrapper.getHttpConfig();
        assertEquals(EXPECTED_DEFAULT_API_BASE_URI, config.getApiBaseUri());
        assertEquals(EXPECTED_DEFAULT_REST_BASE_URI, config.getRestBaseUri());
        assertEquals(EXPECTED_DEFAULT_SNS_BASE_URI, config.getSnsBaseUri());
    }
}

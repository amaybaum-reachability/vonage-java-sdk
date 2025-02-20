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

import com.vonage.client.AbstractMethod;
import com.vonage.client.HttpWrapper;
import com.vonage.client.auth.JWTAuthMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

class CreateCallEndpoint extends AbstractMethod<Call, CallEvent> {
    private static final String PATH = "/calls";
    private static final Class<?>[] ALLOWED_AUTH_METHODS = {JWTAuthMethod.class};

    CreateCallEndpoint(HttpWrapper httpWrapper) {
        super(httpWrapper);
    }

    @Override
    public RequestBuilder makeRequest(Call request) throws UnsupportedEncodingException {
        String uri = httpWrapper.getHttpConfig().getVersionedApiBaseUri("v1") + PATH;
        return RequestBuilder.post(uri)
                .setHeader("Content-Type", "application/json")
                .setHeader("Accept", "application/json")
                .setEntity(new StringEntity(request.toJson(), ContentType.APPLICATION_JSON));
    }

    @Override
    protected Class<?>[] getAcceptableAuthMethods() {
        return ALLOWED_AUTH_METHODS;
    }

    @Override
    public CallEvent parseResponse(HttpResponse response) throws IOException {
        return CallEvent.fromJson(basicResponseHandler.handleResponse(response));
    }
}

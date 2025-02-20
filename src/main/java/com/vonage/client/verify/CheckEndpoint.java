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
package com.vonage.client.verify;

import com.vonage.client.AbstractMethod;
import com.vonage.client.HttpWrapper;
import com.vonage.client.auth.TokenAuthMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

class CheckEndpoint extends AbstractMethod<CheckRequest, CheckResponse> {
    private static final Class<?>[] ALLOWED_AUTH_METHODS = {TokenAuthMethod.class};
    private static final String PATH = "/verify/check/json";

    CheckEndpoint(HttpWrapper httpWrapper) {
        super(httpWrapper);
    }

    @Override
    protected Class<?>[] getAcceptableAuthMethods() {
        return ALLOWED_AUTH_METHODS;
    }

    @Override
    public RequestBuilder makeRequest(CheckRequest request) throws UnsupportedEncodingException {
        String uri = httpWrapper.getHttpConfig().getApiBaseUri() + PATH;
        RequestBuilder result = RequestBuilder.post(uri)
                .setHeader("Content-Type", "application/x-www-form-urlencoded")
                .setHeader("Accept", "application/json")
                .addParameter("request_id", request.getRequestId())
                .addParameter("code", request.getCode());

        if (request.getIpAddress() != null) {
            result.addParameter("ip_address", request.getIpAddress());
        }

        return result;
    }

    @Override
    public CheckResponse parseResponse(HttpResponse response) throws IOException {
        return CheckResponse.fromJson(basicResponseHandler.handleResponse(response));
    }
}

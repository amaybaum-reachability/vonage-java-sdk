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
import com.vonage.client.VonageUnexpectedException;
import com.vonage.client.auth.JWTAuthMethod;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

class ListCallsEndpoint extends AbstractMethod<CallsFilter, CallInfoPage> {
    private static final String PATH = "/calls";
    private static final Class<?>[] ALLOWED_AUTH_METHODS = {JWTAuthMethod.class};

    ListCallsEndpoint(HttpWrapper httpWrapper) {
        super(httpWrapper);
    }

    @Override
    protected Class<?>[] getAcceptableAuthMethods() {
        return ALLOWED_AUTH_METHODS;
    }

    @Override
    public RequestBuilder makeRequest(CallsFilter filter) throws UnsupportedEncodingException {
        URIBuilder uriBuilder;
        String uri = httpWrapper.getHttpConfig().getVersionedApiBaseUri("v1") + PATH;

        try {
            uriBuilder = new URIBuilder(uri);
        } catch (URISyntaxException e) {
            throw new VonageUnexpectedException("Could not parse URI: " + uri);
        }
        if (filter != null) {
            List<NameValuePair> params = filter.toUrlParams();
            for (NameValuePair param : params) {
                uriBuilder.setParameter(param.getName(), param.getValue());
            }
        }
        return RequestBuilder.get(uriBuilder.toString())
                .setHeader("Accept", "application/json");
    }

    @Override
    public CallInfoPage parseResponse(HttpResponse response) throws IOException {
        return CallInfoPage.fromJson(basicResponseHandler.handleResponse(response));
    }
}

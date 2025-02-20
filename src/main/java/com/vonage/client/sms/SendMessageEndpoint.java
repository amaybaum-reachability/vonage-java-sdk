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
package com.vonage.client.sms;

import com.vonage.client.AbstractMethod;
import com.vonage.client.HttpWrapper;
import com.vonage.client.auth.SignatureAuthMethod;
import com.vonage.client.auth.TokenAuthMethod;
import com.vonage.client.sms.messages.Message;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

class SendMessageEndpoint extends AbstractMethod<Message, SmsSubmissionResponse> {
    private static final Class<?>[] ALLOWED_AUTH_METHODS = {SignatureAuthMethod.class, TokenAuthMethod.class};
    private static final String PATH = "/sms/json";

    SendMessageEndpoint(HttpWrapper httpWrapper) {
        super(httpWrapper);
    }

    @Override
    protected Class<?>[] getAcceptableAuthMethods() {
        return ALLOWED_AUTH_METHODS;
    }

    @Override
    public RequestBuilder makeRequest(Message message) throws UnsupportedEncodingException {
        RequestBuilder request = RequestBuilder
            .post(httpWrapper.getHttpConfig().getRestBaseUri() + PATH)
            .setHeader("Content-Type", "application/x-www-form-urlencoded")
            .setHeader("Accept", "application/json");
        message.addParams(request);
        return request;
    }

    @Override
    public SmsSubmissionResponse parseResponse(HttpResponse response) throws IOException {
        return SmsSubmissionResponse.fromJson(basicResponseHandler.handleResponse(response));
    }
}

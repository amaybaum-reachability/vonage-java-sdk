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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vonage.client.voice.ncco.Ncco;

/**
 * Extension of ModifyCallPayload which adds an NCCO destination to the serialized form.
 *
 * @deprecated Will be made package-private in next major release.
 */
@Deprecated
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class TransferCallPayload extends ModifyCallPayload {
    private final TransferDestination destination;

    public TransferCallPayload(String nccoUrl) {
        super(ModifyCallAction.TRANSFER);
        destination = new TransferDestination(nccoUrl);
    }

    public TransferCallPayload(Ncco ncco) {
        super(ModifyCallAction.TRANSFER);
        destination = new TransferDestination(ncco);
    }

    @JsonProperty("destination")
    public TransferDestination getDestination() {
        return destination;
    }
}

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
package com.vonage.client.messages.whatsapp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vonage.client.messages.internal.MessagePayload;
import com.vonage.client.messages.MessageType;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public final class WhatsappAudioRequest extends WhatsappRequest {
	final MessagePayload audio;

	WhatsappAudioRequest(Builder builder) {
		super(builder, MessageType.AUDIO);
		audio = new MessagePayload(builder.url);
		audio.validateUrlExtension("aac", "m4a", "amr", "mp3", "opus");
		audio.validateUrlLength(10, 2000);
	}

	@JsonProperty("audio")
	public MessagePayload getAudio() {
		return audio;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder extends WhatsappRequest.Builder<WhatsappAudioRequest, Builder> {
		String url;

		Builder() {}

		/**
		 * (REQUIRED)
		 * Sets the URL of the audio attachment. Supports only {@code .aac}, {@code .m4a}
		 * {@code .amr}, {@code .mp3} and {@code .opus} file extensions.
		 *
		 * @param url The URL as a string.
		 * @return This builder.
		 */
		public Builder url(String url) {
			this.url = url;
			return this;
		}

		@Override
		public WhatsappAudioRequest build() {
			return new WhatsappAudioRequest(this);
		}
	}

}

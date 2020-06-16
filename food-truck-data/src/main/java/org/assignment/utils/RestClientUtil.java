package org.assignment.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.assignment.entities.RequestType;
import org.assignment.exceptions.APIRequestException;
import org.assignment.exceptions.InvalidDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Client util to make Rest API calls.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RestClientUtil {
	private static final Logger log = LoggerFactory.getLogger(RestClientUtil.class);

	private String url;
	private RequestType requestType;

	/**
	 * Makes request to the given URL with the given type.
	 *
	 * @return An <class>InputStream</class> of the response if successful.
	 * @throws InvalidDataException If the URL/request type is invalid.
	 * @throws APIRequestException  If there is a failure in getting a response from the remote service.
	 */
	public InputStream makeRequest() throws InvalidDataException, APIRequestException {
		validate();
		try {
			URL url = new URL(this.url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(this.requestType.toString());
			return connection.getInputStream();
		} catch (IOException e) {
			log.error("Could not connect to the given URL[{}]", this.url, e);
			throw new APIRequestException("Communication failure with URL: " + this.url);
		}
	}

	private void validate() throws InvalidDataException {
		if (StringUtils.isBlank(this.url) || requestType == null) {
			log.error("Invalid URL[{}] or RequestType[{}].", this.url, this.requestType);
			throw new InvalidDataException("Invalid URL or RequestType.");
		}
	}

}

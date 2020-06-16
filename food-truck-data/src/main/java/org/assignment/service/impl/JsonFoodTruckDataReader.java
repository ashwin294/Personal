package org.assignment.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.assignment.entities.APIResponse;
import org.assignment.service.FoodTruckDataReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class JsonFoodTruckDataReader implements FoodTruckDataReader {
	private static final Logger log = LoggerFactory.getLogger(JsonFoodTruckDataReader.class);

	@Override
	public List<APIResponse> readData(InputStream inputStream) {
		try {
			return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(inputStream, new TypeReference<List<APIResponse>>() {
			});
		} catch (IOException e) {
			log.error("Could not read data from the InputStream.", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Convenience method to use String object.
	 *
	 * @param inputString String containing the JSON data.
	 * @return List of APIResponse objects.
	 */
	public List<APIResponse> readData(String inputString) {
		return readData(new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8)));
	}

	/**
	 * Convenience method to use File object.
	 *
	 * @param inputFile File containing the JSON data.
	 * @return List of APIResponse objects.
	 * @throws FileNotFoundException
	 */
	public List<APIResponse> readData(File inputFile) throws FileNotFoundException {
		return readData(new FileInputStream(inputFile));
	}
}

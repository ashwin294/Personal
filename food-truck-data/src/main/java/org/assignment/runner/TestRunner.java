package org.assignment.runner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assignment.entities.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TestRunner {
	private static final Logger log = LoggerFactory.getLogger(TestRunner.class);

	public TestRunner() {
	}

	public static void main(String[] args) {
		try {
			StringBuilder result = new StringBuilder();
//			URL url = new URL("http://data.sfgov.org/resource/bbb8-hzi6.xml?starttime=10AM&dayofweekstr=Monday");
			URL url = new URL("https://data.sfgov.org/resource/jjew-r69b.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			List<APIResponse> apiResponse = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(conn.getInputStream(), new TypeReference<List<APIResponse>>() {
			});

//			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			String line;
//			while ((line = rd.readLine()) != null) {
//				result.append(line);
//			}
//			rd.close();

			log.info("Response: {}", apiResponse);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}

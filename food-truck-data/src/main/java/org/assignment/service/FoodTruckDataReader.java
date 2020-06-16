package org.assignment.service;

import org.assignment.entities.APIResponse;

import java.io.InputStream;
import java.util.List;

/**
 * Interface to parse Food Truck Data.
 */
public interface FoodTruckDataReader {

	/**
	 * Read from source and generate a list of APIResponse objects.
	 *
	 * @param inputStream InputStream to the source data.
	 * @return A list of APIResponse objects read from the InputStream, empty list in case of failures or if no data present.
	 */
	List<APIResponse> readData(InputStream inputStream);

}

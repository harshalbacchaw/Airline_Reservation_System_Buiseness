package com.wipro.velocity.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.wipro.velocity.model.Flight;

public interface FlightRepository extends CrudRepository<Flight, Integer> {

	@Query(value = "SELECT * from Flight f where f.source =:source AND f.destination = :destination ", nativeQuery = true)
	   
	List<Flight> fetchFlight(@Param("source") String source, @Param("destination")String destination);

	//List<Flight> fetchFlight(String source, String destination);
}

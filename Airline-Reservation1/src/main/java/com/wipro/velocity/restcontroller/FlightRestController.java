package com.wipro.velocity.restcontroller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.velocity.exception.ResourceNotFoundException;
import com.wipro.velocity.model.Flight;
import com.wipro.velocity.repository.FlightRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class FlightRestController {

	@Autowired
	private FlightRepository frepo;

	@PostMapping("/flight")
	public Flight createFlight(@Validated @RequestBody Flight flight)
	{

		Flight f = new Flight();
		//f.setfNumber(flight.getfNumber());
		f.setSource(flight.getSource());
		f.setDestination(flight.getDestination());
		f.setDeparture(flight.getDeparture());
		f.setArrival(flight.getArrival());
		f.setDate(flight.getDate());
		f.setDuration(flight.getDuration());
		f.setPassengers(flight.getPassengers());
		f.setEconomy(flight.getEconomy());
		f.setBuiseness(flight.getBuiseness());

		frepo.save(f);
		return flight;
	}

	// Browse - http://localhost:8086/airline/api/flight/3
	@GetMapping("/flight/{fNumber}")
	public ResponseEntity<Flight> getFlightById(@PathVariable(value = "fNumber") Integer fNumber)
			throws ResourceNotFoundException {
		Flight flight = frepo.findById(fNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Flight not found for this id :: " + fNumber));

		return ResponseEntity.ok().body(flight);
	}

	@DeleteMapping("/flight/{fNumber}")
	public Map<String, Boolean> deleteProduct(@PathVariable(value = "fNumber") Integer fNumber) throws ResourceNotFoundException{

		Flight flight = frepo.findById(fNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + fNumber));
		frepo.delete(flight);

		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}

	// In Postman - PUT - http://localhost:8086/airline/api/flight/6
	//Body - raw - Json - Enter Json object - Send
	@PutMapping("/flight/{fNumber}")
	public ResponseEntity<Flight> updateProduct(@PathVariable(value = "fNumber") Integer fNumber,
			@Validated @RequestBody Flight f) throws ResourceNotFoundException {

		Flight flight = frepo.findById(fNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + fNumber));


		flight.setSource(f.getSource());
		flight.setDestination(f.getDestination());
		flight.setDeparture(f.getDeparture());
		flight.setArrival(f.getArrival());
		flight.setDate(f.getDate());
		flight.setDuration(f.getDuration());
		flight.setPassengers(f.getPassengers());
		flight.setEconomy(f.getEconomy());
		flight.setBuiseness(f.getBuiseness());

		final Flight updatedProduct = frepo.save(flight);
		return ResponseEntity.ok(updatedProduct);
	}

	// Browse - http://localhost:8086/airline/api/flight/source/destination
	@GetMapping("/flight/{source}/{destination}")
	public List<Flight> getAllProducts(@PathVariable(value = "source") String source, @PathVariable(value = "destination") String destination) {
		return frepo.fetchFlight(source,destination);
	}












	/*@PostMapping("/flight")
	public Boolean findFlight(@Validated @RequestBody Flight flight) {
		Boolean a = false;
		int fNumber = flight.getfNumber();
		String source = flight.getSource();
		String destination = flight.getDestination();
		Flight f = frepo.findById(Integer fNumber);


		if (source.equals() && password.equals(d.getPassword())) {
			a = true;
		}
		return a;
	}*/


	/*
	// Browse - http://localhost:8085/ims/api/products
		@GetMapping("/products")
		public List<Product> getAllProducts() {
			return prepo.findAll();   
		}

		/**
	 * ResponseEntity represents an HTTP response, including headers, 
	 * body, and status.
	 */

	/*@PathVariable annotation  indicates that a method parameter 
	 * should be bound to a URI template variable.*/

	//Browse - http://localhost:8085/ims/api/products/3
	/*
		@GetMapping("/products/{id}")
		public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long pId)
				throws ResourceNotFoundException {
			Product product = prepo.findById(pId)
					.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + pId));

			return ResponseEntity.ok().body(product);
		}

		// @RequestBody annotation automatically deserializes the JSON into a Java type
		// In Postman - POST - http://localhost:8085/ims/api/products
		//Body - raw - Json - Enter Json object - Send 
		@PostMapping("/products")
		public Product saveProduct(@Validated @RequestBody Product product) {
			return prepo.save(product)  ;

		}

		// In Postman - DELETE - http://localhost:8085/ims/api/products/7
		@DeleteMapping("/products/{id}")
		public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long pId)
				throws ResourceNotFoundException{
			Product product = prepo.findById(pId)
					.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + pId));
			prepo.delete(product);

			Map<String, Boolean> response = new HashMap<>();
			response.put("Deleted", Boolean.TRUE);
			return response;
		}

		// In Postman - PUT - http://localhost:8085/ims/api/products/6
		//Body - raw - Json - Enter Json object - Send
		@PutMapping("/products/{id}")
		public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long pId,
				@Validated @RequestBody Product p) throws ResourceNotFoundException {

			Product product = prepo.findById(pId)
					.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + pId));


			product.setBrand(p.getBrand());
			product.setMadein(p.getMadein());
			product.setName(p.getName());
			product.setPrice(p.getPrice());

			final Product updatedProduct = prepo.save(product);
			return ResponseEntity.ok(updatedProduct);
		}*/
}
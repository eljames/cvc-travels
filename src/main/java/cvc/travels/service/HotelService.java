package cvc.travels.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import cvc.travels.business.Hotel;
import reactor.core.publisher.Mono;

@Component
public class HotelService {
	
	@Autowired
	private WebClient client;
	
	
	/**
	 * Gets all hotels by a cityId
	 * @param cityId The id of a specific city
	 * @return
	 */
	public List<Hotel> hotels(int cityId) {
		Mono<List<Hotel>> hotels = client
			.get()
			.uri((urlBuilder) ->
				 urlBuilder.path("/hotels/avail/{id}")
				 .build(cityId)
			)
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<List<Hotel>>() {});
		return hotels.block();
	}
	
	/**
	 * Gets a hotel, specifying a hotelId.
	 * @param hotelId
	 * @return
	 */
	public List<Hotel> hotel(int hotelId) {
		Mono<List<Hotel>> hotels = client
			.get()
			.uri((urlBuilder) ->
				 urlBuilder.path("/hotels/{id}")
				 .build(hotelId)
			)
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<List<Hotel>>() {});
		return hotels.block();
	}
}

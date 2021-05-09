package cvc.travels.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import cvc.travels.business.HotelPartner;
import reactor.core.publisher.Mono;

@Component
public class HotelPartnerService {
	
	@Autowired
	private WebClient client;
	
	public List<HotelPartner> hotelsByCity(int cityId) {
		Mono<List<HotelPartner>> hotels = client
			.get()
			.uri((urlBuilder) ->
				 urlBuilder.path("/hotels/avail/{id}")
				 .build(cityId)
			)
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<List<HotelPartner>>() {});
		return hotels.block();
	}
	
	public List<HotelPartner> hotel(int hotelId) {
		Mono<List<HotelPartner>> hotels = client
			.get()
			.uri((urlBuilder) ->
				 urlBuilder.path("/hotels/{id}")
				 .build(hotelId)
			)
			.retrieve()
			.bodyToMono(new ParameterizedTypeReference<List<HotelPartner>>() {});
		return hotels.block();
	}
}

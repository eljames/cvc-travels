package cvc.travels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableCaching
public class TravelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelsApplication.class, args);
	}
	
	
	@Bean
	public WebClient getWebClientBuilder(){
	    return WebClient.builder().exchangeStrategies(ExchangeStrategies.builder()
        .codecs(configurer -> configurer
			.defaultCodecs()
          	.maxInMemorySize(16 * 1024 * 1024))
        	.build())
	    .baseUrl("https://cvcbackendhotel.herokuapp.com")
	    .build();
	}
	
	@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("hotels");
    }

}

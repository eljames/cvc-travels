package cvc.travels;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class TravelsApp {
	
	
	private static final Logger logger = LogManager.getLogger(TravelsApp.class);

	public static void main(String[] args) {
		logger.info("Started Travel Application");
		SpringApplication.run(TravelsApp.class, args);
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

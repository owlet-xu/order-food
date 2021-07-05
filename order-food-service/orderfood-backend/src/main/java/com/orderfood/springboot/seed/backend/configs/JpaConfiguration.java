package  com.orderfood.springboot.seed.backend.configs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Administrator on 2017/3/3.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.orderfood.springboot.seed")
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.orderfood.springboot.seed"})
public class JpaConfiguration {
}

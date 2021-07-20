package com.oocl.configuration;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RedisPropertiesConfiguration.class)
public class RedisAutoConfiguration {

    @Bean
    RedissonClient redissonClient(RedisPropertiesConfiguration redisPropertiesConfiguration) {
        Config config = new Config();
        String prefix = "redis://";
        config.useSingleServer()
                .setAddress(prefix + redisPropertiesConfiguration.getHost() + ":" + redisPropertiesConfiguration.getPort())
                .setTimeout(redisPropertiesConfiguration.getTimeout());
        return Redisson.create(config);
    }


}

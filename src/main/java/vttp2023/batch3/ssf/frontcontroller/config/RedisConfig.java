package vttp2023.batch3.ssf.frontcontroller.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

 @Value("${spring.redis.host}")
 private String redisHost; 

 @Value("${spring.redis.port}")
 private Optional<Integer> redisPort;

 @Value("${spring.redis.username}")
 private String redisUsername; 

 @Value("${spring.redis.password}")
 private String redisPassword; 


 @Bean("login")

 @Scope("singleton")
 public RedisTemplate<String, String> redisTemplate(){
     final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
     config.setHostName(redisHost);
     config.setPort(redisPort.get());

     if(!redisUsername.isEmpty() && !redisPassword.isEmpty()){
         config.setUsername(redisUsername);
         config.setPassword(redisPassword);
     }
     config.setDatabase(0);
     final JedisClientConfiguration jedisClient =  JedisClientConfiguration
                             .builder()
                             .build();

     final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, 
             jedisClient);
     jedisFac.afterPropertiesSet();
     
     RedisTemplate<String, String> r = new RedisTemplate<>();
     r.setConnectionFactory(jedisFac);
     r.setKeySerializer(new StringRedisSerializer());                
     r.setValueSerializer(new StringRedisSerializer());
     
     System.out.println("redisHost > " + redisHost);
     return r;
 }
}


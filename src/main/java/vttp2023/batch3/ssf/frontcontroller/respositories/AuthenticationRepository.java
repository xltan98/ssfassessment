package vttp2023.batch3.ssf.frontcontroller.respositories;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class AuthenticationRepository {

@Autowired
@Qualifier("login")
private RedisTemplate<String,String> template;

	public void addUser(String username){
		template.opsForValue()
		.set(username, username, 1800, TimeUnit.SECONDS);
	}

	public boolean findUser(String username){

		if(template.opsForValue().get(username) == null) {
			return false;
		}
		return true;

	}

	// TODO Task 5
	// Use this class to implement CRUD operations on Redis

}

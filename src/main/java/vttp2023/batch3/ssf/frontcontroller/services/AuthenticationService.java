package vttp2023.batch3.ssf.frontcontroller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.JsonObject;
import vttp2023.batch3.ssf.frontcontroller.model.Authenticate;

import vttp2023.batch3.ssf.frontcontroller.model.Login;
import vttp2023.batch3.ssf.frontcontroller.respositories.AuthenticationRepository;
@Service
public class AuthenticationService {

	@Autowired 
	AuthenticationRepository aRepo;


	@Value("${authenticate.url}")
	private String aUrl;//="https://authservice-production-e8b2.up.railway.app/";

	String fullAUrl=UriComponentsBuilder.fromUriString(aUrl)
										.path("/api/authenticate")
										.toUriString();

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public void authenticate(String username, String password) throws Exception {
		Login login = new Login();
		login.setUsername(username);
		login.setPassword(password);

		RestTemplate template = new RestTemplate();

		RequestEntity<String>request=RequestEntity.post(fullAUrl)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.body(login.toJson().toString());
	

		ResponseEntity<String> response = template.exchange(request,String.class);

		Authenticate auth= Authenticate.create(response.getBody());

		String message = auth.getMessage().toLowerCase();
		

		// if (message.contains("authenticate")){
		// 	return true;
			
		// }

		// return false;


	}


	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
		//aRepo.addUser(username);
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		//return aRepo.findUser(username);
		return false;
	}

	

	

	
}

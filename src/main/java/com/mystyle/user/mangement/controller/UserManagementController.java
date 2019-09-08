
package com.mystyle.user.mangement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mystyle.user.mangement.model.Role;
import com.mystyle.user.mangement.model.User;
import com.mystyle.user.mangement.service.UserService;

/**
 * @author chandan
 *
 */

@RestController
public class UserManagementController {

	   @Autowired
	    private UserService userService;

	    @Autowired
	    private DiscoveryClient discoveryClient;

	    @Autowired
	    private Environment env;

	    @Value("${spring.application.name}")
	    private String serviceId;

	    @GetMapping("/service/port")
	    public String getPort(){
	        return "Service port number : " + env.getProperty("local.server.port");
	    }

	    @GetMapping("/service/instances")
	    public ResponseEntity<?> getInstances(){
	        return new ResponseEntity<>(discoveryClient.getInstances(serviceId), HttpStatus.OK);
	    }

	    @GetMapping("/service/services")
	    public ResponseEntity<?> getServices(){
	        return new ResponseEntity<>(discoveryClient.getServices(), HttpStatus.OK);
	    }
	    
	    
	@PostMapping("/service/registration")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		if (userService.findByUsername(user.getUsername()) != null) {
			// Status code:409
			return new ResponseEntity<>(HttpStatus.CONFLICT);

		}
		// default role User
		user.setRole(Role.USER);

		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
	}

	@GetMapping("/service/login")
	public ResponseEntity<?> getUser(Principal principal) {
		// Principal principal=request.getUserPrincipal();

		if (principal == null || principal.getName() == null) {
			// this means; logout will be successful login?logout
			return new ResponseEntity<>(HttpStatus.OK);
		}
		// username=principal.getName()
		return ResponseEntity.ok(userService.findByUsername(principal.getName()));

	}

	@PostMapping("/service/user/names")
	public ResponseEntity<?> getNamesOfUsers(@RequestBody List<Long> idList) {
		return ResponseEntity.ok(userService.findUsers(idList));

	}

	@GetMapping("/service/test")
	public ResponseEntity<?> test() {
		return ResponseEntity.ok("userservice api working fine!!!");

	}

}

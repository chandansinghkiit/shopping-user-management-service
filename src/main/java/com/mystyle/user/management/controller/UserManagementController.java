
package com.mystyle.user.management.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mystyle.user.management.model.Role;
import com.mystyle.user.management.model.User;
import com.mystyle.user.management.service.UserService;

/**
 * @author chandan
 *
 */

@RestController
@RequestMapping("/service")
public class UserManagementController {

	@Autowired
	private UserService userService;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private Environment env;

	@Value("${spring.application.name}")
	private String serviceId;

	@GetMapping("/port")
	public String getPort() {
		return "Service port number : " + env.getProperty("local.server.port");
	}

	@GetMapping("/instances")
	public ResponseEntity<?> getInstances() {
		return new ResponseEntity<>(discoveryClient.getInstances(serviceId), HttpStatus.OK);
	}

	@GetMapping("/services")
	public ResponseEntity<?> getServices() {
		return new ResponseEntity<>(discoveryClient.getServices(), HttpStatus.OK);
	}

	@PostMapping("/registration")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		System.out.println("inside registration.........");
		if (userService.findByUsername(user.getUsername()) != null) {
			// Status code:409
			return new ResponseEntity<>(HttpStatus.CONFLICT);

		}
		// default role User
		user.setRole(Role.USER);

		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
	}

	@GetMapping("/login")
	public ResponseEntity<?> getUser(Principal principal) {
		  System.out.println("inside login .........");
		// Principal principal=request.getUserPrincipal();
		if (principal == null || principal.getName() == null) {
			// this means; logout will be successful login?logout
		   System.out.println("logout");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		// username=principal.getName()
		System.out.println("username"+principal.getName());
		return ResponseEntity.ok(userService.findByUsername(principal.getName()));

	}

	  @PostMapping("/names")
	    public ResponseEntity<?> getNamesOfUsers(@RequestBody List<Long> idList){
	        return ResponseEntity.ok(userService.findUsers(idList));
	    }

	@GetMapping("/test")
	public ResponseEntity<?> test() {
		return ResponseEntity.ok("userservice api working fine!!!");

	}

}

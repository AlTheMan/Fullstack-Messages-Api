package algot.emil.messagesapi.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("")
public class HealthController {


	@GetMapping
	public ResponseEntity<String> getBase() {
		return new ResponseEntity<>("It's working!", HttpStatus.OK);
	}


	@GetMapping("/healthz")
	public ResponseEntity<String> getHealth() {
		return new ResponseEntity<>(HttpStatus.OK);
	}


}

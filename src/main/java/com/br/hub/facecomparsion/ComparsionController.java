package com.br.hub.facecomparsion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/facecomparsion", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComparsionController {

	@Autowired
	private ComparsionService service;
	
	@GetMapping("/status")
	public Status getStatus() {
	   return new Status("Ok AND Running");	
	}
	
	@PostMapping(path="/compare", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Float> compare(@RequestBody Input input) throws Exception{
		try {
		  float result = service.compareTwoPhotos(input.photo1(), input.photo2());
		  return ResponseEntity.ok(result);
		} catch(Exception exc) {
		      System.out.println("Erro ao comparar faces: " + exc.getMessage());
	          exc.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	record Status(String status) {}
	record Input(String photo1, String photo2) {}
	record Output(float similarity) {}
}

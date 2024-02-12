package light.feather.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import io.micrometer.common.util.StringUtils;
import light.feather.model.Employee;
import light.feather.model.Supervisor;


@RestController
@RequestMapping("/api")
public class LightFeatherController {
	   Logger log = LoggerFactory.getLogger(LightFeatherController.class);

	   @Value("${url.for.supervisor}")
	   String urlForSupervisor;
	   
		WebClient webClient = WebClient.create();
	
	   @GetMapping ("/supervisors")
	   public ResponseEntity<List<String>> getSuppervisors() {
		   var supervisorList = webClient.get()
		   		                        .uri(urlForSupervisor)
		   		                        .accept(MediaType.APPLICATION_JSON)
		   		                        .retrieve()
		   		                        .bodyToMono(new ParameterizedTypeReference<List<Supervisor>>() {})
		   		                        .block();
		   
		   var strList = supervisorList.stream()
		   		                   .filter(supervisor->Character.isLetter(supervisor.getJurisdiction().charAt(0)))
		   		                   .sorted(Comparator.comparing(Supervisor::getJurisdiction).thenComparing(Supervisor::getLastName).thenComparing(Supervisor::getFirstName))
		   		                   .map(Supervisor::toString)
		   		                   .collect(Collectors.toList());
			   			   
	   	return ResponseEntity.status(HttpStatus.OK).body(strList);
	   }
	   
	  
	   @PostMapping ("/submit")
	   public ResponseEntity<String> postEmployee(@RequestBody Employee employee) {
	   	log.info(employee.toString());
	   	if (StringUtils.isBlank(employee.getFirstName()) || StringUtils.isBlank(employee.getLastName()) || StringUtils.isBlank(employee.getSupervisor())) {
	   		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Either firstName or lastName or supervisor is blank");
	   	} else {
	   	   return ResponseEntity.status(HttpStatus.OK).body("Submission is successful.");
	   	}
	   }
}

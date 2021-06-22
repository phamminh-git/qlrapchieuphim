package btlclient.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import btlclient.data.GheNgoiRepository;
import btlclient.model.GheNgoi;

@RestController
@RequestMapping(path="/ghengoi", produces = "application/json")
@CrossOrigin(origins = "*")
public class GheNgoiController {
	public GheNgoiRepository gheNgoiRepo;
	@Autowired
	EntityLinks entityLinks;
	public GheNgoiController(GheNgoiRepository gheNgoiRepo) {
		this.gheNgoiRepo=gheNgoiRepo;
	}
	@GetMapping("/getFree/{id}")
	public Iterable<GheNgoi> GetFree(@PathVariable("id")Long id){
		return gheNgoiRepo.GetGheTrong(id);
	}
	@GetMapping("/searchById/{id}")
	public Optional<GheNgoi> SearchById(@PathVariable("id")Long id) {
		return gheNgoiRepo.findById(id);
	}
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void SaveGheNgoi(@RequestBody GheNgoi ghengoi) {
		 gheNgoiRepo.save(ghengoi);
	}
}

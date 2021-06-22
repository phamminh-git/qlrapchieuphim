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

import btlclient.data.ViTriGheRepository;
import btlclient.model.ViTriGhe;

@RestController
@RequestMapping(path = "vitrighe", produces = "application/json")
@CrossOrigin(origins = "*")
public class ViTriGheController {
	public ViTriGheRepository viTriGheRepo;
	@Autowired
	EntityLinks entityLinks;
	public ViTriGheController(ViTriGheRepository viTriGheRepo) {
		this.viTriGheRepo=viTriGheRepo;
	}
	@GetMapping
	public Iterable<ViTriGhe> GetAll(){
		return viTriGheRepo.findAll();
	}
	@PostMapping(path = "/save", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void SaveViTriGhe(@RequestBody ViTriGhe vitrighe) {
		viTriGheRepo.save(vitrighe);
	}
	@GetMapping("/searchById/{id}")
	public Optional<ViTriGhe> SearchById(@PathVariable("id")Long id) {
		return viTriGheRepo.findById(id);
	}
}

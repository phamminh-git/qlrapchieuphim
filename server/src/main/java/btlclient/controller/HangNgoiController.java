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

import btlclient.data.HangNgoiRepository;
import btlclient.model.HangNgoi;

@RestController
@RequestMapping(path="/hangngoi", produces = "application/json")
@CrossOrigin(origins = "*")
public class HangNgoiController {
	public HangNgoiRepository hangNgoiRepo;
	@Autowired
	EntityLinks entityLinks;
	public HangNgoiController(HangNgoiRepository hangNgoiRepo) {
		this.hangNgoiRepo=hangNgoiRepo;
	}
	@GetMapping
	public Iterable<HangNgoi> GetAll(){
		return hangNgoiRepo.findAll();
	}
	@GetMapping("/search/{name}")
	public Iterable<HangNgoi> SearchByName(@PathVariable("name") String name){
		return hangNgoiRepo.searchByName(name);
	}
	@GetMapping("/searchById/{id}")
	public Optional<HangNgoi> SearchById(@PathVariable("id") Long id) {
		return hangNgoiRepo.findById(id);
	}
	@PostMapping(path="/save", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void SaveHangNgoi(@RequestBody HangNgoi hangngoi) {
		hangNgoiRepo.save(hangngoi);
	}
}

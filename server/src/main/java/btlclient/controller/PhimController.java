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

import btlclient.data.PhimRepository;
import btlclient.model.Phim;

@RequestMapping(path="/phim", produces = "application/json")
@RestController
@CrossOrigin(origins = "*")
public class PhimController {
	private PhimRepository phimRepo;
	@Autowired
	EntityLinks entityLinks;
	public PhimController(PhimRepository phimRepo) {
		this.phimRepo=phimRepo;
	}
	@GetMapping
	public Iterable<Phim> getAllMovie(){
		return phimRepo.findAll();
	}
	@GetMapping(path = "/search/{name}")
	public Iterable<Phim> HandleSearchMovie(@PathVariable("name") String name) {
		return phimRepo.searchMovieByName(name);
	}
	@GetMapping(path="/searchById/{id}")
	public Optional<Phim> HandleSearchById(@PathVariable("id") Long id) {
		return phimRepo.findById(id);
	}
	@PostMapping(path="/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void SaveMovie(@RequestBody Phim phim) {
		phimRepo.save(phim);
	}
}

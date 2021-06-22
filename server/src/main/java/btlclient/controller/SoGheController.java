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


import btlclient.data.SoGheRepository;
import btlclient.model.HangNgoi;
import btlclient.model.SoGhe;

@RestController
@RequestMapping(path="/soghe", produces = "application/json")
@CrossOrigin(origins = "*")
public class SoGheController {
	public SoGheRepository soGheRepo;
	@Autowired
	EntityLinks entityLinks;
	public SoGheController(SoGheRepository soGheRepo) {
		this.soGheRepo=soGheRepo;
	}
	@GetMapping
	public Iterable<SoGhe> GetAll(){
		return soGheRepo.findAll();
	}
	@GetMapping("/search/{name}")
	public Iterable<SoGhe> SearchByName(@PathVariable("name") String name){
		return soGheRepo.SearchByName(name);
	}
	@GetMapping("/searchById/{id}")
	public Optional<SoGhe> SearchById(@PathVariable("id")Long id){
		return soGheRepo.findById(id);
	}
	@PostMapping(path="/save", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void SaveSoGhe(@RequestBody SoGhe soghe) {
		soGheRepo.save(soghe);
	}
}

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

import btlclient.data.NhaCCRepository;
import btlclient.model.NhaCC;

@RestController
@RequestMapping(path="/nhacc", produces = "application/json")
@CrossOrigin(origins = "*")
public class NhaCCController {
	public NhaCCRepository nhaCCRepo;
	@Autowired
	EntityLinks entityLinks;
	public NhaCCController(NhaCCRepository nhaCCRepo) {
		this.nhaCCRepo=nhaCCRepo;
	}
	@GetMapping
	public Iterable<NhaCC> GetAll(){
		return nhaCCRepo.findAll();
	}
	@GetMapping("/searchByName/{name}")
	public Iterable<NhaCC> SearchByName(@PathVariable("name")String name){
		return nhaCCRepo.SearchByName(name);
	}
	@GetMapping("/searchById/{id}")
	public Optional<NhaCC> SearchById(@PathVariable("id")Long id) {
		return nhaCCRepo.findById(id);
	}
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void SavePhongChieu(@RequestBody NhaCC nhacc) {
		nhaCCRepo.save(nhacc);
	}
}

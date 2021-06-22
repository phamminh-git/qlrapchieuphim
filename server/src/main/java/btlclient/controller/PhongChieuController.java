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

import btlclient.data.PhongChieuRepository;
import btlclient.model.PhongChieu;

@RestController
@RequestMapping(path="/phongchieu", produces = "application/json")
@CrossOrigin(origins = "*")
public class PhongChieuController {
	private PhongChieuRepository phongChieuRepo;
	@Autowired
	EntityLinks entityLinks;
	public PhongChieuController(PhongChieuRepository phongChieuRepo) {
		this.phongChieuRepo=phongChieuRepo;
	}
	
	@GetMapping("/searchFreeRoom/{ngay}/{gio}")
	public Iterable<PhongChieu> SearchFreeRoom(@PathVariable("gio") String gio, @PathVariable("ngay") String ngay){
		return phongChieuRepo.searchFreeRoom(gio, ngay);
	}
	@GetMapping
	public Iterable<PhongChieu> GetAll(){
		return phongChieuRepo.findAll();
	}
	@GetMapping("/searchById/{id}")
	public Optional<PhongChieu> SearchById(@PathVariable("id")Long id) {
		return phongChieuRepo.findById(id);
	}
	@GetMapping("/searchByName/{name}")
	public Iterable<PhongChieu> SearchByName(@PathVariable("name")String name){
		return phongChieuRepo.searchByName(name);
	}
	@PostMapping(path="/save", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void SavePhongChieu(@RequestBody PhongChieu phongchieu) {
		phongChieuRepo.save(phongchieu);
	}
}

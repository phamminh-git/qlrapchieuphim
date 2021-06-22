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

import btlclient.data.PhieuNhapPhimRepository;
import btlclient.model.PhieuNhapPhim;

@RestController
@RequestMapping(path="/phieunhapphim", produces = "application/json")
@CrossOrigin(origins = "*")
public class PhieuNhapPhimController {
	public PhieuNhapPhimRepository phieuNhapPhimRepo;
	@Autowired
	EntityLinks entityLinks;
	public PhieuNhapPhimController(PhieuNhapPhimRepository phieuNhapPhimRepo) {
		this.phieuNhapPhimRepo=phieuNhapPhimRepo;
	}
	@GetMapping
	public Iterable<PhieuNhapPhim> GetAll(){
		return phieuNhapPhimRepo.findAll();
	}
	@GetMapping("/get/{id}")
	public Optional<PhieuNhapPhim> GetPhieuNhap(@PathVariable("id")Long id) {
		return phieuNhapPhimRepo.findById(id);
	}
	@PostMapping(path="/save", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void Save(@RequestBody PhieuNhapPhim phieunhapphim) {
		phieuNhapPhimRepo.save(phieunhapphim);
	}
}

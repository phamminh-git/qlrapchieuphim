package btlclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import btlclient.data.PhieuMuaVeRepository;
import btlclient.model.PhieuMuaVe;

@RestController
@RequestMapping(path="/phieumuave", produces = "application/json")
@CrossOrigin(origins="*")
public class PhieuMuaVeController {
	public PhieuMuaVeRepository phieuMuaVeRepo;
	@Autowired
	EntityLinks entityLinks;
	public PhieuMuaVeController(PhieuMuaVeRepository phieuMuaVeRepo) {
		this.phieuMuaVeRepo=phieuMuaVeRepo;
	}
	@PostMapping(path="/save", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void SavePhieuMua(@RequestBody PhieuMuaVe phieumua) {
		phieuMuaVeRepo.save(phieumua);
	}
}

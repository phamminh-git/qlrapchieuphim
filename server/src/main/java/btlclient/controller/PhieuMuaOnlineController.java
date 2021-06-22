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

import btlclient.data.PhieuMuaOnlineRepository;
import btlclient.model.PhieuMuaOnline;

@RestController
@RequestMapping(path="/phieumuaonline", produces = "application/json")
@CrossOrigin(origins = "*")
public class PhieuMuaOnlineController {
	public PhieuMuaOnlineRepository phieuMuaOnlineRepo;
	@Autowired
	EntityLinks entityLinks;
	public PhieuMuaOnlineController(PhieuMuaOnlineRepository phieuMuaOnlineRepo) {
		this.phieuMuaOnlineRepo=phieuMuaOnlineRepo;
	}
	@PostMapping(path="/save", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void SavePhieuMuaOnline(@RequestBody PhieuMuaOnline phieumuaonline) {
		phieuMuaOnlineRepo.save(phieumuaonline);
	}
}

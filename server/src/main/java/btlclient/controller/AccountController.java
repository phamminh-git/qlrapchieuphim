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

import btlclient.data.ThanhVienRepository;
import btlclient.model.ThanhVien;
@RequestMapping(path="/cinema", produces = "application/json")
@RestController
@CrossOrigin(origins = "*")
public class AccountController {
	private ThanhVienRepository thanhVienRepo;
	@Autowired
	EntityLinks entityLinks;
	public AccountController(ThanhVienRepository thanhVienRepo) {
		this.thanhVienRepo=thanhVienRepo;
	}
	@PostMapping(path="/login", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ThanhVien HandleLogin(@RequestBody ThanhVien thanhvien) {
		return thanhVienRepo.CheckLogin(thanhvien.getUsername(), thanhvien.getPassword());
	}
}

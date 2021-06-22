package btlclient.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import btlclient.data.NhanVienRepository;
import btlclient.model.NhanVien;

@RestController
@RequestMapping(path="/nhanvien", produces = "application/json")
@CrossOrigin(origins = "*")
public class NhanVienController {
	public NhanVienRepository nhanVienRepo;
	@Autowired
	EntityLinks entityLinks;
	public NhanVienController(NhanVienRepository nhanVienRepo) {
		this.nhanVienRepo=nhanVienRepo;
	}
	@GetMapping("/get/{id}")
	public Optional<NhanVien> GetNhanVien(@PathVariable("id") Long id) {
		return nhanVienRepo.findById(id);
	}
}

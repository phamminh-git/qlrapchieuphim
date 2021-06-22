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

import btlclient.data.KhachHangRepository;
import btlclient.model.KhachHang;

@RequestMapping(path="/khachhang", produces = "application/json")
@RestController
@CrossOrigin(origins = "*")
public class KhachHangController {
	private KhachHangRepository khachHangRepo;
	@Autowired
	EntityLinks entityLinks;
	public KhachHangController(KhachHangRepository khachHangRepo) {
		this.khachHangRepo=khachHangRepo;
	}
	@PostMapping(path="/register", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public KhachHang Handle(@RequestBody KhachHang khachhang) {
		try {
			khachHangRepo.save(khachhang);
			return khachhang;
		} catch (Exception e) {
			return null;
		}
	}
	@GetMapping("/searchById/{id}")
	public Optional<KhachHang> SearchById(@PathVariable("id") Long id) {
		return khachHangRepo.findById(id);
	}
}

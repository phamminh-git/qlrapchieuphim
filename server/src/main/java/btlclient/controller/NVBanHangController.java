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

import btlclient.data.NhanVienBanHangRepository;
import btlclient.model.NhanVienBanHang;
import btlclient.model.NhanVienQuanLy;

@RestController
@RequestMapping(path="/nhanvienbanhang", produces = "application/json")
@CrossOrigin(origins = "*")
public class NVBanHangController {
	private NhanVienBanHangRepository nvBHRepo;
	@Autowired
	EntityLinks entityLinks;
	public NVBanHangController(NhanVienBanHangRepository nvBanHangRepo ) {
		this.nvBHRepo=nvBanHangRepo;
	}
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public NhanVienBanHang HandleRegister(@RequestBody NhanVienBanHang nvbh) {
		try {
			nvBHRepo.save(nvbh);
			return nvbh;
		} catch (Exception e) {
			return null;
		}
	}
	@GetMapping("/searchById/{id}")
	public Optional<NhanVienBanHang> SearchById(@PathVariable("id")Long id) {
		return nvBHRepo.findById(id);
	}
}

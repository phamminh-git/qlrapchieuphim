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
import btlclient.data.NhanVienQuanLyRepository;
import btlclient.model.NhanVienQuanLy;

@RestController
@RequestMapping(path="/nhanvienquanly", produces = "application/json")
@CrossOrigin(origins = "*")
public class NVQuanLyController {
	private NhanVienQuanLyRepository nvQLRepo;
	@Autowired
	EntityLinks entityLinks;
	public NVQuanLyController(NhanVienQuanLyRepository nvQLRepo ) {
		this.nvQLRepo=nvQLRepo;
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public NhanVienQuanLy HandleRegister(@RequestBody NhanVienQuanLy nvql) {
		try {
			nvQLRepo.save(nvql);
			return nvql;
		} catch (Exception e) {
			return null;
		}	
	}
	@GetMapping("/searchById/{id}")
	public Optional<NhanVienQuanLy> SearchById(@PathVariable("id")Long id) {
		return nvQLRepo.findById(id);
	}
}

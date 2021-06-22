package btlclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import btlclient.data.NhanVienBanHangRepository;
import btlclient.data.NhanVienQuanLyRepository;

@RestController
@RequestMapping(path="/question", produces = "application/json")
@CrossOrigin(origins = "*")
public class AdminController {
	private NhanVienBanHangRepository nhanVienBanHangRepo;
	@Autowired
	EntityLinks entityLinks;
	public AdminController(NhanVienQuanLyRepository nvQLRepo, NhanVienBanHangRepository nvBanHangRepo ) {
		this.nhanVienBanHangRepo=nvBanHangRepo;
	}
}

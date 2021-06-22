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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import btlclient.data.LichChieuRepository;
import btlclient.model.LichChieu;

@RestController
@RequestMapping(path="/lichchieu", produces = "application/json")
@CrossOrigin(origins = "*")
public class LichChieuController {
	public LichChieuRepository lichChieuRepo;
	@Autowired
	EntityLinks entityLinks;
	public LichChieuController(LichChieuRepository lichChieuRepo) {
		this.lichChieuRepo=lichChieuRepo;
	}
	@PostMapping(path="/save", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void SaveLichChieu(@RequestBody LichChieu lichchieu) {
		lichChieuRepo.save(lichchieu);
	}
	@GetMapping("/phim/chuachieu/{id}")
	public Iterable<LichChieu> GetPhimChuaChieu(@PathVariable("id") Long id){
		return lichChieuRepo.GetMovie(id);
	}
	@GetMapping("/getlichchieu/{id}")
	public Optional<LichChieu> getLichChieu(@PathVariable("id") Long id) {
		return lichChieuRepo.findById(id);
	}
}

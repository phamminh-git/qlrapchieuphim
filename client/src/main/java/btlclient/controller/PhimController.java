package btlclient.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import btlclient.model.Phim;

@Controller
@RequestMapping("/phim")
public class PhimController {
	public RestTemplate rest=new RestTemplate();	
	@GetMapping
	public String showMainViewMovie(Model model) {
		List<Phim> phims=Arrays.asList(rest.getForObject("http://localhost:8080/phim", Phim[].class));
		model.addAttribute("phims", phims);
		return "movieManage";
	}
	@GetMapping("/search")
	public String searchPhim(@RequestParam("tenphim")String name, Model model) {
		List<Phim> phims=Arrays.asList(rest.getForObject("http://localhost:8080/phim/search/{name}",Phim[].class, name));
		model.addAttribute("phims", phims);
		return "movieManage";
	}
	@GetMapping("/edit/{id}")
	public String searchPhim(@PathVariable("id") Long id, Model model) {
		Phim phim=rest.getForObject("http://localhost:8080/phim/searchById/{id}", Phim.class, id);
		model.addAttribute("phim", phim);
		return "addMovie";
	}
	@GetMapping("/add")
	public String AddMovie(Model model) {
		model.addAttribute("phim", new Phim());
		return "addMovie";
	}
	@PostMapping("add")
	public String SaveMovie(Phim phim) {
		rest.postForObject("http://localhost:8080/phim/save", phim, Phim.class);
		return "redirect:/phim";
	}
}

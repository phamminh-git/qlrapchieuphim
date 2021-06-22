package btlclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import btlclient.model.SoGhe;

@Controller
@RequestMapping("soghe")
public class SoGheController {
	public RestTemplate rest=new RestTemplate();
	@GetMapping
	public String ShowViewMain(Model model) {
		model.addAttribute("soghes", rest.getForObject("http://localhost:8080/soghe", SoGhe[].class));
		return "QuanLySoGhe";
	}
	@GetMapping("/search")
	public String ShowViewSearch(@RequestParam("ten")String ten, Model model) {
		model.addAttribute("soghes", rest.getForObject("http://localhost:8080/soghe/search/{ten}", SoGhe[].class, ten));
		return "QuanLyHangGhe";
	}
	@GetMapping("/add")
	public String ShowViewAdd(Model model) {
		model.addAttribute("soghe", new SoGhe());
		return "themSoGhe";
	}
	@PostMapping("/add")
	public String HandleAdd(SoGhe soghe) {
		rest.postForObject("http://localhost:8080/soghe/save", soghe, SoGhe.class);
		return "redirect:/soghe";
	}
}

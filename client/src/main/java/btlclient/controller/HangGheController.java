package btlclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import btlclient.model.HangNgoi;

@Controller
@RequestMapping("/hangngoi")
public class HangGheController {
	public RestTemplate rest=new RestTemplate();
	@GetMapping
	public String ShowViewMain(Model model) {
		model.addAttribute("hangngois", rest.getForObject("http://localhost:8080/hangngoi", HangNgoi[].class));
		return "QuanLyHangGhe";
	}
	@GetMapping("/search")
	public String ShowViewSearch(@RequestParam("ten")String ten, Model model) {
		model.addAttribute("hangngois", rest.getForObject("http://localhost:8080/hangngoi/search/{ten}", HangNgoi[].class, ten));
		return "QuanLyHangGhe";
	}
	@GetMapping("/add")
	public String ShowViewAdd(Model model) {
		model.addAttribute("hangngoi", new HangNgoi());
		return "themHangNgoi";
	}
	@PostMapping("/add")
	public String HandleAdd(HangNgoi hangngoi) {
		rest.postForObject("http://localhost:8080/hangngoi/save", hangngoi, HangNgoi.class);
		return "redirect:/hangngoi";
	}
}

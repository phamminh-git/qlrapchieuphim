package btlclient.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import btlclient.model.GheNgoi;
import btlclient.model.HangNgoi;
import btlclient.model.SoGhe;
import btlclient.model.ViTriGhe;

@Controller
@RequestMapping("/vitrighe")
public class ViTriGheController {
	public RestTemplate rest=new RestTemplate();
	@GetMapping
	public String ShowView(Model model) {
		model.addAttribute("vitrighes", rest.getForObject("http://localhost:8080/vitrighe", ViTriGhe[].class));
		return "QuanLyViTriGhe";
	}
	@GetMapping("/add")
	public String ShowViewThem(Model model) {
		model.addAttribute("hangngois", rest.getForObject("http://localhost:8080/hangngoi", HangNgoi[].class));
		model.addAttribute("soghes", rest.getForObject("http://localhost:8080/soghe", SoGhe[].class));
		return "ThemViTriGhe";
	}
	@GetMapping("/save")
	public String HandleSave(@RequestParam("idhang")Long idhang, @RequestParam("idso")Long idso, Model model) {
		List<ViTriGhe> vitrighes=Arrays.asList(rest.getForObject("http://localhost:8080/vitrighe", ViTriGhe[].class));
		HangNgoi hangngoi=rest.getForObject("http://localhost:8080/hangngoi/searchById/{idhang}", HangNgoi.class, idhang);
		SoGhe soghe=rest.getForObject("http://localhost:8080/soghe/searchById/{idso}", SoGhe.class, idso);
		ViTriGhe vitrighe =new ViTriGhe();
		vitrighe.setHang(hangngoi);
		vitrighe.setSoGhe(soghe);
		for(ViTriGhe a: vitrighes) {
			if(a.equals(vitrighe)) {
				return "null";
			}
		}
		rest.postForObject("http://localhost:8080/vitrighe/save", vitrighe, ViTriGhe.class);
		return "redirect:/vitrighe";
	}
}

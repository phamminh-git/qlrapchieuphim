package btlclient.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import btlclient.model.GheNgoi;
import btlclient.model.PhongChieu;
import btlclient.model.ViTriGhe;

@Controller
@RequestMapping("/phongchieu")
public class PhongChieuController {
	public RestTemplate rest=new RestTemplate();
	@GetMapping
	public String ShowView(Model model) {
		model.addAttribute("phongchieus", rest.getForObject("http://localhost:8080/phongchieu", PhongChieu[].class));
		return "QuanLyPhongChieu";
	}
	@GetMapping("/search")
	public String ShowView(@RequestParam("name")String name, Model model) {
		model.addAttribute("phongchieus", rest.getForObject("http://localhost:8080/phongchieu/searchByName/{name}", PhongChieu[].class, name));
		return "redirect:/phongchieu";
	}
	@GetMapping("/manage/{id}")
	public String ShowViewManage(@PathVariable("id")Long id, Model model, HttpSession session) {
		PhongChieu phongchieu=rest.getForObject("http://localhost:8080/phongchieu/searchById/{id}", PhongChieu.class, id);
		session.setAttribute("phong chieu", phongchieu);
		model.addAttribute("ghengois", phongchieu.getListGhe());
		return "PhongChieu";
	}
	@GetMapping("/add")
	public String ShowViewAdd(Model model) {
		model.addAttribute("vitrighes", rest.getForObject("http://localhost:8080/vitrighe", ViTriGhe[].class));
		model.addAttribute("phongchieu", new PhongChieu());
		return "ThemPhongChieu";
	}
	@PostMapping("/add")
	public String HandleAdd(PhongChieu phongchieu,@RequestParam("themghe")ArrayList<Long> themghe,@RequestParam("isvip")ArrayList<Long> isvip) {
		List<GheNgoi> ghengois=new ArrayList<GheNgoi>();
		for(Long id: themghe) {
			GheNgoi ghengoi=new GheNgoi();
			ViTriGhe vitrighe=rest.getForObject("http://localhost:8080/vitrighe/searchById/{id}", ViTriGhe.class, id);
			ghengoi.setViTriGhe(vitrighe);
			if(isvip.contains(id)) {
				ghengoi.setVip(true);
			}
			ghengois.add(ghengoi);
		}
		phongchieu.setListGhe(ghengois);
		rest.postForObject("http://localhost:8080/phongchieu/save", phongchieu, PhongChieu.class);
		return "redirect:/phongchieu";
	}
}

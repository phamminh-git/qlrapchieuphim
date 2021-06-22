package btlclient.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import btlclient.model.GheNgoi;
import btlclient.model.PhongChieu;
import btlclient.model.ViTriGhe;

@Controller
@RequestMapping("/ghengoi")
public class GheNgoiController {
	public RestTemplate rest=new RestTemplate();
	@GetMapping("/add")
	public String showViewAdd(Model model, HttpSession session) {
		PhongChieu phongchieu=(PhongChieu) session.getAttribute("phong chieu");
		ViTriGhe[] vs=rest.getForObject("http://localhost:8080/vitrighe", ViTriGhe[].class);
		ArrayList<ViTriGhe> vitrighes=new ArrayList<ViTriGhe>();
		for(int i=0;i<vs.length;i++) {
			vitrighes.add(vs[i]);
		}
		for(GheNgoi ghe: phongchieu.getListGhe()) {
			for(int i=0;i<vs.length;i++) {
				if(vs[i].equals(ghe.getViTriGhe())) {
					vitrighes.remove(ghe.getViTriGhe());
					break;
				}
			}
		}
		model.addAttribute("vitrighes", vitrighes);
		
		return "ThemGheNgoi";
	}
	@PostMapping("/add")
	public String AddGheNgoi(@RequestParam("themghe")ArrayList<Long> themghe,@RequestParam("isvip")ArrayList<Long> isvip,HttpSession session) {
		PhongChieu phongchieu=(PhongChieu) session.getAttribute("phong chieu");
		List<GheNgoi> ghengois=phongchieu.getListGhe();
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
		return "redirect:/phongchieu/manage/"+ phongchieu.getId();
	}
}

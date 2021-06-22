package btlclient.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import btlclient.model.GheNgoi;
import btlclient.model.NhaCC;
import btlclient.model.Phim;
import btlclient.model.PhongChieu;
import btlclient.model.ViTriGhe;

@Controller
@RequestMapping("/nhacc")
public class NhaCCController {
	public RestTemplate rest=new RestTemplate();
	@GetMapping
	public String ShowView(Model model) {
		model.addAttribute("nhaccs", rest.getForObject("http://localhost:8080/nhacc", NhaCC[].class));
		return "QuanLyNhaCC";
	}
	@GetMapping("/search")
	public String ShowView(@RequestParam("name")String name, Model model) {
		model.addAttribute("nhaccs", rest.getForObject("http://localhost:8080/nhacc/searchByName/{name}", NhaCC[].class, name));
		return "redirect:/nhacc";
	}
	@GetMapping("/add")
	public String ShowViewAdd(Model model, HttpSession session) {
		model.addAttribute("phim", new Phim());
		model.addAttribute("nhacc", new NhaCC());
		return "ThemNhaCC";
	}
	@PostMapping("/add")
	public String HandleAdd(NhaCC nhacc, HttpSession session) {
		ArrayList<Phim> dsphim= (ArrayList<Phim>) session.getAttribute("danh sach phim");
		nhacc.setListPhim(dsphim);
		System.out.println(nhacc);
		rest.postForObject("http://localhost:8080/nhacc/save",nhacc, NhaCC.class);
		return "redirect:/nhacc";
	}
	@PostMapping("/addphim")
	public String HandleAdd(NhaCC nhacc, Model model,Phim phim, HttpSession session) {
		model.addAttribute("nhacc", nhacc);
		ArrayList<Phim> dsphim;
		if(session.getAttribute("danh sach phim")!=null) {
			dsphim=(ArrayList<Phim>) session.getAttribute("danh sach phim");		
			
		}
		else {
			dsphim=new ArrayList<Phim>();	
		}
		dsphim.add(phim);
		session.setAttribute("danh sach phim", dsphim);
		return "redirect:/nhacc/add";
	}
}

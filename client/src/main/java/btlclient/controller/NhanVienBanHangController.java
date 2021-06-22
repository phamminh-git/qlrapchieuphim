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
import btlclient.model.LichChieu;
import btlclient.model.PhieuMuaVe;
import btlclient.model.Phim;
import btlclient.model.VeXemPhim;

@Controller
@RequestMapping("/nhanvienbanhang")
public class NhanVienBanHangController {
	public RestTemplate rest=new RestTemplate();
	@GetMapping
	public String ShowView() {
		return "nhanvienbanhang";
	}
	@GetMapping("/saleoff")
	public String ShowViewSaleOff(Model model) {
		model.addAttribute("phims", rest.getForObject("http://localhost:8080/phim", Phim[].class));
		return "viewSaleOff";
	}
	@GetMapping("/searchphim")
	public String searchPhim(@RequestParam("tenphim")String name, Model model) {
		
		List<Phim> phims=Arrays.asList(rest.getForObject("http://localhost:8080/phim/search/{tenphim}",Phim[].class, name));
		model.addAttribute("phims", phims);
		return "viewSaleOff";
	}
	@GetMapping("/selectphim")
	public String HandleSelectPhim(@RequestParam("selectPhim")Long id, Model model) {
		List<LichChieu> lichchieus=Arrays.asList(rest.getForObject("http://localhost:8080/lichchieu/phim/chuachieu/{id}", LichChieu[].class, id));
		model.addAttribute("lichchieus", lichchieus);
		return "chonlichchieu";
	}
	@GetMapping("/selectlichchieu/{id}")
	public String HandleSelectLichChieu(@PathVariable("id")Long id, Model model, HttpSession session) {
		List<GheNgoi> ghengois=Arrays.asList(rest.getForObject("http://localhost:8080/ghengoi/getFree/{id}", GheNgoi[].class, id));
		model.addAttribute("ghengois", ghengois);
		session.setAttribute("danh sach ghe trong", ghengois);
		session.setAttribute("lich chieu", rest.getForObject("http://localhost:8080/lichchieu/getlichchieu/{id}", LichChieu.class, id));
		return "chonGheNgoi";
	}
	@ResponseBody 
	@PostMapping("/banve")
	public String HandleBanVe(@RequestParam("chonghe")ArrayList<Integer> ids, @RequestParam("tennguoimua")String tennguoimua, HttpSession session) {		
		LichChieu lichchieu=(LichChieu) session.getAttribute("lich chieu");
		List<GheNgoi> ghengois=(List<GheNgoi>) session.getAttribute("danh sach ghe trong");
		List<VeXemPhim> ves=new ArrayList<VeXemPhim>();
		for(int index: ids) {
			VeXemPhim ve=new VeXemPhim();
			ve.setGheNgoi(ghengois.get(index-1));
			ve.setLichChieu(lichchieu);
			ves.add(ve);
		}
		PhieuMuaVe phieumua=new PhieuMuaVe();
		phieumua.setListVe(ves);
		phieumua.setTenNguoiMua(tennguoimua);
		rest.postForObject("http://localhost:8080/phieumuave/save", phieumua, PhieuMuaVe[].class);
		return "ban thanh cong";
	}
}

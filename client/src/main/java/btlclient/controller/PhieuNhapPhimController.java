package btlclient.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import btlclient.model.NhaCC;
import btlclient.model.NhanVienQuanLy;
import btlclient.model.PhieuNhapPhim;
import btlclient.model.Phim;

@Controller
@RequestMapping("/phieunhapphim")
public class PhieuNhapPhimController {
	public RestTemplate rest=new RestTemplate();
	@GetMapping
	public String ShowView(Model model) {
		model.addAttribute("phieunhapphims",rest.getForObject( "http://localhost:8080/phieunhapphim", PhieuNhapPhim[].class));
		return "QuanLyPhieuNhapPhim";
	}
	@GetMapping("/get/{id}")
	public String ShowViewManage(@PathVariable("id")Long id, Model model) {
		PhieuNhapPhim phieunhapphim=rest.getForObject("http://localhost:8080/phieunhapphim/get/{id}", PhieuNhapPhim.class, id);
		model.addAttribute("phieunhapphim", phieunhapphim);
		return "ThongTinPhieuNhap";
	}
	@GetMapping("/add")
	public String ShowViewAdd(Model model) {
		model.addAttribute("nhaccs", rest.getForObject("http://localhost:8080/nhacc", NhaCC[].class));
		return "ChonNhaCC";
	}
	@GetMapping("/chonnhacc/{id}")
	public String HandleChonNhaCC(@PathVariable("id") Long id, Model model, HttpSession session) {
		NhaCC nhacc=rest.getForObject("http://localhost:8080/nhacc/searchById/{id}", NhaCC.class, id);
		session.setAttribute("nha cung cap",nhacc);
		model.addAttribute("phim", new Phim());
		model.addAttribute("phimchons", null);
		
		
		return "ThemPhim";
	}
	@PostMapping("/themphim")
	public String HandleThemPhim(Phim phim, Model model, HttpSession session) {
		ArrayList<Phim> dsphim;
		if(session.getAttribute("danh sach phim chon")!=null) {
			dsphim=(ArrayList<Phim>) session.getAttribute("danh sach phim chon");
			
			
		}
		else {
			dsphim=new ArrayList<Phim>();
		}
		dsphim.add(phim);
		session.setAttribute("danh sach phim chon", dsphim);
		model.addAttribute("phimchons", dsphim);
		model.addAttribute("phim", new Phim());
		return "ThemPhim";
	}
	@GetMapping("/save")
	public String HandleSave(HttpSession session) {
		NhaCC nhacc= (NhaCC) session.getAttribute("nha cung cap");
		ArrayList<Phim> dsphim=(ArrayList<Phim>) session.getAttribute("danh sach phim chon");
		PhieuNhapPhim phieunhapphim=new PhieuNhapPhim();
		phieunhapphim.setListPhim(dsphim);
		phieunhapphim.setNhaCC(nhacc);
		NhanVienQuanLy nvql=(NhanVienQuanLy) session.getAttribute("thanh vien");
		phieunhapphim.setNvQuanLy(nvql);
		rest.postForObject("http://localhost:8080/phieunhapphim/save", phieunhapphim, PhieuNhapPhim.class);
		return "redirect:/phieunhapphim";
	}
}

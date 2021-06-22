package btlclient.controller;

import java.lang.ProcessBuilder.Redirect;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import btlclient.model.LichChieu;
import btlclient.model.NhanVienQuanLy;
import btlclient.model.Phim;
import btlclient.model.PhongChieu;

@Controller
@RequestMapping("/nhanvienquanly")
public class NhanVienQuanLyController {
	private RestTemplate rest=new RestTemplate();
	@GetMapping
	public String Show() {
		return "nvql";
	}
	@GetMapping("/lenlich")
	public String showFormLenLich() {
		return "viewLenLich";
	}
	@PostMapping("/lenlich")
	public String HandleLenLich(@RequestParam("ngay")String ngay, @RequestParam("gio") String gio, Model model, HttpSession session) {
		List<PhongChieu> phongs= Arrays.asList(rest.getForObject("http://localhost:8080/phongchieu/searchFreeRoom/{ngay}/{gio}",PhongChieu[].class, ngay, gio));
		List<Phim> phims=Arrays.asList(rest.getForObject("http://localhost:8080/phim", Phim[].class));
		model.addAttribute("phongs", phongs);
		session.setAttribute("phongtrong", phongs);
		model.addAttribute("phims", phims);
		session.setAttribute("danhsachphim", phims);
		session.setAttribute("giochieu", gio);
		session.setAttribute("ngaychieu", ngay);
		return	"formlenlich";	
	}
	@ResponseBody
	@PostMapping("/submit")
	public String HandleSubmit(@RequestParam("idphong")int idphong ,@RequestParam("idphim") int idphim, HttpSession session) {
		List<PhongChieu> phongs=(List<PhongChieu>) session.getAttribute("phongtrong");
		PhongChieu phong=phongs.get(idphong-1);
		List<Phim> phims=(List<Phim>) session.getAttribute("danhsachphim");
		Phim phim=phims.get(idphim-1);
		NhanVienQuanLy nvql=(NhanVienQuanLy) session.getAttribute("thanh vien");
		LichChieu lichchieu=new LichChieu();
		lichchieu.setGioChieu((String)session.getAttribute("giochieu"));
		lichchieu.setNgayChieu((String) session.getAttribute("ngaychieu"));
		lichchieu.setNvQuanLy(nvql);
		lichchieu.setPhim(phim);
		lichchieu.setPhongChieu(phong);
		rest.postForObject("http://localhost:8080/lichchieu/save", lichchieu, LichChieu.class);
		return idphong+"";
	}
	@GetMapping("/quanlyhangngoi")
	public String QuanLyHangGhe() {
		return "redirect:/hangngoi";
	}
	
}

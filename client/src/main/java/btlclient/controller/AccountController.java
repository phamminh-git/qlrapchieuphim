package btlclient.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import btlclient.model.KhachHang;
import btlclient.model.NhanVien;
import btlclient.model.NhanVienBanHang;
import btlclient.model.NhanVienQuanLy;
import btlclient.model.ThanhVien;

@Controller
@RequestMapping("cinema")
public class AccountController {
	private RestTemplate rest=new RestTemplate();
	@GetMapping("/login")
	public String ShowLoginForm(Model model) {
		model.addAttribute("thanhvien", new ThanhVien());
		return "login";
	}
	@PostMapping("/login")
	public String HandleLogin(ThanhVien thanhvien, HttpSession session) {
		ThanhVien tv=rest.postForObject("http://localhost:8080/cinema/login", thanhvien, ThanhVien.class);
		if(tv!=null) {
			System.out.println(tv);		
			if(tv.getVaiTro().equals("admin")) {
				session.setAttribute("thanh vien", tv);
				return "redirect:/admin";
			}
			else if(tv.getVaiTro().equals("khachhang")){
				
				KhachHang khachhang=rest.getForObject("http://localhost:8080/khachhang/searchById/{id}", KhachHang.class, tv.getId());
				session.setAttribute("khachhang", khachhang);
				return "redirect:/khachhang";
			}
			else {
				NhanVien nv=rest.getForObject("http://localhost:8080/nhanvien/get/{id}", NhanVien.class, tv.getId());
				if(nv.getViTri().equals("bán hàng")) {
					NhanVienBanHang nvbh=rest.getForObject("http://localhost:8080/nhanvienbanhang/searchById/{id}", NhanVienBanHang.class, tv.getId());
					session.setAttribute("thanh vien", nvbh);
					return "redirect:/nhanvienbanhang";
				}
				else {
					NhanVienQuanLy nvql=rest.getForObject("http://localhost:8080/nhanvienquanly/searchById/{id}", NhanVienQuanLy.class, tv.getId());
					session.setAttribute("thanh vien", nvql);
					return "redirect:/nhanvienquanly";
				}
			}
			
		}
		else return "test";
	}
	
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("khachhang", new KhachHang());
		return "register";
	}
	@PostMapping("/register")
	@ResponseBody
	public String HandleRegister(KhachHang khachhang, Model model) {
		khachhang.setVaiTro("khachhang");
		KhachHang tv=rest.postForObject("http://localhost:8080/khachhang/register", khachhang, KhachHang.class);
		if(tv!=null) {
			return "thanhcong";
		}
		return "khongthanhcong";
	}
}

package btlclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import btlclient.model.NhanVien;
import btlclient.model.NhanVienBanHang;
import btlclient.model.NhanVienQuanLy;

@Controller
@RequestMapping("admin")
public class AdminController {
	public RestTemplate rest=new RestTemplate();
	@GetMapping
	public String ShowAdminForm() {
		return "admin";
	}
	@GetMapping("/register")
	public String ShowFormRegister(Model model) {
		model.addAttribute("nhanvien", new NhanVien());
		return "themNhanVien";
	}
	@ResponseBody
	@PostMapping("/register")
	public String HandleRegister(NhanVien nhanvien) {
		nhanvien.setVaiTro("nhanvien");
		if(nhanvien.getViTri().equals("bán hàng")) {
			NhanVienBanHang nvbh=new NhanVienBanHang();
			nvbh.setAddress(nhanvien.getAddress());
			nvbh.setEmail(nhanvien.getEmail());
			nvbh.setPhoneNum(nhanvien.getPhoneNum());
			nvbh.setUsername(nhanvien.getUsername());
			nvbh.setPassword(nhanvien.getPassword());
			nvbh.setVaiTro(nhanvien.getVaiTro());
			nvbh.setViTri(nhanvien.getViTri());
			if(rest.postForObject("http://localhost:8080/nhanvienbanhang/register", nvbh, NhanVienBanHang.class)!=null) {
				return "them nhan vien ban hang thanh cong";
			}
			else return "them nhan vien ban hang khong thanh cong";
		}
		else if(nhanvien.getViTri().equals("quản lý")) {
			NhanVienQuanLy nvql=new NhanVienQuanLy();
			nvql.setAddress(nhanvien.getAddress());
			nvql.setEmail(nhanvien.getEmail());
			nvql.setPhoneNum(nhanvien.getPhoneNum());
			nvql.setUsername(nhanvien.getUsername());
			nvql.setPassword(nhanvien.getPassword());
			nvql.setVaiTro(nhanvien.getVaiTro());
			nvql.setViTri(nhanvien.getViTri());
			if(rest.postForObject("http://localhost:8080/nhanvienquanly/register", nvql, NhanVienQuanLy.class)!=null) {
				return "them nhan vien quan ly thanh cong";
			}
			else return "them nhan vien quan ly khong thanh cong";
		}	
		return "khong them";
	}
}

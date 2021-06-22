package btlclient.model;

import java.util.List;


import lombok.Data;

@Data
public class PhieuNhapPhim {
	private long id;
	private List<Phim> listPhim;
	private NhaCC nhaCC;
	private NhanVienQuanLy nvQuanLy;
}

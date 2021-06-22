package btlclient.model;

import java.util.Date;


import lombok.Data;

@Data
public class LichChieu {
	private long id;
	private String ngayChieu;
	private String gioChieu;
	private PhongChieu phongChieu;
	private NhanVienQuanLy nvQuanLy;
	private Phim phim;
}

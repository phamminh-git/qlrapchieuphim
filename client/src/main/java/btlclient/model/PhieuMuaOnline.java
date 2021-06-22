package btlclient.model;

import java.util.List;


import lombok.Data;
@Data
public class PhieuMuaOnline {
	private long id;
	private KhachHang khach;
	private List<VeXemPhim> listVe;
}

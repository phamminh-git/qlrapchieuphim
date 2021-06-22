package btlclient.model;

import java.util.List;


import lombok.Data;

@Data
public class PhieuMuaVe {
	private long id;
	private String tenNguoiMua;
	private List<VeXemPhim> listVe;
}

package btlclient.model;

import java.util.List;


import lombok.Data;

@Data
public class NhaCC {
	private long id;
	private String ten;
	private String diaChi;
	private String sodt;
	private List<Phim> listPhim;
}

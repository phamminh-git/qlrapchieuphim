package btlclient.model;

import java.util.List;

import lombok.Data;

@Data
public class PhongChieu {
	private long id;
	private String ten;
	private List<GheNgoi> listGhe;
}

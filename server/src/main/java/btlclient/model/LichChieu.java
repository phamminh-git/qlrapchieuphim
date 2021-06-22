package btlclient.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="lichchieu")
public class LichChieu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String ngayChieu;
	private String gioChieu;
	@ManyToOne
	private PhongChieu phongChieu;
	@ManyToOne
	private NhanVienQuanLy nvQuanLy;
	@ManyToOne
	private Phim phim;
}

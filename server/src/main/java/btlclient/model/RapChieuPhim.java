package btlclient.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="rapchieuphim")
public class RapChieuPhim {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String ten;
	private String diaChi;
	private String email;
	private String sdt;
	@OneToMany(targetEntity = PhongChieu.class)
	private List<PhongChieu> listPhongChieu;
}

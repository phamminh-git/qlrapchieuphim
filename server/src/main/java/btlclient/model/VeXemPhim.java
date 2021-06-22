package btlclient.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="vexemphim")
public class VeXemPhim {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private float giaVe;
	@ManyToOne
	private LichChieu lichChieu;
	@ManyToOne
	private GheNgoi gheNgoi;
}

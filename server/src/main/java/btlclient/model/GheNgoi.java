package btlclient.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ghengoi")
public class GheNgoi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private boolean isVip;
	@ManyToOne
	private ViTriGhe viTriGhe;
//	@ManyToOne
//	private PhongChieu phongChieu;
}

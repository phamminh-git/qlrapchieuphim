package btlclient.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="nhanvien")
@Inheritance(strategy = InheritanceType.JOINED)
public class NhanVien extends ThanhVien{
	private String viTri;
}

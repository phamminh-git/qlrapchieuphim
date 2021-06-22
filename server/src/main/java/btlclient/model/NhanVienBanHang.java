package btlclient.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="nvbanhang")
public class NhanVienBanHang extends NhanVien{

}

package btlclient.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="nvquanly")
public class NhanVienQuanLy extends NhanVien{

}

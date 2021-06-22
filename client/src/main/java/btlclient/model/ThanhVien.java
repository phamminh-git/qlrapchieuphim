package btlclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ThanhVien {
	private long id;
	private String username;
	private String password;
	private String address;
	private String phoneNum;
	private String email;
	private String vaiTro;
}

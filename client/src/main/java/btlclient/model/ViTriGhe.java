package btlclient.model;

import lombok.Data;

@Data
public class ViTriGhe {
	private long id;
	private HangNgoi hang;
	private SoGhe soGhe;
	public boolean equals(Object obj) {
		if(obj instanceof ViTriGhe) {
			ViTriGhe another=(ViTriGhe) obj;
			if(this.hang.getId()==another.hang.getId()&& this.soGhe.getId()==another.soGhe.getId()) {
				return true;
			}
		}
		return false;
	}
	public int hashCode() {
		return (int) this.id;
	}
}

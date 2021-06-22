package btlclient.model;



import lombok.Data;

@Data
public class GheNgoi {
	private long id;
	private boolean isVip;
	private ViTriGhe viTriGhe;
}

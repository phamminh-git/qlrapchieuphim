package btlclient.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import btlclient.model.PhongChieu;

public interface PhongChieuRepository extends CrudRepository<PhongChieu, Long>{
	@Query(value="select phongchieu.* from cinema.phongchieu where phongchieu.id not in\r\n"
			+ "(select distinct phongchieu.id from cinema.phongchieu, cinema.phim, cinema.lichchieu where\r\n"
			+ "time(lichchieu.gio_chieu)< addtime(time(?1), SEC_TO_TIME(phim.thoiluong*60)) and \n"
			+ "date(ngay_chieu)<date(?2) and phongchieu.id=lichchieu.phong_chieu_id and lichchieu.phim_id=phim.id\r\n"
			+ ")", nativeQuery = true)
	public Iterable<PhongChieu> searchFreeRoom(String gio, String ngay);
	@Query(value="select * from phongchieu where ten like %?1%", nativeQuery = true)
	public Iterable<PhongChieu> searchByName(String name);
}

package btlclient.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import btlclient.model.LichChieu;

public interface LichChieuRepository extends CrudRepository<LichChieu, Long>{
	@Query(value="select * from lichchieu where phim_id=?1", nativeQuery=true)
	public Iterable<LichChieu> GetByMovie(Long id);
	@Query(value="select * from lichchieu where lichchieu.phim_id=?1 and ((date(lichchieu.ngay_chieu)=current_date() "
			+ "and time(lichchieu.gio_chieu)>current_time()) or (date(lichchieu.ngay_chieu)>current_date()))", nativeQuery = true)
	public Iterable<LichChieu> GetMovie(Long id);
}

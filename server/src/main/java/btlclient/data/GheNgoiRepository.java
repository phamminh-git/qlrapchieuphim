package btlclient.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import btlclient.model.GheNgoi;

public interface GheNgoiRepository extends CrudRepository<GheNgoi, Long>{
	@Query(value="select ghengoi.* from cinema.ghengoi,cinema.phongchieu_list_ghe, cinema.lichchieu where ghengoi.id not in "
			+ "(select distinct ghengoi.id from cinema.ghengoi, cinema.vexemphim,cinema.lichchieu, cinema.phongchieu_list_ghe where "
			+ "ghengoi.id=vexemphim.ghe_ngoi_id and vexemphim.lich_chieu_id=lichchieu.id and "
			+ "phongchieu_list_ghe.phong_chieu_id=lichchieu.phong_chieu_id and list_ghe_id=ghengoi.id and lichchieu.id=?1) "
			+ "and ghengoi.id=phongchieu_list_ghe.list_ghe_id and phongchieu_list_ghe.phong_chieu_id=lichchieu.phong_chieu_id "
			+ "and lichchieu.id=?1", nativeQuery = true)
	public Iterable<GheNgoi> GetGheTrong(Long id);
}

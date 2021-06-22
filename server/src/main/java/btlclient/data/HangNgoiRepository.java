package btlclient.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import btlclient.model.HangNgoi;

public interface HangNgoiRepository extends CrudRepository<HangNgoi, Long>{
	@Query(value="select * from hangngoi where ten like %?1%", nativeQuery = true)
	public Iterable<HangNgoi> searchByName(String name);
}

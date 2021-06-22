package btlclient.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import btlclient.model.SoGhe;

public interface SoGheRepository extends CrudRepository<SoGhe, Long>{
	@Query(value="select * from soghe where ten like %?1%", nativeQuery = true)
	public Iterable<SoGhe> SearchByName(String name);
}

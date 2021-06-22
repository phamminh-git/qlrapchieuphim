package btlclient.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import btlclient.model.NhaCC;

public interface NhaCCRepository extends CrudRepository<NhaCC, Long>{
	@Query(value="select * from nhacc where ten like %?1%", nativeQuery = true)
	public Iterable<NhaCC> SearchByName(String name);
}

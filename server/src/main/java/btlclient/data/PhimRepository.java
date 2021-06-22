package btlclient.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import btlclient.model.Phim;

public interface PhimRepository extends CrudRepository<Phim, Long>{
	@Query(value="select * from phim where ten like %?1%", nativeQuery = true)
	public Iterable<Phim> searchMovieByName(String name);
}

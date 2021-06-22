package btlclient.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import btlclient.model.ThanhVien;

public interface ThanhVienRepository extends CrudRepository<ThanhVien, Long>{
	@Query(value = "select *, 0 as clazz_ from thanhvien where username = ?1 and password = ?2", nativeQuery = true)
	public ThanhVien CheckLogin(String username, String password);
}

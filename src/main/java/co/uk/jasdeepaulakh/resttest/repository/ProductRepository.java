package co.uk.jasdeepaulakh.resttest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.uk.jasdeepaulakh.resttest.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query(value="SELECT * from product WHERE LOWER(name) LIKE LOWER(CONCAT('%',:searchTerm,'%'))", nativeQuery=true)
	List<Product> findBySearchName(@Param("searchTerm") String searchTerm);

	
}

package co.uk.jasdeepaulakh.resttest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.uk.jasdeepaulakh.resttest.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	
}

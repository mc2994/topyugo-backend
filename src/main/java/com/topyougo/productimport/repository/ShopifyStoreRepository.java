package com.topyougo.productimport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.topyougo.productimport.model.ShopifyStores;

@Repository
public interface ShopifyStoreRepository extends JpaRepository<ShopifyStores, Long>{
    
	ShopifyStores findShopifyStoresByStoreName(String storeName);
}

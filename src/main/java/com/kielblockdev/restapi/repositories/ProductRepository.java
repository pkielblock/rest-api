package com.kielblockdev.restapi.repositories;

import com.kielblockdev.restapi.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
//Repository is a interface extending the JpaRepository one, Saying the type and the id. (UUID in this case)
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}

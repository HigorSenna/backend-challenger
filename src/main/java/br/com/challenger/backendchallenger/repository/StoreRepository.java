package br.com.challenger.backendchallenger.repository;

import br.com.challenger.backendchallenger.dto.StoreDTO;
import br.com.challenger.backendchallenger.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT new br.com.challenger.backendchallenger.dto.StoreDTO(store.id, store.name) " +
           "FROM Store store                                                                  " +
           "WHERE store.name = :storeName                                                     " )
    StoreDTO find(@Param("storeName") String storeName);

    @Query("SELECT new br.com.challenger.backendchallenger.dto.StoreDTO(store.id, store.name) " +
            "FROM Store store                                                                 " +
            "WHERE 1=1                                                                        " +
            "AND (:storeName is null OR store.name = :storeName)                               " +
            "AND (:storeId is null OR store.id = :storeId)                                    " )
    StoreDTO find(@Param("storeId") Long storeId, @Param("storeName") String storeName);
}

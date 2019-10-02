package br.com.challenger.backendchallenger.repository;

import br.com.challenger.backendchallenger.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}

package com.rakuten.challenge.repo;

import com.rakuten.challenge.entity.CharacterEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CharacterRepo extends CrudRepository<CharacterEntity, Integer> {
    boolean existsByName(String name);

    Optional<CharacterEntity> findByName(String name);
}

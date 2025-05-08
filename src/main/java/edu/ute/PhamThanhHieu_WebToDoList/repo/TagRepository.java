package edu.ute.PhamThanhHieu_WebToDoList.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ute.PhamThanhHieu_WebToDoList.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}


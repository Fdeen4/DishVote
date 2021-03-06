package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface TastyRepository extends CrudRepository <Tasty, Long> {

    Iterable<Tasty> findAllByTheDish_IdAndVoteAtAfter(Long id, LocalDateTime theTime);

}


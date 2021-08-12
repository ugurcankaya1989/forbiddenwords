package com.kariyer.forbiddenwords.repository;

import com.kariyer.forbiddenwords.model.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends CrudRepository<Word,String> {
}

package com.parsermicroservice.parser.Repository;

import com.parsermicroservice.parser.Parser.Parser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParserRepository extends JpaRepository<Parser, Long> {
}

package com.authorization.jwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.authorization.jwt.dto.TokenDTO;

public interface TokenRepository extends JpaRepository<TokenDTO, Long> {

	@Query("""
			select t from TokenDTO t inner join UserDTO u on t.user.id = u.id
			where t.user.id = :userId and t.loggedOut = false
			""")
	List<TokenDTO> findAllTokensByUser(long userId);

	Optional<TokenDTO> findByToken(String token);

}

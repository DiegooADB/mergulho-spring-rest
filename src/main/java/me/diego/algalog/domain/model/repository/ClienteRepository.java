package me.diego.algalog.domain.model.repository;

import me.diego.algalog.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNome(String name);
    List<Cliente> findByNomeContaining(String name);
}

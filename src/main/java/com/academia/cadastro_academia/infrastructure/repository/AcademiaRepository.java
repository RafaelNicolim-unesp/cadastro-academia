package com.academia.cadastro_academia.infrastructure.repository;

import com.academia.cadastro_academia.infrastructure.entity.Academia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AcademiaRepository extends JpaRepository<Academia, Integer> {

    Optional<Academia> findByNome(String nome);

    Optional<Academia> findByCnpj(String cnpj);

    void deleteByCnpj(String cnpj);
}

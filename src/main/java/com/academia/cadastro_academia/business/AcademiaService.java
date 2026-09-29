package com.academia.cadastro_academia.business;

import com.academia.cadastro_academia.infrastructure.entity.Academia;
import com.academia.cadastro_academia.infrastructure.repository.AcademiaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AcademiaService {

    private final AcademiaRepository repository;

    public AcademiaService(AcademiaRepository repository) {
        this.repository = repository;
    }

    public void salvarAcademia(Academia academia) {
        repository.saveAndFlush(academia);
    }

    public Academia buscarAcademiaPorNome(String nome) {
        return repository.findByNome(nome).orElseThrow(
                () -> new RuntimeException("Academia não encontrada com o nome: " + nome)
        );
    }

    @Transactional
    public void deletarAcademiaPorCnpj(String cnpj) {
        repository.deleteByCnpj(cnpj);
    }

    public void atualizarAcademiaPorId(Integer id, Academia academia) {
        Academia academiaExistente = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Academia não encontrada com o ID: " + id)
        );

        Academia academiaAtualizada = Academia.builder()
                .id(academiaExistente.getId())
                .nome(academia.getNome() != null ? academia.getNome() : academiaExistente.getNome())
                .cnpj(academia.getCnpj() != null ? academia.getCnpj() : academiaExistente.getCnpj())
                .endereco(academia.getEndereco() != null ? academia.getEndereco() : academiaExistente.getEndereco())
                .mensalidade(academia.getMensalidade() != null ? academia.getMensalidade() : academiaExistente.getMensalidade())
                .capacidadeAlunos(academia.getCapacidadeAlunos() != null ? academia.getCapacidadeAlunos() : academiaExistente.getCapacidadeAlunos())
                .build();

        repository.saveAndFlush(academiaAtualizada);
    }
}

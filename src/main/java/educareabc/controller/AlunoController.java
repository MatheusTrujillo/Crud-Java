package educareabc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import educareabc.entity.Aluno;
import educareabc.service.AlunoService;

@RestController
@RequestMapping("/users")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    /**
     * Listar todos os alunos.
     *
     * @return Lista de alunos.
     */
    @GetMapping
    public ResponseEntity<List<Aluno>> getAllAlunos() {
        List<Aluno> alunos = alunoService.getAllAlunos();
        return ResponseEntity.ok(alunos);
    }

    /**
     * Buscar aluno por ID.
     *
     * @param id ID do aluno.
     * @return Aluno encontrado ou resposta 404 se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
        Aluno aluno = alunoService.getAlunoById(id);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Criar um novo aluno.
     *
     * @param aluno Objeto aluno a ser criado.
     * @return Aluno criado.
     */
    @PostMapping
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.saveAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    /**
     * Atualizar um aluno existente.
     *
     * @param id ID do aluno a ser atualizado.
     * @param alunoDetails Detalhes do aluno a serem atualizados.
     * @return Aluno atualizado ou resposta 404 se não encontrado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody Aluno alunoDetails) {
        Aluno alunoExistente = alunoService.getAlunoById(id);
        if (alunoExistente != null) {
            alunoExistente.setNome(alunoDetails.getNome());
            alunoExistente.setNota(alunoDetails.getNota());
            Aluno alunoAtualizado = alunoService.saveAluno(alunoExistente);
            return ResponseEntity.ok(alunoAtualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Excluir um aluno por ID.
     *
     * @param id ID do aluno a ser excluído.
     * @return Resposta 204 No Content se excluído com sucesso.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        if (alunoService.getAlunoById(id) != null) {
            alunoService.deleteAluno(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

package educareabc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import educareabc.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}


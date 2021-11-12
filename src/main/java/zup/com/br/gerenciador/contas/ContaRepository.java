package zup.com.br.gerenciador.contas;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zup.com.br.gerenciador.contas.enums.Status;
import zup.com.br.gerenciador.contas.enums.Tipo;
import zup.com.br.gerenciador.contas.model.Conta;

import java.util.List;

public interface ContaRepository extends CrudRepository<Conta, Integer> {
    List<Conta> findAllByid(int id);

    List<Conta> findAllBynome(String nome);

    @Query(value = "SELECT * FROM contas WHERE VALOR BETWEEN :valor*05 AND :valor*1.3", nativeQuery = true)
    List<Conta> findAllByvalor(double valor);

    List<Conta> findAllBystatus(Status status);

    List<Conta> findAllBytipo(Tipo tipo);
}

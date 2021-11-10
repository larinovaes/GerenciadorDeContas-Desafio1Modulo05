package zup.com.br.gerenciador.contas;

import org.springframework.data.repository.CrudRepository;
import zup.com.br.gerenciador.contas.enums.Status;

import java.util.List;

public interface ContaRepository extends CrudRepository<Conta, Integer> {
    List<Conta> findAllByid(int id);
    List<Conta> findAllBynome(String nome);
    List<Conta> findAllByvalor(double valor);
    List<Conta> findAllBystatus(Status status);
}

package zup.com.br.gerenciador.contas;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import zup.com.br.gerenciador.contas.enums.Status;
import zup.com.br.gerenciador.contas.enums.Tipo;
import zup.com.br.gerenciador.contas.model.Conta;

import java.util.List;

public interface ContaRepository extends CrudRepository<Conta, Integer> {
    List<Conta> findAllByid(int id);

    List<Conta> findAllBynome(String nome);

    @Query(value = "SELECT * FROM contas WHERE valor < :valor", nativeQuery = true)
    List<Conta> findAllByvalor(@Param("valor") double valor);

    @Query(value = "SELECT * FROM contas WHERE valor > :valor", nativeQuery = true)
    List<Conta> findAllValorMaiorQue(@Param("valor") double valor);

    @Query(value = "SELECT * FROM contas WHERE valor BETWEEN :valorMenor AND :valorMaior", nativeQuery = true)
    List<Conta> findAllValorEntre(@Param("valorMenor") double valorMenor, @Param("valorMaior") double valorMaior);


    List<Conta> findAllBystatus(Status status);

    List<Conta> findAllBytipo(Tipo tipo);
}

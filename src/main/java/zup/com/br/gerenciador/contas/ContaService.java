package zup.com.br.gerenciador.contas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zup.com.br.gerenciador.contas.enums.Status;

import java.time.LocalDate;


@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta salvarConta(Conta conta) {
        conta.setStatus(conta.getDataDeVencimento().isBefore(LocalDate.now()) ? Status.VENCIDA : Status.AGUARDANDO);
        contaRepository.save(conta);
        return conta;
    }
}

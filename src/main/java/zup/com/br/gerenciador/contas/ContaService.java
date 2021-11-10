package zup.com.br.gerenciador.contas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zup.com.br.gerenciador.contas.enums.Status;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta salvarConta(Conta conta) {
            conta.setDataDeVencimento(conta.getDataDeVencimento());
            return contaRepository.save(conta);
    }

}

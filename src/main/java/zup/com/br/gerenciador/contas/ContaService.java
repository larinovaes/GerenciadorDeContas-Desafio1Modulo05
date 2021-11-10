package zup.com.br.gerenciador.contas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zup.com.br.gerenciador.contas.enums.Status;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta salvarConta(Conta conta) {
        conta.setStatus(conta.getDataDeVencimento().isBefore(LocalDate.now()) ? Status.VENCIDA : Status.AGUARDANDO);
        contaRepository.save(conta);
        return conta;
    }

    public List<Conta> exibirTodasAsContas (String nome, Double valor,Integer id,Status status) {
        if (nome != null) {
            contaRepository.findAllBynome(nome);
        } else if(valor != null) {
            contaRepository.findAllByvalor(valor);
        } else if(id != null) {
            contaRepository.findAllByid(id);
        } else if (status != null) {
            contaRepository.findAllBystatus(status);
        }
        List<Conta> contas = (List<Conta>) contaRepository.findAll();
        return contas;
    }

}

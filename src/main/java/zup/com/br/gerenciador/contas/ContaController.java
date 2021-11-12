package zup.com.br.gerenciador.contas;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zup.com.br.gerenciador.contas.contadto.ContaDTO;
import zup.com.br.gerenciador.contas.contadto.ContaStatusDTO;
import zup.com.br.gerenciador.contas.contadto.ResumoContaDTO;
import zup.com.br.gerenciador.contas.enums.Status;

import zup.com.br.gerenciador.contas.enums.Tipo;
import zup.com.br.gerenciador.contas.exceptions.ValorNaoEncontrado;
import zup.com.br.gerenciador.contas.model.Conta;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaDTO cadastrarConta(@RequestBody @Valid ContaDTO contaDTO) {
        Conta conta = modelMapper.map(contaDTO, Conta.class);
        contaService.salvarConta(conta);
        contaDTO = modelMapper.map(conta, ContaDTO.class);
        return contaDTO;
    }

    @GetMapping
    public List<ResumoContaDTO> exibirTodasAsContas(@RequestParam(required = false) Integer id,
                                                    @RequestParam(required = false) String nome,
                                                    @RequestParam(required = false) Status status,
                                                    @RequestParam(required = false) Double valor) {
        List<ResumoContaDTO> contas = new ArrayList<>();
        for (Conta conta : contaService.exibirTodasAsContas(nome, valor, id, status)) {
            ResumoContaDTO resumo = modelMapper.map(conta, ResumoContaDTO.class);
            contas.add(resumo);
        }
        return contas;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContaDTO pagarconta(@RequestBody ContaStatusDTO contaStatusDTO, @PathVariable Integer id) {
        if (contaStatusDTO.getStatus() == Status.PAGO) {
            Conta contaLocalizada = contaService.pagarConta(id, contaStatusDTO.getStatus());
            ContaDTO contaDTO = modelMapper.map(contaLocalizada, ContaDTO.class);

            return contaDTO;
        }
        throw new RuntimeException();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarConta(@PathVariable Integer id) {
        contaService.deletarConta(id);
    }

    @GetMapping("/{id}")
    public ContaDTO exibirContaEspecificaPeloOid(@PathVariable Integer id) {
        Conta conta = contaService.buscarContaEspecifica(id);
        ContaDTO contaDTO = modelMapper.map(conta, ContaDTO.class);

        return contaDTO;
    }

    @GetMapping("/status/{status}")
    public List<ContaDTO> buscarPorStatus(@PathVariable Status status) {
        //comandos a seguir serve para transformando List<Conta> em uma Lista<ContaDTO>
        List<Conta> contas = contaService.buscarContasPorStatus(status);
        List<ContaDTO> contasDTO = new ArrayList<>();

        for (Conta conta : contas) {
            ContaDTO contaDTO = modelMapper.map(conta, ContaDTO.class);
            contasDTO.add(contaDTO);
        }
        return contasDTO;
    }

    @GetMapping("/tipo/{tipo}")
    public List<ContaDTO> buscarContasPorTipo(@PathVariable Tipo tipo) {
        //comandos a seguir serve para transformando List<Conta> em uma Lista<ContaDTO>
        List<Conta> contas = contaService.buscarContasPorTipo(tipo);
        List<ContaDTO> contaDTOS = new ArrayList<>();

        for (Conta conta : contas) {
            ContaDTO contaDTO = modelMapper.map(conta, ContaDTO.class);
            contaDTOS.add(contaDTO);
        }
        return contaDTOS;
    }

    @GetMapping("/menor-que")
    public List<ContaDTO> buscarContasPorValorMenorQue(@RequestParam("valor") Double valor) {
        List<Conta> conta = contaService.buscarContasPorValorMenorQue(valor);
        List<ContaDTO> contasDTOS = new ArrayList<>();

        for (Conta contaReferencia : conta) {
            ContaDTO contaDTO = modelMapper.map(contaReferencia, ContaDTO.class);
            contasDTOS.add(contaDTO);
        }
        return contasDTOS;
    }

    @GetMapping("/maior-que")
    public List<ContaDTO> buscarContaPorValorMaiorQue(@RequestParam("valor") Double valor) {
        List<Conta> conta = contaService.buscarContasPorValorMaiorQue(valor);
        List<ContaDTO> contasDTOS = new ArrayList<>();

        for (Conta contaReferencia : conta) {
            ContaDTO contaDTO = modelMapper.map(contaReferencia, ContaDTO.class);
            contasDTOS.add(contaDTO);
        }
        return contasDTOS;
    }

    @GetMapping("/valores-aproximados")
    public List<ContaDTO> buscarPorContasComValoresAproximadas(@RequestParam("valorMenor") Double valorMenor,
                                                               @RequestParam("valorMaior") Double valorMaior) {
        List<Conta> conta = contaService.buscarContasAproximadas(valorMenor, valorMaior);
        List<ContaDTO> contasDTOS = new ArrayList<>();

        for (Conta contaReferencia : conta) {
            ContaDTO contaDTO = modelMapper.map(contaReferencia, ContaDTO.class);
            contasDTOS.add(contaDTO);
        }
        return contasDTOS;
    }

}

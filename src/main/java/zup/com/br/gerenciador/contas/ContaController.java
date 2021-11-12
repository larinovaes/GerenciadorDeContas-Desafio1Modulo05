package zup.com.br.gerenciador.contas;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zup.com.br.gerenciador.contas.contadto.ContaDTO;
import zup.com.br.gerenciador.contas.contadto.ContaStatusDTO;
import zup.com.br.gerenciador.contas.contadto.ResumoContaDTO;
import zup.com.br.gerenciador.contas.enums.Status;

import zup.com.br.gerenciador.contas.enums.Tipo;
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
    public ContaDTO apagarconta(@RequestBody ContaStatusDTO contaStatusDTO, @PathVariable Integer id) {
        Conta contaLocalizada = contaService.pagarConta(id, contaStatusDTO.getStatus());
        ContaDTO contaDTO = modelMapper.map(contaLocalizada, ContaDTO.class);

        return contaDTO;
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
    public List<Conta> buscarPorStatus(@PathVariable Status status) {

        return  contaService.buscarContasPorStatus(status);
    }

    @GetMapping("/tipo/{tipo}")
    public List<Conta> buscarContasPorTipo(@PathVariable Tipo tipo) {
        return contaService.buscarContasPorTipo(tipo);
    }

    @GetMapping("/valor/{valor}")
    public List<Conta> buscarContasPorValor(@PathVariable Double valor) {
        return contaService.buscarContasPorValor(valor);
    }


}

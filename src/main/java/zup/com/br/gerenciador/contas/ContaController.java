package zup.com.br.gerenciador.contas;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zup.com.br.gerenciador.contas.contadto.ContaDTO;
import zup.com.br.gerenciador.contas.contadto.ContaStatusDTO;
import zup.com.br.gerenciador.contas.contadto.ResumoContaDTO;
import zup.com.br.gerenciador.contas.enums.Status;

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
    public ContaDTO cadastrarConta(@RequestBody ContaDTO contaDTO) {
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
    public ContaDTO pagarConta(@RequestBody ContaStatusDTO contaStatusDTO, @PathVariable Integer id) {
        Conta contaLocalizada = contaService.pagarConta(id, contaStatusDTO.getStatus());
        ContaDTO contaDTO = modelMapper.map(contaLocalizada, ContaDTO.class);

        return contaDTO;
    }
}

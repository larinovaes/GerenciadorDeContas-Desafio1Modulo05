package zup.com.br.gerenciador.contas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CamposInvalidos.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public MensagemDeErro erroCampoInvalido(CamposInvalidos camposInvalidos) {
        return new MensagemDeErro("Campo obrigatorio, por favor digite novamente");
    }
}

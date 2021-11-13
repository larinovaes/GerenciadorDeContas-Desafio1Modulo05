package zup.com.br.gerenciador.contas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<CampoInvalido> manipularErrosDeValidacao(MethodArgumentNotValidException exception) {
        List<CampoInvalido> erros = new ArrayList<>();

        for (FieldError fieldError : exception.getFieldErrors()) {
            CampoInvalido erroCamposVazio = new CampoInvalido(fieldError.getDefaultMessage(), fieldError.getField());
            erros.add(erroCamposVazio);
        }

        return erros;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MensagemGenerica manipularErroDeTipoNull(HttpMessageNotReadableException exception) {

        MensagemGenerica mensagemGenerica = new MensagemGenerica("Campo obrigatório não encontrado");

        return mensagemGenerica;
    }

    @ExceptionHandler(ContaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CampoInvalido manipularErrosDeContaNaoEcontrada(ContaNaoEncontradaException exception) {
        CampoInvalido camposInvalidos = new CampoInvalido("Conta ".concat(exception.getIdConta().toString())
                .concat(", não encontrada"), "Id");

        return camposInvalidos;
    }
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CampoInvalido manipularErrosDeContaNaoEcontrada(RuntimeException exception) {
        CampoInvalido camposInvalidos = new CampoInvalido("O campo aceita apenas PAGO, por favor digite"
               + " novamente", "Status");

        return camposInvalidos;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemGenerica manipularErrosDeFiltroPorStatus(MethodArgumentTypeMismatchException exception) {
        MensagemGenerica mensagemGenerica = new MensagemGenerica("Requisição inválido");
        return mensagemGenerica;
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MensagemGenerica manipularErrosDeFiltroPorStatus(MissingServletRequestParameterException exception) {
        MensagemGenerica mensagemGenerica = new MensagemGenerica("Parametro não está presente");
        return mensagemGenerica;
    }

}

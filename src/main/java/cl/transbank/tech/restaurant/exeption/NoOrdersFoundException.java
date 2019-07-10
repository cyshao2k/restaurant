package cl.transbank.tech.restaurant.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Orders not found")
public class NoOrdersFoundException extends Exception {

}

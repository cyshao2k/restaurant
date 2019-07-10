package cl.transbank.tech.restaurant.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Table is not used")
public class TableNotUsedException extends Exception {

}

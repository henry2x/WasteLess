package grocer.GroceryStore;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class GroceryStoreNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(GroceryStoreNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String GroceryStoreNotFoundHandler(GroceryStoreNotFoundException ex) {
        return ex.getMessage();
    }
}
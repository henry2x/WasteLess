package grocer.Manager;

public class ManagerNotFoundException extends RuntimeException {

	ManagerNotFoundException(int id) {
        super("Could not find Manager " + id);
    }
}
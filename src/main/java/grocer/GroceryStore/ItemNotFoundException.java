package grocer.GroceryStore;

class ItemNotFoundException extends RuntimeException {

	ItemNotFoundException(Long id) {
        super("Could not find Grocery Store " + id);
    }
}
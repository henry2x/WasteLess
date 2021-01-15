package grocer.GroceryStore;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class GroceryStoreModelAssembler implements RepresentationModelAssembler<GroceryStore, EntityModel<GroceryStore>> {

    @Override
    public EntityModel<GroceryStore> toModel(GroceryStore groceryStore) {

        return EntityModel.of(groceryStore, //
                linkTo(methodOn(GroceryStoreController.class).one(groceryStore.getId())).withSelfRel(), linkTo(methodOn(GroceryStoreController.class).all()).withRel("GroceryStores"));
    }
}
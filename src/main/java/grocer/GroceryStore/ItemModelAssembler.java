package grocer.GroceryStore;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class ItemModelAssembler implements RepresentationModelAssembler<Item, EntityModel<Item>> {

    @Override
    public EntityModel<Item> toModel(Item Item) {

        return EntityModel.of(Item, //
                linkTo(methodOn(ItemController.class).one(Item.getId())).withSelfRel(), linkTo(methodOn(ItemController.class).all()).withRel("Items"));
    }
}
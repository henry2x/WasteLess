package grocer.Customer;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class CustomerItemModelAssembler implements RepresentationModelAssembler<CustomerItem, EntityModel<CustomerItem>> {

    @Override
    public EntityModel<CustomerItem> toModel(CustomerItem CustomerItem) {

        return EntityModel.of(CustomerItem, //
                linkTo(methodOn(CustomerItemController.class).one(CustomerItem.getId())).withSelfRel(), linkTo(methodOn(CustomerItemController.class).all()).withRel("CustomerItems"));
    }
}
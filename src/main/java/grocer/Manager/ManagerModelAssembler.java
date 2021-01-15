package grocer.Manager;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ManagerModelAssembler implements RepresentationModelAssembler<Manager, EntityModel<Manager>> {

    @Override
    public EntityModel<Manager> toModel(Manager Manager) {

        return EntityModel.of(Manager, //
                linkTo(methodOn(ManagerController.class).one(Manager.getId())).withSelfRel(), linkTo(methodOn(ManagerController.class).all()).withRel("Managers"));
    }
}
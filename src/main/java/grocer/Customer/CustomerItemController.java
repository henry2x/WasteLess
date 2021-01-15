package grocer.Customer;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
class CustomerItemController {

    private final CustomerItemRepository repository;
    private CustomerItemModelAssembler assembler;
    
    CustomerItemController(CustomerItemRepository repository, CustomerItemModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
        
    }

    @RequestMapping("/customerItem")
    public String home(Model model) {
    	model.addAttribute("customeritem", repository.findAll());
    	return "items";
    }
    @PostMapping("/saveCustomerItem")
    public String saveItem(@ModelAttribute("customeritem") CustomerItem customeritem) {
    	repository.save(customeritem);
    	return "redirect:/";
    }
    // Aggregate root

    @GetMapping("/CustomerItems")
    CollectionModel<EntityModel<CustomerItem>> all() {

       List<EntityModel<CustomerItem>> CustomerItems = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(CustomerItems, linkTo(methodOn(CustomerItemController.class).all()).withSelfRel());
    }

    @PostMapping("/CustomerItems")
    ResponseEntity<?> newCustomerItem(@RequestBody CustomerItem newCustomerItem) {

        EntityModel<CustomerItem> entityModel = assembler.toModel(repository.save(newCustomerItem));

        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }

    // Single CustomerItem
    @GetMapping("/CustomerItems/{id}")
    EntityModel<CustomerItem> one(@PathVariable Long id) {

        CustomerItem CustomerItem = repository.findById(id) //
                .orElseThrow(() -> new CustomerItemNotFoundException(id));

        return assembler.toModel(CustomerItem);
    }

    @PutMapping("/CustomerItems/{id}")
    ResponseEntity<?> replaceCustomerItem(@RequestBody CustomerItem newCustomerItem, @PathVariable Long id) {

    	CustomerItem updatedCustomerItem = repository.findById(id)
                .map(CustomerItem -> {
                	CustomerItem.setItem(newCustomerItem.getItem());
                	CustomerItem.setQuantity(newCustomerItem.getQuantity());
                    return repository.save(CustomerItem);
                })
                .orElseGet(() -> {
                	newCustomerItem.setId(id);
                    return repository.save(newCustomerItem);
                });
        
        EntityModel<CustomerItem> entityModel = assembler.toModel(updatedCustomerItem);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        
    }

    @DeleteMapping("/CustomerItems/{id}")
    ResponseEntity<?> deleteCustomerItem(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
package grocer.GroceryStore;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
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
class GroceryStoreController {

    private final GroceryStoreRepository repository;
    private GroceryStoreModelAssembler assembler;
    
    GroceryStoreController(GroceryStoreRepository repository, GroceryStoreModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
        
    }
    @RequestMapping("/groceryStore")
    public String home(Model model) {
    	model.addAttribute("grocerystores", repository.findAll());
    	return "index";
    }
    @PostMapping("/saveGroceryStore")
    public String saveGroceryStore(@ModelAttribute("grocerystores") GroceryStore store) {
    	repository.save(store);
    	return "redirect:/";
    }

    // Aggregate root

    @GetMapping("/GroceryStores")
    CollectionModel<EntityModel<GroceryStore>> all() {

        List<EntityModel<GroceryStore>> groceryStores = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList()); 

        return CollectionModel.of(groceryStores, linkTo(methodOn(GroceryStoreController.class).all()).withSelfRel());
    }

    @PostMapping("/GroceryStores")
    ResponseEntity<?> newGroceryStore(@RequestBody GroceryStore newGroceryStore) {

        EntityModel<GroceryStore> entityModel = assembler.toModel(repository.save(newGroceryStore));

        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }

    // Single item
    @GetMapping("/GroceryStores/{id}")
    EntityModel<GroceryStore> one(@PathVariable Long id) {

        GroceryStore GroceryStore = repository.findById(id) //
                .orElseThrow(() -> new GroceryStoreNotFoundException(id));

        return assembler.toModel(GroceryStore);
    }

    @PutMapping("/GroceryStores/{id}")
    ResponseEntity<?> replaceGroceryStore(@RequestBody GroceryStore newGroceryStore, @PathVariable Long id) {

    	GroceryStore updatedGroceryStore = repository.findById(id)
                .map(GroceryStore -> {
                	GroceryStore.setAddress(newGroceryStore.getAddress());
                    return repository.save(GroceryStore);
                })
                .orElseGet(() -> {
                	newGroceryStore.setId(id);
                    return repository.save(newGroceryStore);
                });
        
        EntityModel<GroceryStore> entityModel = assembler.toModel(updatedGroceryStore);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        
    }

    @DeleteMapping("/GroceryStores/{id}")
    ResponseEntity<?> deleteGroceryStore(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
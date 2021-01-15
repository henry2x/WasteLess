package grocer.Manager;

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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@EnableWebMvc
public class ManagerController {

    private final ManagerRepository repository;
    private ManagerModelAssembler assembler;
    

    
    ManagerController(ManagerRepository repository, ManagerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
        
    }
    
    @RequestMapping("/managerController")
    public String home(Model model) {
    	model.addAttribute("managers", repository.findAll());
    	return "index";
    }
    
    @GetMapping("/Manager/{id}")
    public String one(@PathVariable int id, Model model) {

        Manager Manager = repository.findById(id) //
                .orElseThrow(() -> new ManagerNotFoundException(id));
        
        model.addAttribute("manager", Manager);
        return "redirect:/";
    }
    
    @GetMapping("/GetManagers")
    public String get(Model model) {
    	model.addAttribute("managers", repository.findAll());
    	return "index";
    }
    @DeleteMapping("/removeManager/{id}")
    public String deleteManager(@PathVariable int id) {
        repository.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/saveManager")
    public String saveManager(@ModelAttribute("manager") Manager Manager) {
    	repository.save(Manager);
    	return "redirect:/";
    }
   
    // Aggregate root
	
	  @GetMapping("/Managers") 
	  CollectionModel<EntityModel<Manager>> all() {
	  List<EntityModel<Manager>> Managers = repository.findAll() .stream()
	  .map(assembler::toModel) .collect(Collectors.toList());
	  
	  
	  return CollectionModel.of(Managers,linkTo(methodOn(ManagerController.class).all()).withSelfRel()); }
	 
    
    @PostMapping("/Managers")
    ResponseEntity<?> newManager(@RequestBody Manager newManager) {
        EntityModel<Manager> entityModel = assembler.toModel(repository.save(newManager));

        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }

    // Single item
    @GetMapping("/Managers/{id}")
    EntityModel<Manager> one(@PathVariable int id) {

        Manager Manager = repository.findById(id) //
                .orElseThrow(() -> new ManagerNotFoundException(id));

        return assembler.toModel(Manager);
    }
	
    @PutMapping("/Managers/{id}")
    ResponseEntity<?> replaceManager(@RequestBody Manager newManager, @PathVariable int id) {

        Manager updatedManager = repository.findById(id)
                .map(Manager -> {
                    Manager.setName(newManager.getName());
                    return repository.save(Manager);
                })
                .orElseGet(() -> {
                    newManager.setId(id);
                    return repository.save(newManager);
                });
        
        EntityModel<Manager> entityModel = assembler.toModel(updatedManager);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        
    }
	
	/*
	 * @DeleteMapping("/Managers/{id}") ResponseEntity<?>
	 * deleteManager(@PathVariable int id) { repository.deleteById(id); return
	 * ResponseEntity.noContent().build(); }
	 */
    
}
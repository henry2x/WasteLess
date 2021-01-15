package grocer.Order;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import grocer.Status;
import grocer.GroceryStore.Item;

@Controller
@EnableWebMvc
class OrderController {

    private final OrderRepository orderRepository;
    private final OrderModelAssembler assembler;
    
    OrderController(OrderRepository orderRepository, OrderModelAssembler assembler) {
        this.orderRepository = orderRepository;
        this.assembler = assembler;
    }
    @RequestMapping("/getOrders")
    public ModelAndView home(ModelAndView model) {
    	model.addObject("orders", orderRepository.findAll());
    	model.setViewName("ordersPage");
    	return model;
    }
    @PostMapping("/saveOrder")
    public String saveOrder(@ModelAttribute("orders") Order order) {
    	orderRepository.save(order);
    	return "redirect:/";
    }

    @GetMapping("/orders")
    CollectionModel<EntityModel<Order>> all() {

        List<EntityModel<Order>> orders = orderRepository.findAll()
                .stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(orders, //
                linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @GetMapping("/orders/{id}")
    EntityModel<Order> one(@PathVariable Long id) {

        Order order = orderRepository.findById(id) //
                .orElseThrow(() -> new OrderNotFoundException(id));

        return assembler.toModel(order);
    }
    
    @RequestMapping(value = "/addorder", method = RequestMethod.POST)
    public String greetingSubmit(HttpServletRequest request) {
    	Map<String, String[]> parameterMap = request.getParameterMap();
    	
    	String name = parameterMap.get("item_name")[0];
    	String pickup_time = parameterMap.get("pickup_time")[0];
    	String pickup_date = parameterMap.get("pickup_date")[0];
    	orderRepository.save(new Order(name, Status.PENDING, pickup_time, pickup_date));
    	
      return "redirect:/getOrders";  
    }

    @PostMapping("/orders")
    ResponseEntity<EntityModel<Order>> newOrder(@RequestBody Order order) {

        order.setStatus(Status.IN_PROGRESS);
        Order newOrder = orderRepository.save(order);

        return ResponseEntity //
                .created(linkTo(methodOn(OrderController.class).one(newOrder.getId())).toUri()) //
                .body(assembler.toModel(newOrder));
    }

    
    
    @PutMapping("/orders/{id}/complete")
    ResponseEntity<?> complete(@PathVariable Long id) {

      Order order = orderRepository.findById(id) //
          .orElseThrow(() -> new OrderNotFoundException(id));

      if (order.getStatus() == Status.IN_PROGRESS) {
        order.setStatus(Status.COMPLETED);
        return ResponseEntity.ok(assembler.toModel(orderRepository.save(order)));
      }

      return ResponseEntity //
          .status(HttpStatus.METHOD_NOT_ALLOWED) //
          .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
          .body(Problem.create() //
              .withTitle("Method not allowed") //
              .withDetail("You can't complete an order that is in the " + order.getStatus() + " status"));
    }

    @DeleteMapping("/orders/{id}/cancel")
    ResponseEntity<?> cancel(@PathVariable Long id) {

        Order order = orderRepository.findById(id) //
                .orElseThrow(() -> new OrderNotFoundException(id));

        if (order.getStatus() == Status.IN_PROGRESS) {
            order.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(assembler.toModel(orderRepository.save(order)));
        }

        return ResponseEntity //
                .status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body(Problem.create() //
                        .withTitle("Method not allowed") //
                        .withDetail("You can't cancel an order that is in the " + order.getStatus() + " status"));
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam Long id, ModelAndView model) {
        orderRepository.deleteById(id);
        return "redirect:/getOrders";      
    }
}

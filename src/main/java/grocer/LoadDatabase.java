package grocer;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import grocer.Customer.CustomerItem;
import grocer.Customer.CustomerItemRepository;
import grocer.GroceryStore.Address;
import grocer.GroceryStore.GroceryStore;
import grocer.GroceryStore.GroceryStoreRepository;
import grocer.Manager.Manager;
import grocer.Manager.ManagerRepository;
import grocer.Order.Order;
import grocer.Order.OrderRepository;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(ManagerRepository ManagerRepository, OrderRepository orderRepository,
			GroceryStoreRepository groceryStoreRepository, CustomerItemRepository customerItemRepository) {

		return args -> {

		     /*new TestDbAppender().doTask();
			Address test1 = new Address("45 Overlea Blvd", "M4H 1C3", "Toronto");
			Manager m1 = new Manager("Lionel", "Messi");
			GroceryStore g1 = new GroceryStore(test1, "Food Basics");
			groceryStoreRepository.save(g1);
			m1.setG(g1);
			Date d = new Date();
			d.setDate(15);
			Order o = new Order("Salvation Army", Status.PENDING, "","");
			o.setG(g1);
			Order o2 = new Order("Childrens Food Bank", Status.PENDING, "","");
			o2.setG(g1);
			CustomerItem c = new CustomerItem("Kale", 3, 1);
			CustomerItem c2 = new CustomerItem("Salmon", 5, 1);
			CustomerItem c3 = new CustomerItem("Beans", 2, 2);
			CustomerItem c4 = new CustomerItem("Rice", 1, 2);
			CustomerItem c5 = new CustomerItem("Corn", 3, 1);

			customerItemRepository.save(c);
			customerItemRepository.save(c2);
			customerItemRepository.save(c3);
			customerItemRepository.save(c4);
			customerItemRepository.save(c5);
			orderRepository.save(o);
			orderRepository.save(o2);

			Address test = new Address("120 Eglinton Ave E", "M4P 1E2", "Toronto");
			Manager m2 = new Manager("Jimmy", "Boi");
			GroceryStore g2 = new GroceryStore(test, "Walmart");
			groceryStoreRepository.save(g2);
			m1.setG(g2);

			Manager m3 = new Manager("Sal", "Man");
			Address test2 = new Address("101 Lawrence", "M3C 1B4", "Toronto");
			GroceryStore g3 = new GroceryStore(test2, "Sobeys");
			groceryStoreRepository.save(g3);
			m2.setG(g3);

			Order o3 = new Order("Daily Bread Food Bank", Status.COMPLETED, "","");
			o3.setG(g3);

			log.info("Preloading " + ManagerRepository.save(m1));
			log.info("Preloading " + ManagerRepository.save(m2));

			ManagerRepository.findAll().forEach(Manager -> log.info("Preloaded " + Manager));

			orderRepository.save(o); //

			orderRepository.findAll().forEach(order -> {
				log.info("Preloaded " + order);
			});*/

		};
	}
}
package grocer.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerItemRepository extends JpaRepository<CustomerItem, Long> {

}

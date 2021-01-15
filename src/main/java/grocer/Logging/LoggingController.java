package grocer.Logging;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@RestController
public class LoggingController {

	@Autowired
	private LogDAOImpl contactDAO;

	private final Bucket bucket;

	public LoggingController() {
		Bandwidth limit = Bandwidth.classic(2, Refill.greedy(2, Duration.ofMinutes(1)));
		this.bucket = Bucket4j.builder().addLimit(limit).build();
	}


	@RequestMapping(value = "/logs")
	public ModelAndView listLogs(ModelAndView model) throws IOException {
		if (bucket.tryConsume(1)) {
			List<Log> listLogs = contactDAO.list();
			model.addObject("logs", listLogs);
			model.setViewName("rawlogs");
			return model;
		} else {
			model.setStatus(HttpStatus.TOO_MANY_REQUESTS);
			model.setViewName("throttle");
			return model;
		}

	}

	@RequestMapping(value = "/parsedlogs")
	public ModelAndView listParsedLogs(ModelAndView model) throws IOException, SQLException {
		if (bucket.tryConsume(1)) {
		List<ParsedLog> listLogs = contactDAO.parsedList();
		model.addObject("parsedlogs", listLogs);
		model.setViewName("cleanlogs");

		return model;
		}else {
			model.setStatus(HttpStatus.TOO_MANY_REQUESTS);
			model.setViewName("throttle");
			return model;
		}
		
	}

}

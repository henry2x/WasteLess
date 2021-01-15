package grocer;

import java.text.DateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDbAppender {

	   private static final Logger logger = LoggerFactory.getLogger(TestDbAppender.class);
	   public TestDbAppender() {
	      logger.info("Class instance created at {}", 
	                DateFormat.getInstance().format(new Date()));
	   }

	   public void doTask() {
	      logger.trace("In doTask");
	      logger.trace("doTask complete");
	   }


	}
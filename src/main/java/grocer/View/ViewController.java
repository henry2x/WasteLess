package grocer.View;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class ViewController {
	@RequestMapping(value = "/")
	public ModelAndView showHome(ModelAndView model) {
		model.setViewName("index");

		return model;
	}
	
	@RequestMapping(value = "/about")
	public ModelAndView showAbout(ModelAndView model) {
		model.setViewName("about");

		return model;
	}
	
	@RequestMapping(value = "/comingsoon")
	public ModelAndView showComingSoon(ModelAndView model) {
		model.setViewName("comingsoon");

		return model;
	}
	
	
    @RequestMapping(value = "/order-image", method = RequestMethod.GET,
            produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<InputStreamResource> getImage() throws IOException {

    	InputStream in = new URL("https://www.pngkit.com/png/detail/299-2992889_new-order-icon-create-new-order-icon.png").openStream();

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(in));
    }
	
    @RequestMapping(value = "/inventory-image", method = RequestMethod.GET,
            produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<InputStreamResource> getImage2() throws IOException {

    	InputStream in = new URL("https://www.seekpng.com/png/detail/136-1365028_categories-inventory-icon-inventory-icons.png").openStream();

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(in));
    }
	
	
}

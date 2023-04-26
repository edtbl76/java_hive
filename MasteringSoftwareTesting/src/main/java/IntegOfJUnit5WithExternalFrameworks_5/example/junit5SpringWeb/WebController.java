package IntegOfJUnit5WithExternalFrameworks_5.example.junit5SpringWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class WebController {

    @Autowired
    private PageService pageService;

    @RequestMapping(value = "/", method = GET)
    public String greeting() {
        return pageService.getPage();
    }
}

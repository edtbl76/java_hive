package IntegOfJUnit5WithExternalFrameworks_5.example.junit5SpringWeb;

import org.springframework.stereotype.Service;

@Service
public class PageService {


    public String getPage() {
        return "/index.html";
    }
}

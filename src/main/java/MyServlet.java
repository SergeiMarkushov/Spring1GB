import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


@WebServlet(name = "MyServlet", urlPatterns = "/products")
public class MyServlet implements Servlet {

    private static Logger logger = LoggerFactory.getLogger(MyServlet.class);
    private transient ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("New request");
        servletResponse.getWriter().printf("<html><body>");
        for (int i = 1; i <= 10 ; i++) {
            servletResponse.getWriter().printf("<h1>" + new Product(i, getTitle(), getCost()) + "</h1>");
        }
        servletResponse.getWriter().printf("</body></html>");
    }

    @Override
    public String getServletInfo() {
        return "MyServlet";
    }

    @Override
    public void destroy() {
        logger.info("Servlet {} destroyed", getServletInfo());
    }

    public int getCost() {
        return (int) (Math.random()*1000);
    }

    public String getTitle() {
        String[] title = {"Xiaomi", "Iphone", "Huawey", "Simens", "Panasinic",
                        "Alcatel", "Motorola", "Sony", "SoniEricson", "AOC"};
        int l = title.length;
        return title[(int)(Math.random()*l)];
    }

}

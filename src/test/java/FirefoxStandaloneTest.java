import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class FirefoxStandaloneTest {

    public static  void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:5555/wd/hub");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("firefox");
        RemoteWebDriver rDriver = new RemoteWebDriver(url,cap);
        rDriver.get("http://google.com");
        System.out.println(rDriver.getTitle());
    }
}

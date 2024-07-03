import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ChromeStandAloneTest {

    @BeforeTest
    public void startDockerScale() throws IOException, InterruptedException {
        DockerUtil.dockerCommandPerform(DockerCommandType.DOCKERUP);
        Thread.sleep(7000);
        DockerUtil.dockerCommandPerform(DockerCommandType.DOCKERSCALE_CHROME);
        Thread.sleep(4000);
    }

    @Test
    public void chromeTest1() throws MalformedURLException {
        URL url = new URL("http://localhost:4444/wd/hub");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("chrome");
        RemoteWebDriver rDriver = new RemoteWebDriver(url,cap);
        rDriver.get("http://google.com");
        System.out.println(rDriver.getTitle());

    }

    @Test
    public void chromeTest2() throws MalformedURLException {
        URL url = new URL("http://localhost:4444/wd/hub");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("chrome");
        RemoteWebDriver rDriver = new RemoteWebDriver(url,cap);
        rDriver.get("http://yahoo.com");
        System.out.println(rDriver.getTitle());
    }

    @Test
    public void chromeTest3() throws MalformedURLException {
        URL url = new URL("http://localhost:4444/wd/hub");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("chrome");
        RemoteWebDriver rDriver = new RemoteWebDriver(url,cap);
        rDriver.get("http://gmail.com");
        System.out.println(rDriver.getTitle());
    }

    @AfterTest
    public void dockerStopContainers() throws IOException, InterruptedException {
        DockerUtil.dockerCommandPerform(DockerCommandType.DOCKERDOWN);

    }
}

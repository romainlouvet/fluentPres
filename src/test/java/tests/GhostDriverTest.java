package tests;

import static org.fest.assertions.Assertions.assertThat;
import static pages.PosteHomePage.URL_LA_POSTE;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GhostDriverTest {
  private WebDriver                    webDriver;
  protected static DesiredCapabilities dCaps;

  @Before
  public void setUp() throws Exception {

    dCaps = new DesiredCapabilities();
    dCaps.setJavascriptEnabled(true);
    dCaps.setCapability("takesScreenshot", true);

    File file = new File("C:/atelier/outils/phantomjs-1.9.1-windows/phantomjs.exe");
    System.setProperty("phantomjs.binary.path", file.getAbsolutePath());

    webDriver = new PhantomJSDriver(dCaps);
    webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void should_search_correct_zip_code() {

    // GIVEN
    webDriver.get(URL_LA_POSTE);

    // WHEN
    webDriver.findElement(By.id("crit1")).sendKeys("27400");
    webDriver.findElement(By.name("cpform")).submit();

    List<WebElement> results = webDriver.findElements(By.className("resultat"));
    String s = "";
    for (WebElement we : results) {
      s += we.findElement(By.tagName("div")).getText();
    }

    // THEN
    assertThat(s).contains("27400 LOUVIERS ");
  }

  @Test
  public void should_search_unknow_zip_code() {
    // GIVEN
    webDriver.get(URL_LA_POSTE);

    // WHEN
    webDriver.findElement(By.id("crit1")).sendKeys("27499");
    webDriver.findElement(By.name("cpform")).submit();

    List<WebElement> results = webDriver.findElements(By.className("resultat"));
    String s = "";
    for (WebElement we : results) {
      s += we.findElement(By.tagName("div")).getText();
    }

    // THEN
    assertThat(s).contains("");
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }

}

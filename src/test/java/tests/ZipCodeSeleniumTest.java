package tests;

import static org.fest.assertions.Assertions.assertThat;
import static pages.PosteHomePage.URL_LA_POSTE;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created with IntelliJ IDEA. User: rlouvet Date: 02/09/13 Time: 11:05
 */

public class ZipCodeSeleniumTest {

  public WebDriver webDriver = new FirefoxDriver();

  // public WebDriver webDriver = new HtmlUnitDriver();

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
  public void quit() {
    webDriver.quit();
  }

}

package tests;

import static org.fest.assertions.Assertions.assertThat;

import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created with IntelliJ IDEA. User: rlouvet Date: 02/09/13 Time: 11:05
 */

public class ZipCodeTest extends FluentTest {

  public WebDriver webDriver = new ChromeDriver();

  @Test
  public void should_search_correct_zip_code() {
    // GIVEN
    goTo("http://www.laposte.fr/sna/rubrique.php3?id_rubrique=59&recalcul=oui");

    // WHEN
    fill("#crit1").with("27400");
    submit("form[name=cpform]");

    // THEN
    assertThat(find(".resultat").find("div").getTexts()).contains("27400 LOUVIERS ");
  }

  @Test
  public void should_search_unknow_zip_code() {
    // GIVEN
    goTo("http://www.laposte.fr/sna/rubrique.php3?id_rubrique=59&recalcul=oui");

    // WHEN
    fill("#crit1").with("27499");
    submit("form[name=cpform]");

    // THEN
    assertThat(find(".resultat").find("div").getTexts()).contains();
  }

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

}

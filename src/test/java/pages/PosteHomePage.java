package pages;

import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withName;

import java.util.List;

import org.fluentlenium.core.FluentPage;

/**
 * Created with IntelliJ IDEA. User: rlouvet Date: 02/09/13 Time: 11:30
 */
public class PosteHomePage extends FluentPage {
  public static final String URL_LA_POSTE = "http://www.laposte.fr/sna/rubrique.php3?id_rubrique=59";

  @Override
  public String getUrl() {
    return URL_LA_POSTE;
  }

  @Override
  public void isAt() {
    assertThat(findFirst("#crit1")).isDisplayed();
    assertThat(findFirst("#crit2")).isDisplayed();
    assertThat(findFirst("#crit3")).isDisplayed();
  }

  public void searchByZipCode(String zipCode) {
    fill("#crit1").with(zipCode);
    submit("form", withName("cpform"));
  }

  public List<String> getResults() {
    return find(".resultat").find("div").getTexts();
  }

}

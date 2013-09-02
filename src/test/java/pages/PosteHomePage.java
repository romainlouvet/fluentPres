package pages;

import org.fluentlenium.core.FluentPage;

import java.util.List;

import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;

/**
 * Created with IntelliJ IDEA. User: rlouvet Date: 02/09/13 Time: 11:30
 */
public class PosteHomePage extends FluentPage {

  public void isAt() {

    assertThat(findFirst("#crit1")).isDisplayed();
    assertThat(findFirst("#crit2")).isDisplayed();
    assertThat(findFirst("#crit3")).isDisplayed();
  }

  public void searchByZipCode(String zipCode) {
    fill("#crit1").with(zipCode);
    submit("form[name=cpform]");
  }

  public List<String> getResults() {
    return find(".resultat").find("div").getTexts();
  }

}

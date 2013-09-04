package tests;

import org.fest.assertions.fluentlenium.FluentLeniumAssertions;
import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.withName;
import static pages.PosteHomePage.URL_LA_POSTE;

/**
 * Created with IntelliJ IDEA. User: rlouvet Date: 02/09/13 Time: 11:05
 */

public class ZipCodeTest extends FluentTest {

    public WebDriver webDriver = new ChromeDriver();
    //public WebDriver     webDriver = new HtmlUnitDriver();

    @Test
    public void should_search_correct_zip_code() {
        // GIVEN
        goTo(URL_LA_POSTE);

        // WHEN
        fill("#crit1").with("27400");
        submit("form", withName("cpform"));

        // THEN
        assertThat(find(".resultat").find("div").getTexts()).contains("27400 LOUVIERS ");
        FluentLeniumAssertions.assertThat($(".resultat div")).hasText("27400 LOUVIERS ");
    }

    @Test
    public void should_search_unknow_zip_code() {
        // GIVEN
        goTo(URL_LA_POSTE);

        // WHEN
        fill("#crit1").with("27499");
        submit("form", withName("cpform"));

        // THEN
        assertThat(find(".resultat").find("div").getTexts()).contains();
    }

    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }

}

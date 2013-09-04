package tests;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pages.PosteHomePage;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA. User: rlouvet Date: 02/09/13 Time: 11:05
 */

public class ZipCodeTestWithPage extends FluentTest {

  public WebDriver     webDriver = new HtmlUnitDriver();

  @Page
  public PosteHomePage posteHomePage;

  @Before
  public void go() {
      posteHomePage.go();
  }

  @Test
  public void should_search_correct_zip_code() {
    // GIVEN

    // WHEN
    posteHomePage.isAt();
    posteHomePage.searchByZipCode("27400");

    // THEN
    assertThat(posteHomePage.getResults()).contains("27400 LOUVIERS ");
  }

  @Test
  public void should_search_unknow_zip_code() {
    // GIVEN

    // WHEN
    posteHomePage.isAt();
    posteHomePage.searchByZipCode("27499");

    // THEN
    assertThat(posteHomePage.getResults()).hasSize(0);
  }

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

}

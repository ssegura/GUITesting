package psa.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.seljup.SeleniumExtension;

@ExtendWith(SeleniumExtension.class)
public class BookingTestChrome {

	private ChromeDriver driver;

	public BookingTestChrome(ChromeDriver driver) {
		this.driver = driver;
	}
	
    @Before
    public void setUp() throws Exception {
      driver = new ChromeDriver();
    }

    @Test
    public void testBooking() throws Exception {
      driver.get("https://www.booking.com");
      driver.findElement(By.id("ss")).click();
      driver.findElement(By.id("ss")).clear();
      driver.findElement(By.id("ss")).sendKeys("Cádiz");
     // driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='From cosy country homes to funky city flats'])[1]/following::li[1]")).click();
      driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='+'])[5]/following::span[1]")).click();
      driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Check-in day'])[1]/following::i[3]")).click();
      assertTrue(isElementPresent(By.xpath("//div[@id='filter_popular_activities']/div/p")));
      assertEquals("2", driver.findElement(By.id("group_adults")).getAttribute("value"));
    }

    private boolean isElementPresent(By by) {
      try {
        driver.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }
}
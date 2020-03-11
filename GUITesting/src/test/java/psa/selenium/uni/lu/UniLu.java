package psa.selenium.uni.lu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.seljup.SeleniumExtension;

@ExtendWith(SeleniumExtension.class)
public class UniLu {

	static ChromeDriver driver;

	public UniLu(ChromeDriver d) {
		driver = d;
	}

	@BeforeAll
	static void setup() {
		// Screenshots
		System.setProperty("sel.jup.screenshot.at.the.end.of.tests", "true");
		System.setProperty("sel.jup.screenshot.format", "png");
		System.setProperty("sel.jup.output.folder", ".\\output\\media\\uni\\");
	}

	@AfterAll
	static void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	@DisplayName("Display correct title in Chrome")
	public void titleChromeTest() {
		driver.get("https://wwwen.uni.lu/");
		assertEquals("Home", driver.getTitle());
	}

	@Test
	@DisplayName("Display correct title in Firefox")
	public void titleFirefoxTest() {
		// TODO: Check that the title of the homepage of the University of Luxembourg is equal to "Home" in Firefox.
	}

	@Test
	@DisplayName("Display correct title in Chrome (headless)")
	public void titleTestHeadlessChrome() {
		// TODO: Check that the title of the homepage of the University of Luxembourg is equal to "Home" using headless Chrome.
	}

	@Test
	@DisplayName("Display correct campus info for Bachelor in AIT")
	public void testBachelorCampus() throws Exception {
		driver.get("https://wwwen.uni.lu/");

		// Accept cookie consent
		driver.findElement(By.xpath("/html/body/div[2]/div/a[2]")).click();
		
		// Mouseover on Studies dropdown menu
		Actions action = new Actions(driver);
		WebElement btn = driver.findElement(By.xpath("//*[@id=\"nav\"]/li[6]"));
		action.moveToElement(btn).perform();

		// Thread.sleep just for user to notice the event
		Thread.sleep(3000);

		// Click on Bachelors
		driver.findElement(By.linkText("Bachelors")).click();

		// TODO:  Click on Bachelor in Applied Information Technology
		
		// Assert campus is "Belval"
		assertEquals("Campus: Belval",
				driver.findElement(By.xpath("//div[@id='main']/table/tbody/tr/td[3]/div/div[6]/ul/li[4]")).getText());
	}

	@Test
	@DisplayName("Search feature")
	public void testSearch() throws Exception {
		driver.get("https://wwwen.uni.lu/");
		// TODO: Type "erasmus" in the search textbox and submit
		assertTrue(driver.findElement(By.xpath("//div[@id='module_result']/div[2]/form/div/div")).getText()
				.matches("^Search for \"erasmus\" returned [\\s\\S]*$"));
	}

	// Create a Junit 5 parameterized test to test the search feature with different search keywords

}
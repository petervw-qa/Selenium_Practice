package google.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GooglePageTest {

	private static RemoteWebDriver driver;
	private WebElement targ;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());
		driver.manage().window().setSize(new Dimension(1366, 768));

	}

	@Test
	public void test() throws InterruptedException {
		// tell the webdriver where to go
		driver.get("https://google.com");

		// tell the webdriver what element to interact with 
		targ = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input"));

		// enter search term
		targ.sendKeys("Kittens");
		targ.submit();

		// tell the webdriver where to look
		targ = driver.findElement(By.xpath("//*[@id=\"iur\"]/div[2]/div/div/div[10]/a/g-img/div"));
		targ.click();

		// assert equals
		String result = driver.getTitle();
		assertEquals("Kittens - Google Search", result);
	}

	@After
	public void tearDown() {
		driver.close();
	}

	public static ChromeOptions chromeCfg() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		ChromeOptions cOptions = new ChromeOptions();

		// Settings
		prefs.put("profile.default_content_setting_values.cookies", 2);
		prefs.put("network.cookie.cookieBehavior", 2);
		prefs.put("profile.block_third_party_cookies", true);

		// Create ChromeOptions to disable Cookies pop-up
		cOptions.setExperimentalOption("prefs", prefs);

		return cOptions;
	}

}

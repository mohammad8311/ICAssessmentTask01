package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EventScheduler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		try {
            //Navigate the Event Scheduler
            driver.get("event_scheduler_url");

            // Fill in the event create form
            WebElement eventNameInput = driver.findElement(By.id("event_name"));
            eventNameInput.sendKeys("Example Event Name");

            WebElement eventDateInput = driver.findElement(By.id("date"));
            eventDateInput.sendKeys("2024-02-11"); // date format should follow business rules

            WebElement participantsEmailInput = driver.findElement(By.id("participant_emails"));
            participantsEmailInput.sendKeys("participant1@example.com,participant2@example.com,participant3@example.com");

            // Submit the create event form
            WebElement submitButton = driver.findElement(By.id("submit_button"));
            submitButton.click();

            Thread.sleep(5000);

            // Verify that the event appears in the user's event list with the correct details.
            WebElement createdEventPage = driver.findElement(By.xpath("//div[@class='event-item'][1]"));
            String eventName = createdEventPage.findElement(By.className("event-name")).getText();
            String eventDate = createdEventPage.findElement(By.className("event-date")).getText();
            String participantsEmail = createdEventPage.findElement(By.className("event-participants")).getText();

            // assertion based on the expected values 
            assert eventName.equals("Example Event Name") : "Event name doesn't match";
            assert eventDate.equals("2024-02-11") : "Event date doesn't match";
            assert participantsEmail.equals("participant1@example.com, participant2@example.com, participant2@example.com") : "Participants list doesn't match";

            System.out.println("Passed: Event creation and verification successful");
            

        } 
		
		catch (Exception e) 
		{
            e.printStackTrace();
        } 
		
		// Close the browser
		finally 
		{
            driver.quit();
        }

	}

}

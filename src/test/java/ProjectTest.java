import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j

public class ProjectTest {



        private WebDriver driver;
        private String urlAcodemy;



        @BeforeEach
        public void webDriverSetUp() throws Exception{
            driver = new ChromeDriver();
            urlAcodemy = "https://shop.acodemy.lv";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        }

    @Test
    public void couponFieldVisibility() throws Exception {
        driver.get(urlAcodemy);
        driver.findElement(By.xpath("//img[contains(@src,'https://shop.acodemy.lv/wp-content/uploads/2021/08/belt-2-324x324.jpg')]")).click();
        driver.findElement(By.name("add-to-cart")).click();
        driver.findElement(By.xpath("//div[@id='content']/div/div/div/a")).click();
        Boolean isDisplayedField = driver.findElement(By.id("coupon_code")).isDisplayed();
        System.out.println(isDisplayedField);
        assertTrue(isDisplayedField);
    }



    @Test
    public void couponRemove() throws Exception {
        driver.get(urlAcodemy);
        driver.findElement(By.xpath("//img[contains(@src,'https://shop.acodemy.lv/wp-content/uploads/2021/08/single-1-324x324.jpg')]")).click();
        driver.findElement(By.name("add-to-cart")).click();
        driver.findElement(By.linkText("€2.00 1 item")).click();
        driver.findElement(By.id("coupon_code")).click();
        driver.findElement(By.id("coupon_code")).sendKeys("ACODEMY20OFF");
        driver.findElement(By.name("apply_coupon")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("[Remove]")).click();

    }


        @Test
        public void couponCodeExpired(){
            driver.get(urlAcodemy);
            driver.findElement(By.xpath("//img[contains(@src,'https://shop.acodemy.lv/wp-content/uploads/2021/08/cap-2-324x324.jpg')]")).click();
            driver.findElement(By.name("add-to-cart")).click();
            driver.findElement(By.xpath("//div[@id='content']/div/div/div/a")).click();
            driver.findElement(By.id("coupon_code")).click();
            driver.findElement(By.id("coupon_code")).sendKeys("EXPIRED");
            driver.findElement(By.name("apply_coupon")).click();
            Boolean isDisplayedError = driver.findElement(By.className("woocommerce-error")).isDisplayed();
            System.out.println(isDisplayedError);
            assertTrue(isDisplayedError);



        }

        @Test

        public void CoponCodeApply(){

            driver.get(urlAcodemy);
            driver.findElement(By.xpath("//img[contains(@src,'https://shop.acodemy.lv/wp-content/uploads/2021/08/hoodie-with-zipper-2-324x324.jpg')]")).click();
            driver.findElement(By.name("add-to-cart")).click();
            driver.findElement(By.xpath("//div[@id='content']/div/div/div/a")).click();
            driver.findElement(By.id("coupon_code")).click();
            driver.findElement(By.id("coupon_code")).sendKeys("ACODEMY10OFF");
            driver.findElement(By.name("apply_coupon")).click();

        }

        @Test
        public void ApplyCopons(){

            driver.get(urlAcodemy);
            driver.findElement(By.xpath("//*[@id=\"main\"]/ul/li[2]/a[1]")).click();
            WebElement btn = driver.findElement(By.name("add-to-cart"));
            btn.click();
            WebElement messageCart = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a"));
            messageCart.click();
            WebElement couponField = driver.findElement(By.id("coupon_code"));
            couponField.click();


        }

        @Test

        public void notApplySeveralCoupons() throws InterruptedException {

            driver.get(urlAcodemy);
            driver.findElement(By.xpath("//*[@id=\"main\"]/ul/li[5]/a[1]/img")).click();
            WebElement btn = driver.findElement(By.name("add-to-cart"));
            btn.click();
            WebElement messageCart = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a"));
            messageCart.click();
            WebElement couponField = driver.findElement(By.id("coupon_code"));
            couponField.sendKeys("ACODEMY10OFF");
            WebElement applyCouponCode = driver.findElement(By.name("apply_coupon"));
            applyCouponCode.click();
            Thread.sleep(2000);
            driver.findElement(By.id("coupon_code")).sendKeys("ACODEMY20OFF");
            WebElement applySecondCouponCode = driver.findElement(By.name("apply_coupon"));
            applySecondCouponCode.click();

        }



    @Test
    public void errorMessage() throws Exception {
        driver.get(urlAcodemy);
        driver.findElement(By.xpath("//img[contains(@src,'https://shop.acodemy.lv/wp-content/uploads/2021/08/long-sleeve-tee-2-324x324.jpg')]")).click();
        driver.findElement(By.name("add-to-cart")).click();
        driver.findElement(By.xpath("//div[@id='content']/div/div/div/a")).click();
        driver.findElement(By.id("coupon_code")).click();
        driver.findElement(By.id("coupon_code")).sendKeys("NOTACOUPON");
        driver.findElement(By.name("apply_coupon")).click();
        Thread.sleep(4000);
        Boolean isDisplayedError = driver.findElement(By.xpath("//*[@id=\"post-7\"]/div/div/div[1]/ul/li")).isDisplayed();
        System.out.println(isDisplayedError);
        assertTrue(isDisplayedError);

    }


    @Test
    public void registrationError() throws Exception {
        driver.get(urlAcodemy);
        driver.findElement(By.linkText("My account")).click();
        driver.findElement(By.id("reg_username")).click();
        driver.findElement(By.id("reg_username")).sendKeys("d");
        driver.findElement(By.name("register")).click();
        assertEquals("Error: Please provide a valid email address.","Error: Please provide a valid email address.", driver.findElement(By.xpath("//div[@id='content']/div/div/ul/li")).getText());
    }





    @AfterEach
        public void tearDown() {
            driver.quit();

        }



}

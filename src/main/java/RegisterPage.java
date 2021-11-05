import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


@Slf4j

public class RegisterPage {

    //Creating Objects (Variables + locator)

    WebDriver driver = new ChromeDriver();

    private final WebElement userNameInput = driver.findElement(By.id("reg_username"));
    private final WebElement emailInput = driver.findElement(By.id("reg_email"));
    private final WebElement passwordInput = driver.findElement(By.id("reg_password"));
    private final WebElement btnRegister = driver.findElement(By.id("register"));
    private final WebElement errorMessage = driver.findElement(By.id("woocommerce-error"));


    // actions


    public RegisterPage setUserName(String username){
        userNameInput.sendKeys("Diana");
        return this;

    }

    public RegisterPage setEmail(String email ){
        emailInput.sendKeys("kokoloko1@gmail.com");
        return this;
    }

    public RegisterPage passwordValidation(String password){
        passwordInput.sendKeys("kokoloko123");
        return this;
    }

    public void clickButton(){
        btnRegister.click();
    }

    public boolean isError(){
        if(errorMessage.isDisplayed()){
            return true;
        }
            else {
                return false;
            }

    }

}

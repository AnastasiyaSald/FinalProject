package case03;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class PagesPage03 extends BasePage03 {

    private static PagesPage03 instance;

    private PagesPage03 (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public static PagesPage03 getInstance(WebDriver driver) {
        if (instance == null) {
            instance = new PagesPage03(driver);
        }
        return instance;
    }

    @FindBy(xpath = "//div[@class='wrap']//a[@class='page-title-action' and contains(text(), 'Add New Page')]")
    WebElement addNewPageButton;

    @FindBy(xpath = "//div[@class='components-modal__screen-overlay']//div[@class='components-modal__header-heading-container']//h1[@id='components-modal-header-0']")
    WebElement chooseAPaternModalWindow;

    @FindBy(xpath = "//div[@class='editor-start-page-options__modal-content']//div[@class='block-editor-block-patterns-list__list-item']//div[@id='twentytwentyfour/page-newsletter-landing']")
    WebElement lastTemplateIsUploaded;

    public void clickOnAddNewPageButton (){
        wait.until(ExpectedConditions.elementToBeClickable(addNewPageButton));
        addNewPageButton.click();
    }

    public boolean chooseAPatternModalIsOpened() {
        wait.until(ExpectedConditions.visibilityOf(chooseAPaternModalWindow));
        wait.until(ExpectedConditions.visibilityOf(lastTemplateIsUploaded));
        return chooseAPaternModalWindow.isDisplayed() && lastTemplateIsUploaded.isDisplayed();
    }

    @FindBy(xpath = "//div[@class='editor-start-page-options__modal-content']//div[@class='block-editor-block-patterns-list__item-title' and contains(text(), 'Portfolio home image gallery')]")
            WebElement selectedPattern;

    public void clickOnSelectedPattern(){
        selectedPattern.click();
    }

    @FindBy(xpath = "//div[contains(@class, 'editor-visual-editor__post-title-wrapper')]//h1[@aria-label='Add title']")
    WebElement addTitleField;

    public void enterTextInTheTitleField(String text) {
        WebElement editorIframe = driver.findElement(By.className("block-editor-iframe__html"));
        driver.switchTo().frame(editorIframe);
        wait.until(ExpectedConditions.visibilityOf(addTitleField));
        addTitleField.click();
        addTitleField.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        addTitleField.sendKeys(text);
        driver.switchTo().defaultContent();
    }

    @FindBy(xpath = "//h1[contains(@class, 'block-editor-rich-text__editable') and contains(@class, 'wp-block-heading')]")
    WebElement articleTextField;

    public void enterTextInTheArticleField (String text) {
        WebElement editorIframe = driver.findElement(By.className("block-editor-iframe__html"));
        driver.switchTo().frame(editorIframe);
        articleTextField.click();
        articleTextField.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE); //нажми ctrl + a (выделение), а потом нажми Backspace
        articleTextField.sendKeys(text);
        wait.until(ExpectedConditions.textToBePresentInElement(articleTextField, text));
        driver.switchTo().defaultContent();
    }
    @FindBy(xpath = "//div[@class='editor-header__settings']//button[contains(text(), 'Publish')]")
    WebElement publishButton;

    @FindBy(xpath = "//div[@class='editor-post-publish-panel']//button[contains(@class, 'editor-post-publish-button__button') and contains(text(), 'Publish')]")
    WebElement secondPublishButton;

    public void clickPublishButtonTwice(){
        publishButton.click();
        secondPublishButton.click();
    }

    @FindBy(xpath = "//div[contains(@class, 'editor-header') and contains(@class, 'edit-post-header')]//div//*[local-name()='svg']/*[local-name()='path' and starts-with(@d, 'M20 10c0-5.51')]")
    WebElement wButton;

    public void returnBackToPagesPage (){
        wButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(addNewPageButton));
    }

    @FindBy(xpath = "//table[@class='wp-list-table widefat fixed striped table-view-list pages']//tbody[@id='the-list']//a[@class='row-title' and contains(text(), 'Nastya') network and contains(text(), 'Title')]")
    WebElement titleEnteredInTheTable;

    public void titleIsDisplayedInTheTable () {
        Assert.assertTrue(titleEnteredInTheTable.isDisplayed());
    }
}

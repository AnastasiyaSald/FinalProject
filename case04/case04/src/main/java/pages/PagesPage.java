package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import webdriver.Browser;

import java.time.Duration;

public class PagesPage extends BasePage {

    public PagesPage() {
        super();
    }

    private static final By addNewPageButtonLocator = By.xpath(
            "//div[@class='wrap']//a[@class='page-title-action' and contains(text(), 'Add New Page')]");

    private static final By chooseAPaternModalWindowLocator = By.xpath(
            "//div[@class='components-modal__screen-overlay']//div[@class='components-modal__header-heading-container']//h1[@id='components-modal-header-0']");

    private static final By lastTemplateIsUploadedLocator = By.xpath(
            "//div[@class='editor-start-page-options__modal-content']//div[@class='block-editor-block-patterns-list__list-item']//div[@id='twentytwentyfour/page-newsletter-landing']");

    private static final By selectedPatternLocator = By.xpath(
            "//div[@class='editor-start-page-options__modal-content']//div[@class='block-editor-block-patterns-list__item-title' and contains(text(), 'Portfolio home image gallery')]");

    private static final By addTitleFieldLocator = By.xpath(
            "//div[contains(@class, 'editor-visual-editor__post-title-wrapper')]//h1[@aria-label='Add title']");

    private static final By editorIframeLocator = By.xpath("//iframe[@name='editor-canvas']");

    private static final By articleTextFieldLocator = By.xpath(
            "//h1[contains(@class, 'block-editor-rich-text__editable') and contains(@class, 'wp-block-heading')]");

    private static final By publishButtonLocator = By.xpath(
            "//div[@class='editor-header__settings']//button[contains(text(), 'Publish')]");

    private static final By secondPublishButtonLocator = By.xpath(
            "//div[@class='editor-post-publish-panel']//button[contains(@class, 'editor-post-publish-button__button') and contains(text(), 'Publish')]");

    private static final By wButtonLocator = By.xpath(
            "//a[@aria-label='View Pages' or contains(@class, 'edit-post-fullscreen-mode-close')]");

    private static final By titleEnteredInTheTableLocator = By.xpath(
            "//table[@class='wp-list-table widefat fixed striped table-view-list pages']//tbody[@id='the-list']//a[@class='row-title' and contains(text(), 'Nastya') and contains(text(), 'Title')]");

    private static final By bulkActionsDropdownLocator = By.xpath("//div[@class='tablenav top']//select[@id='bulk-action-selector-top']");

    private static final By applyButtonLocator = By.xpath("//input[@class='button action' and contains(@id, 'doaction')]");
    private static final By statusDropdownLocator = By.xpath("//div[@class='inline-edit-col']//div[@class='inline-edit-group wp-clearfix']//select[@name='_status' and .//option[contains(text(), '— No Change —')]]");
    private static final By updateButtonLocator = By.xpath("//div[@class='submit inline-edit-save']//input[@id='bulk_edit']");

    public void clickOnAddNewPageButton() {
        findVisibleElement(addNewPageButtonLocator).click();
    }

    public boolean chooseAPatternModalIsOpened() {
        WebElement modal = findVisibleElement(chooseAPaternModalWindowLocator);
        WebElement template = findVisibleElement(lastTemplateIsUploadedLocator);
        return modal.isDisplayed() && template.isDisplayed();
    }

    public void clickOnSelectedPattern() {
        findVisibleElement(selectedPatternLocator).click();
    }

    public void enterTextInTheTitleField(String text) {
        WebElement editorIframe = getDriver().findElement(editorIframeLocator);
        getDriver().switchTo().frame(editorIframe);

        WebElement titleField = findVisibleElement(addTitleFieldLocator);
        titleField.click();
        titleField.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        titleField.sendKeys(text);
        getDriver().switchTo().defaultContent();
    }

    public void enterTextInTheArticleField(String text) {
        WebElement editorIframe = getDriver().findElement(editorIframeLocator);
        getDriver().switchTo().frame(editorIframe);

        WebElement articleField = findVisibleElement(articleTextFieldLocator);
        articleField.click();
        articleField.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        articleField.sendKeys(text);

        WebDriverWait localWait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        localWait.until(ExpectedConditions.textToBePresentInElement(articleField, text));

        getDriver().switchTo().defaultContent();
    }
    private static final By SaveDraftButtonLocator = By.xpath("//div[@id='editor']//div[@class='editor-header__settings']//button[contains(text(), 'Save draft')]");

    public void clickSaveDraftButton () {
        findVisibleElement(SaveDraftButtonLocator).click();
    }

    public void clickPublishButtonTwice() {
        findVisibleElement(publishButtonLocator).click();

        WebDriverWait localWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        localWait.until(ExpectedConditions.elementToBeClickable(secondPublishButtonLocator));

        getDriver().findElement(secondPublishButtonLocator).click();
    }

    public void returnBackToPagesPage() {
        findVisibleElement(wButtonLocator).click();
        WebDriverWait localWait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        localWait.until(ExpectedConditions.elementToBeClickable(addNewPageButtonLocator));
    }

    public void titleIsDisplayedInTheTable(String expectedTitle) {

        By dynamicLocator = getTitleInTableLocator(expectedTitle);
        WebElement titleRow = findVisibleElement(dynamicLocator);
        Assert.assertTrue(titleRow.isDisplayed(), "Созданная страница с заголовком '" + expectedTitle + "' не найдена в таблице!");
    }

    private By getTitleInTableLocator(String expectedTitle) {
        String xpathExpression = String.format(
                "//table[@class='wp-list-table widefat fixed striped table-view-list pages']//tbody[@id='the-list']//a[@class='row-title' and contains(text(), '%s')]",
                expectedTitle
        );
        return By.xpath(xpathExpression);
    }

    public void clickOnTheDraftPageItemInTheTable(String expectedTitle){
        By dynamicLocator = getTitleInTableLocator(expectedTitle);
        WebElement titleClickableRow = findVisibleElement(dynamicLocator);
        titleClickableRow.click();
    }

    private By getCheckboxNearCreatedPageLocator(String expectedTitle) {
        String xpathExpression = String.format(
                "//tr[.//span[@class='screen-reader-text' and contains(text(), '%s')]]//input[@type='checkbox']",
                expectedTitle);
        return By.xpath(xpathExpression);
    }

    public void clickCheckboxNearCreatedPage (String expectedTitle) {
       By dynamicCheckBoxLocator = getCheckboxNearCreatedPageLocator(expectedTitle);
       WebElement checkboxIsFound = findVisibleElement(dynamicCheckBoxLocator);
       checkboxIsFound.click();
    }

    public void selectEditFromDropdown() {
        WebElement dropdownElement = findVisibleElement(bulkActionsDropdownLocator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Edit");

    }

    public void clickOnApplyButton(){
        WebElement applyButton = findVisibleElement(applyButtonLocator);
        applyButton.click();
    }

    public void selectDraftOptionInTheDropdown(){
        WebElement dropdownElement = findVisibleElement(statusDropdownLocator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Draft");
    }

    public void clickUpdateButton(){
        findVisibleElement(updateButtonLocator).click();
    }

    private By updatedTitleInTableWithDraftWordLocator(String expectedTitle) {
        String xpathExpression = String.format(
                "//tr[.//a[@class='row-title' and contains(text(), '%s')] and .//span[@class='post-state' and contains(text(), 'Draft')]]",
                expectedTitle
        );
        return By.xpath(xpathExpression);
    }

    public void verifyPageStatusIsDraft(String expectedTitle) {
        By draftPageLocator = updatedTitleInTableWithDraftWordLocator(expectedTitle);
        boolean isDraftDisplayed = findVisibleElement(draftPageLocator).isDisplayed();
        Assert.assertTrue(isDraftDisplayed,"Страница '" + expectedTitle + "' со статусом 'Draft' не найдена в таблице!");
    }



}

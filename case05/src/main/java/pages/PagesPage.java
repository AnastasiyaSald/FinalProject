package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class PagesPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(PagesPage.class);

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
    private static final By tableOnPagesPageLocator = By.xpath("//div[@class='wrap']//table[@class='wp-list-table widefat fixed striped table-view-list pages']");

    public void clickOnAddNewPageButton() {
        findVisibleElement(addNewPageButtonLocator).click();
    }

    public boolean pagesPageIsDisplayed() {
        logger.info("Checking if Pages page is displayed");
        WebElement buttonIsPresent = findVisibleElement(addNewPageButtonLocator);
        return buttonIsPresent.isDisplayed();
    }

    public boolean chooseAPatternModalIsOpened() {
        logger.info("Verifying that choose a pattern modal is opened");
        WebElement modal = findVisibleElement(chooseAPaternModalWindowLocator);
        WebElement template = findVisibleElement(lastTemplateIsUploadedLocator);
        return modal.isDisplayed() && template.isDisplayed();
    }

    public void clickOnSelectedPattern() {
        findVisibleElement(selectedPatternLocator).click();
    }

    public void enterTextInTheTitleField(String text) {
        logger.debug("Checking that entered text is displayed in the title: {}", text);
        WebElement editorIframe = getDriver().findElement(editorIframeLocator);
        getDriver().switchTo().frame(editorIframe);

        WebElement titleField = findVisibleElement(addTitleFieldLocator);
        titleField.click();
        titleField.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        titleField.sendKeys(text);
        getDriver().switchTo().defaultContent();
    }

    public void enterTextInTheArticleField(String text) {
        logger.debug("Checking that entered text is displayed in the article: {}", text);
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

    public void clickSaveDraftButton() {
        findVisibleElement(SaveDraftButtonLocator).click();
    }

    public void clickPublishButtonTwice() {
        logger.info("Verify that page is published: publish buttons clicked twice");
        findVisibleElement(publishButtonLocator).click();

        WebDriverWait localWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        localWait.until(ExpectedConditions.elementToBeClickable(secondPublishButtonLocator));

        getDriver().findElement(secondPublishButtonLocator).click();
    }

    public void returnBackToPagesPage() {
        logger.debug("Checking that W button is clicked correctly and user returned to Pages page");
        findVisibleElement(wButtonLocator).click();
        WebDriverWait localWait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        localWait.until(ExpectedConditions.elementToBeClickable(addNewPageButtonLocator));
    }

    public void titleIsDisplayedInTheTable(String expectedTitle) {
        logger.debug("Checking that newly added title is present in the table");
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

    public void clickOnTheDraftPageItemInTheTable(String expectedTitle) {
        logger.debug("Verifying that Draft page is found in the table and test clicks on it");
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

    public void clickCheckboxNearCreatedPage(String expectedTitle) {
        logger.debug("Click on the checkbox near newly created page");
        By dynamicCheckBoxLocator = getCheckboxNearCreatedPageLocator(expectedTitle);
        WebElement checkboxIsFound = findVisibleElement(dynamicCheckBoxLocator);
        checkboxIsFound.click();
    }

    public void selectMoveToTrashtFromDropdown() {
        logger.debug("Correct option *move to trash* is selected in the dropdown");
        WebElement dropdownElement = findVisibleElement(bulkActionsDropdownLocator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(2);

    }

    public void clickOnApplyButton() {
        logger.info("clicking on Apply button after move to trash option was selected");
        WebElement applyButton = findVisibleElement(applyButtonLocator);
        applyButton.click();
    }

    public boolean checkThatPageIsDeleted(String expectedTitle) {
        logger.debug("Verify that page is finally deleted");
        try {
            WebElement page = findVisibleElement(getTitleInTableLocator(expectedTitle));
            return !page.isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }

    public void hoverOverPageTitleAndClickOnTrashOption(String expectedTitle) {
        logger.info("Hovering over page title '{}' and clicking 'Trash' button", expectedTitle);

        By titleLocator = getTitleInTableLocator(expectedTitle);
        WebElement pageTitle = findVisibleElement(titleLocator);
        Actions actions = new Actions(getDriver());
        actions.moveToElement(pageTitle).perform();
        By trashButtonLocator = getTrashButtonInRowLocator(expectedTitle);
        WebDriverWait localWait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement trashButton = localWait.until(ExpectedConditions.elementToBeClickable(trashButtonLocator));
        trashButton.click();
    }
    private By getTrashButtonInRowLocator(String expectedTitle) {
        String xpathExpression = String.format(
                "//tr[.//a[@class='row-title' and contains(text(), '%s')]]//span[@class='trash']/a",
                expectedTitle
        );
        return By.xpath(xpathExpression);
    }

    public void clickBrowserBack() {
        logger.info("Clicking browser back button");
        getDriver().navigate().back();
    }
}

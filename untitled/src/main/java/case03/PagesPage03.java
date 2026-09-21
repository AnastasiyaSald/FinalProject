package case03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PagesPage03 extends BasePage03 {

    public PagesPage03 () {
        super();
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

    public boolean pagesPageIsOpened() {
        wait.until(ExpectedConditions.visibilityOf(chooseAPaternModalWindow));
        wait.until(ExpectedConditions.visibilityOf(lastTemplateIsUploaded));
        return chooseAPaternModalWindow.isDisplayed() && lastTemplateIsUploaded.isDisplayed();
    }

    @FindBy(xpath = "//div[@class='editor-start-page-options__modal-content']//div[@class='components-flex components-h-stack block-editor-patterns__pattern-details fdca-dd-a-bdd-ccd-13b06dz e19lxcc00']//div[@class='block-editor-block-patterns-list__item-title' and contains(text(), 'Portfolio home image gallery')]")
    WebElement selectedPattern;

    public void clickOnSelectedPattern(){
        selectedPattern.click();
    }


}

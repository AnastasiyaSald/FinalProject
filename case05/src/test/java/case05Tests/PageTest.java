package case05Tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.PagesPage;

@Listeners(ListenerTest.class)
public class PageTest extends BaseTest {

        @Test(description = "Page Draft is created")
        public void testCreateNewWordPressPageInDraft() {

            PagesPage pagesPage = new PagesPage();
            pagesPage.clickOnAddNewPageButton();
            Assert.assertTrue(pagesPage.chooseAPatternModalIsOpened(),"Модальное окно выбора шаблона не открылось!");
            pagesPage.clickOnSelectedPattern();


            String pageTitle = "Nastya Title Page";
            pagesPage.enterTextInTheTitleField(pageTitle);

            String articleText = "Это текст тестовой статьи, созданной автоматическим скриптом.";
            pagesPage.enterTextInTheArticleField(articleText);

            pagesPage.clickSaveDraftButton();
            pagesPage.returnBackToPagesPage();
            pagesPage.titleIsDisplayedInTheTable(pageTitle);
        }

        @Test(dependsOnMethods = "testCreateNewWordPressPageInDraft")
        public void publishDraftPage () {
            PagesPage pagesPage = new PagesPage();
            String pageTitle = "Nastya Title Page";
            pagesPage.clickOnTheDraftPageItemInTheTable(pageTitle);
            pagesPage.clickPublishButtonTwice();
            pagesPage.returnBackToPagesPage();
        }

//        @Test(dependsOnMethods = "publishDraftPage")
//        public void deleteThePage() {
//            PagesPage pagesPage = new PagesPage();
//            String pageTitle = "Nastya Title Page";
//            pagesPage.clickCheckboxNearCreatedPage(pageTitle);
//            pagesPage.selectMoveToTrashtFromDropdown();
//            pagesPage.clickOnApplyButton();
//            Assert.assertTrue(pagesPage.checkThatPageIsDeleted(pageTitle));
//        }

    @Test(dependsOnMethods = "publishDraftPage")
    public void deleteThePagemethod2() {
        PagesPage pagesPage = new PagesPage();
        String pageTitle = "Nastya Title Page";
        pagesPage.hoverOverPageTitleAndClickOnTrashOption(pageTitle);
        pagesPage.clickBrowserBack();
        Assert.assertTrue(pagesPage.pagesPageIsDisplayed());

    }

}


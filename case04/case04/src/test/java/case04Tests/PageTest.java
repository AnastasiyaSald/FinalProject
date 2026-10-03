package case04Tests;

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
        public void publishDraftPage (){
            PagesPage pagesPage = new PagesPage();
            pagesPage.clickOnTheDraftPageItemInTheTable("Nastya Title Page");

        }

}


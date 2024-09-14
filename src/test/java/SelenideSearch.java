import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideSearch {
    @BeforeAll
    static void beforeAll() {/* Всегда пишется со static. Вызывается один перед всеми тестами в этом тестовом классе*/
        Configuration.browserSize = "1920x1080";        /* Задаем разрешение браузера */
        Configuration.pageLoadStrategy = "eager";       /* Не ждем, когда загрузится полностью страница, чтобы долго не ждать*/
        Configuration.baseUrl = "https://github.com/";  /* Открывает основную страницу сайта */
        //Configuration.holdBrowserOpen = true;         /* Не дает закрыть тесту браузер. Нужно только для отладки */
    }

    @Test
    void fillFormTest() {
        // открыть главную страницу selenide/selenide в github
        open("/selenide/selenide");
        // перейти в раздел Wiki проекта
        $("#wiki-tab").click();
        // убедиться, что в списке страниц (Pages) есть страница SoftAssertions
        // открыть страницу SoftAssertions
        $(".markdown-body").$("[href='/selenide/selenide/wiki/SoftAssertions']").click();

        // проверить, что внутри есть пример кода для JUnit5
        $("#wiki-body").shouldHave(text("3. Using JUnit5 extend test class: "
                + """
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                @Test
                 void test() {
                   Configuration.assertionMode = SOFT;
                   open("page.html");
                    $("#first").should(visible).click(); 
                    $("#second").should(visible).click(); 
                    }
                }"""));
    }
}


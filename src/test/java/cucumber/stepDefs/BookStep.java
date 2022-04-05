package cucumber.stepDefs;

import cucumber.action.BookAction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.cucumber.java8.En;

public class BookStep implements En{
    BookAction bookAction = new BookAction();

    @Given("a book exists with an isbn of {word}")
    public void aBookExistsWithAnIsbnOf(String isbn) {
        bookAction.setRequest(isbn);
    }

    @When("a user retrieves the book by isbn")
    public void aUserRetrievesTheBookByIsbn() {
        bookAction.getResponse();
    }

    @Then("the status code is {int}")
    public void theStatusCodeIs(int statusCode) {
        assertThat("Verify Status code for Book Api ", bookAction.getStatusCode(), equalTo(statusCode));
    }

    @And("response includes the following")
    public void response_equals(Map<String,String> responseFields) {
        bookAction.response_equals(responseFields);
    }

    @And("response includes the following in any order")
    public void response_contains_in_any_order(Map<String,String> responseFields){
        bookAction.response_contains_in_any_order(responseFields);
    }

}

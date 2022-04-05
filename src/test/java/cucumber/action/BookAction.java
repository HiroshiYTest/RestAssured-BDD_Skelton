package cucumber.action;

import cucumber.utils.ConfigLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class BookAction {
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;
    private RequestSpecification requestSpec;
    private String ENDPOINT_GET_BOOK_BY_ISBN = ConfigLoader.getInstance().getBaseUrl();

    private void setPrerequisite(){
        requestSpec = new RequestSpecBuilder().
                setBaseUri(ENDPOINT_GET_BOOK_BY_ISBN).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();
    }

    public void setRequest(String isbn){
        setPrerequisite();
//        ISBN myISBN = new ISBN("q", isbn);
//        request = given(requestSpec);
//        request = given(requestSpec).param("q", "isbn:" + isbn);

        request = given(requestSpec).param("q", "isbn:" + isbn);
    }

    public void getResponse(){
        response = request.when().get(ENDPOINT_GET_BOOK_BY_ISBN);
        json = response.then();
    }

    public int getStatusCode(){
        return response.then().extract().statusCode();
    }

    public void response_equals(Map<String,String> responseFields) {
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if(StringUtils.isNumeric(field.getValue())){
                json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            }
            else{
                json.body(field.getKey(), equalTo(field.getValue()));
            }
        }
    }

    public void response_contains_in_any_order(Map<String,String> responseFields){
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if(StringUtils.isNumeric(field.getValue())){
                json.body(field.getKey(), containsInAnyOrder(Integer.parseInt(field.getValue())));
            }
            else{
                json.body(field.getKey(), containsInAnyOrder(field.getValue()));
            }
        }
    }
}

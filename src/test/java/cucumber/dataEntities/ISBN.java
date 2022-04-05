package cucumber.dataEntities;

public class ISBN {
    private String param;
    private String isbnCode;

    public ISBN(String param, String isbnCode){
        this.param = param;
        this.isbnCode = isbnCode;
    }

    public String getParam(){ return param; }
    public void setParam(String param) { this.param = param; }
    public String getIsbnCode(){ return isbnCode; }
    public void setIsbnCode(String isbnCode) { this.isbnCode = isbnCode; }
}

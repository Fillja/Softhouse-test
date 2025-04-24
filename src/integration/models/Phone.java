package src.integration.models;

// Assuming that both phone-values will be given, or else I would have to validate the numbers landline and/or if it is a mobile number, internationally.
public class Phone {
    public String mobile;

    public String landline;

    public Phone(String mobile, String landline){
        this.mobile = mobile;
        this.landline = landline;
    }
}

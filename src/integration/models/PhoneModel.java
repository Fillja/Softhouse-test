package src.integration.models;

// Assuming that both phone-values will be given, or else I would have to validate the numbers landline and/or if it is a mobile number
public class PhoneModel {
    public String mobile;

    public String landline;

    public PhoneModel(String mobile, String landline){
        this.mobile = mobile;
        this.landline = landline;
    }
}

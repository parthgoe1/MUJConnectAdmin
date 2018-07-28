package tech.sadana.mujconnectadmin;

public class Details {
    String firstName, lastName, email, regNum, year, callNum, whatsNum, paid;

    public  Details(String firstName, String lastName, String email, String regNum, String year, String callNum, String whatsNum){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.regNum = regNum;
        this.year = year;
        this.callNum = callNum;
        this.whatsNum = whatsNum;
        this.paid = "false";

    }

    public Details(){}

    public String getCallNum() {
        return callNum;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRegNum() {
        return regNum;
    }

    public String getWhatsNum() {
        return whatsNum;
    }

    public String getYear() {
        return year;
    }

    public String getPaid() {
        return paid;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public void setCallNum(String callNum) {
        this.callNum = callNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public void setWhatsNum(String whatsNum) {
        this.whatsNum = whatsNum;
    }

    public void setYear(String year) {
        this.year = year;
    }
}


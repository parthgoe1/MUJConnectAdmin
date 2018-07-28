package tech.sadana.mujconnectadmin;

public class Details {
    String Name, regNum;

    Details(String Name, String regNum){
        this.Name = Name;
        this.regNum = regNum;
    }

    public String getName() {
        return Name;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }
}

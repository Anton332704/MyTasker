package iipo.models;

/**
 * Created by user on 05.06.2016.
 */
public class UserModel {

    private String name;
    private String secName;
    private String icon;

    public UserModel(String name, String secName, String icon) {
        this.name = name;
        this.secName = secName;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

package droiidpelaez.westernproject.Roles.Commands;

public class Role {
    private final String role, display, color;


    public Role(String role, String display, String color) {
        this.role = role;
        this.display = display;
        this.color = color;
    }
    public String getRole(){
        return this.role;
    }
    public String getDisplay(){
        return this.display;
    }
    public String getColor(){
        return this.color;
    }
}

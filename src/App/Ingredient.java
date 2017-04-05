package App;

/**
 * Created by jlh94 on 08/03/2017.
 */
public class Ingredient {


    // Fields
    private String name;
    private String category;

    // Constructors
    public Ingredient() {

    }
    public Ingredient(String _name, String _category) {
        this.name = _name;
        this.category = _category;
    }

    // Methods


    // Getter / Setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}

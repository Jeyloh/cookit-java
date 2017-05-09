package Model;

/**
 * Created by jlh94 on 24/04/2017.
 */
public class Ingredient {

    public int id;
    public String title;
    public String category;
    public String description;

    public Ingredient() {};

    public Ingredient(int id, String title, String category, String description) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(
                "Ingredient[id=%s, title='%s', category='%s', description='%s']",
                id, title, category, description);
    }
}

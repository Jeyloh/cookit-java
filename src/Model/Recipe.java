package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


/**
 * Created by jlh94 on 24/04/2017.
 */
public class Recipe {


    private int id;
    private String name;
    //private ObservableList<Ingredient> ingredientList;
    private ArrayList<Ingredient> ingredientList;
    private String description;


    public Recipe() {};

    /**
     *
     * @param _id
     * @param _name
     * @param _ingredientList
     * @param _description
     */
    public Recipe(int _id, String _name, ArrayList<Ingredient> _ingredientList, String _description) {
        this.id = _id;
        this.name = _name;
        //this.ingredientList = FXCollections.observableList(_ingredientList);
        this.ingredientList = _ingredientList;
        this.description = _description;

    }


    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public ObservableList<Ingredient> getObservableList() {
        ObservableList<Ingredient> observableList = FXCollections.observableList(ingredientList);

        return observableList;
    }

    public void setIngredientList(ArrayList<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
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
                "Recipe[id=%s, name='%s', ingredientList='%s', description='%s']",
                id, name, ingredientList, description);
    }
}

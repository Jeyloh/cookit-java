package App;


import java.util.ArrayList;


/**
 * Created by jlh94 on 08/03/2017.
 */
public class Recipes {

    private String name;
    private ArrayList<Ingredient> ingredientList;
    private Ingredient ingredient;


    public Recipes(String _name, ArrayList<Ingredient> _ingredientList) {
        this.name = _name;
        this.ingredientList = _ingredientList;
        ingredientList = new ArrayList<>();
        ingredient = new Ingredient("Bread", "Bakery");
        ingredientList.add(ingredient);
        ingredient = new Ingredient("Apple", "Fruit");
        ingredientList.add(ingredient);
        ingredient = new Ingredient("Orange", "Fruit");
        ingredientList.add(ingredient);



        createRecipe("Sample Recipe", ingredientList);
    }

    public void createRecipe(String _name, ArrayList<Ingredient> _ingredientList) {
        this.name = _name;
        this.ingredientList = _ingredientList;
    }

    public void addIngredientToRecipe(ArrayList<Ingredient> targetRecipe, Ingredient ingredient) {
        targetRecipe.add(new Ingredient(ingredient.getName(), ingredient.getCategory()));

    }
}

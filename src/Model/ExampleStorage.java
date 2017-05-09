package Model;

import java.util.ArrayList;

/**
 * Created by jlh94 on 24/04/2017.
 */
public class ExampleStorage {

    private Recipe recipe;
    private Ingredient ingredient;

    private ArrayList<Recipe> recipeList;
    private ArrayList<Ingredient> recipesIngredients;
    private ArrayList<Ingredient> allIngredients;
    private CategoryEnum ingredientCategories;

    private Ingredient failed = new Ingredient(
            99,
            "Failed",
            "Failed",
            "Failed"
    );

    /**
     *
     */
    public ExampleStorage() {
        recipeList = new ArrayList<>();
        recipesIngredients = new ArrayList<>();
        allIngredients = new ArrayList<>();

        createIngredients();
        setupExampleRecipes();
        // Just a random list
        int a[] = {33, 3, 4, 5};


        for (Recipe rec : recipeList) {
            System.out.println(rec.getName());
            for (Ingredient ing : rec.getIngredientList()) {
                System.out.println(ing.getTitle());
            }
            System.out.println("_____");
        }

    }

    /**
     * @return
     */
    private ArrayList<Ingredient> addIngredientToRecipeList() {
        ArrayList<Ingredient> ingredientList;
        if (recipe.getIngredientList() != null) {
            ingredientList = recipe.getIngredientList();

            for (Ingredient ing : allIngredients) {
                if (allIngredients.contains(ing)) {
                    ingredientList.add(ing);
                    return ingredientList;
                } else {
                    System.out.println("Sorry, doesn't exist. Do you want to add it?");
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * @param ingredientsToAdd
     * @return
     */
    private ArrayList<Ingredient> addIngredientToRecipe(ArrayList<Ingredient> ingredientsToAdd) {

        //ObservableList<Ingredient> observableIngredientList = FXCollections.observableList(ingList);
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.addAll(ingredientsToAdd);
        return ingredientList;
    }

    /**
     * Generate 2 default Recipes
     */
    private void setupExampleRecipes() {
        Recipe recipe1 = new Recipe(
                1,
                "Honey Glazed Ham",
                addIngredientToRecipe(allIngredients), //addIngredientToRecipeList(allIngredients.get(0)),
                "Just like yo mama likes it!"
        );
        Recipe recipe2 = new Recipe(
                2,
                "Cereal",
                addIngredientToRecipe(allIngredients), //addIngredientToRecipeList(allIngredients.get(0)),
                "This might not be a good idea..."
        );

        recipeList.add(recipe1);
        recipeList.add(recipe2);
        //addIngredientToRecipe(allIngredients);
    }


    /**
     *
     */
    private void createIngredients() {
        Ingredient apple = new Ingredient(
                1, "Apple", "FRUIT", "Green");
        Ingredient ham = new Ingredient(
                2, "Ham", "MEAT", "With salt, pepper. Uncooked");
        Ingredient milk = new Ingredient(
                3, "Milk", "DAIRY", "Whole");
        Ingredient breadcrumbs = new Ingredient(
                4, "Breadcrumbs", "PASTRY", "Cut small");
        Ingredient honey = new Ingredient(
                5, "Honey", "SWEET", "Ecological, pure.");

        allIngredients.add(apple);
        allIngredients.add(ham);
        allIngredients.add(milk);
        allIngredients.add(breadcrumbs);
        allIngredients.add(honey);

    }

    /**
     * @return
     */
    public ArrayList<Recipe> getRecipeList() {
        return recipeList;
    }

    /**
     * @param recipeList
     */
    public void setRecipeList(ArrayList<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    /**
     * @return
     */
    public ArrayList<Ingredient> getAllIngredients() {
        return allIngredients;
    }

    /**
     * @param allIngredients
     */
    public void setAllIngredients(ArrayList<Ingredient> allIngredients) {
        this.allIngredients = allIngredients;
    }

}

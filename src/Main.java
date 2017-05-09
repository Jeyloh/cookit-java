import Model.CategoryEnum;
import Model.ExampleStorage;
import Model.Ingredient;
import Model.Recipe;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * Created by jlh94 on 24/04/2017.
 */

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Recipe currentRecipe;
    private ExampleStorage ex;
    private ArrayList<Ingredient> ingredientInputFieldList = new ArrayList<>();
    private ListView creationList;
    private GridPane grid = new GridPane();

    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        ex = new ExampleStorage();
        currentRecipe = ex.getRecipeList().get(0);

        BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        VBox vbox = addVBox();

        border.setTop(hbox);
        border.setLeft(vbox);

        border.setCenter(addGridPane());
        //border.setRight();

        BorderPane panel = new BorderPane();
        panel.setCenter(border);

        Scene scene = new Scene(panel, 1200, 800);
        primaryStage.setTitle("Cook IT!");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    /**
     *
     * @return
     */
    public VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Your Recipes");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        ListView recipeListView = new ListView();

        for (Recipe recipe : ex.getRecipeList()) {
            recipeListView.getItems().add(recipe);
        }

        recipeListView.setOnMouseClicked((MouseEvent event) -> {
            currentRecipe = (Recipe) recipeListView.getSelectionModel().getSelectedItem();
            System.out.println(currentRecipe);
            grid.getChildren().clear();
            addGridPane();
        });

        vbox.getChildren().add(title);
        vbox.getChildren().add(recipeListView);

        return vbox;
    }

    /**
     *
     * @return
     */
    public HBox addHBox() {
        creationList = new ListView();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #001a62;");

        // Left label in column 1 (bottom), row 3
        Label label1 = new Label("New Ingredient:");
        label1.setTextFill(Color.web("#fff"));
        TextField textField1 = new TextField();
        Button addIngredientBtn = new Button("Add Ingredient");

        // Add ingredient from Inputs
        Ingredient addedIngredient = new Ingredient(
                0,
                textField1.getText(),
                (String) createCategorybox().getValue(),
                "desc here..."
        );

        addIngredientBtn.setOnMouseClicked(event ->
                addCurrentIngredient(addedIngredient, textField1)
        );
        hbox.getChildren().addAll(label1, textField1, createCategorybox(), addIngredientBtn);

        return hbox;
    }

    /**
     *
     * @param grid
     * @param currentRecipe
     */
    private void renderCurrentRecipe(GridPane grid, Recipe currentRecipe) {
        // Targeted recipe in column 3, row 3
        Text recipeTitle = new Text("Index " + currentRecipe.getId() + ": " + currentRecipe.getName());
        Text recipeDescription = new Text(currentRecipe.getDescription());

        ListView<String> recipeList = new ListView<>();
        for (Ingredient ing : currentRecipe.getIngredientList()) {
            recipeList.getItems().add(ing.getTitle());
        }
        grid.add(recipeTitle, 4, 1);
        grid.add(recipeDescription, 4, 2);
        grid.add(recipeList, 4, 3);

    }


    /**
     *
     * @return
     */
    public GridPane addGridPane() {
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 0, 20));

        // Category in column 2, row 1
        Text category = new Text("Add Recipe:");
        category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(category, 1, 0);

        // Title in column 3, row 1
        Text chartTitle = new Text("Current Recipe:");
        chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(chartTitle, 4, 0);

        renderCurrentRecipe(grid, currentRecipe);

        // Subtitle in columns 2-3, row 2
        Text chartSubtitle = new Text("Give your recipe a name and add ingredients to it.\n");
        grid.add(chartSubtitle, 1, 1, 2, 1);

        //        // House icon in column 1, rows 1-2
        //        ImageView imageHouse = new ImageView(
        //                new Image(LayoutSample.class.getResourceAsStream("graphics/house.png")));
        //        grid.add(imageHouse, 0, 0, 1, 2);


        // Left label in column 1 (bottom), row 3
        Label recipeNameLabel = new Label("Recipe Name:");
        TextField recipeNameField = new TextField();
        grid.add(recipeNameLabel, 0, 2);
        grid.add(recipeNameField, 1, 2);
        recipeNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
        });

        Button addRecipeBtn = new Button("Add Recipe!");

        addRecipeBtn.setOnMouseClicked((MouseEvent event) -> {
            System.out.print("Test");
            addInputAsRecipe(ex.getRecipeList().size() + 1, recipeNameField.getText(), "desc...");

        });

        creationList.setStyle("height: 200px");
        addRecipeBtn.setStyle("width: 100%");
        grid.add(addRecipeBtn, 1, 4);
        grid.add(creationList, 1, 3);
        return grid;
    }

    /**
     *
     * @param ing
     * @param title
     */
    private void addCurrentIngredient(Ingredient ing, TextField title) {
        ingredientInputFieldList.add(ing);
        creationList.getItems().add(title.getText());
        ingredientInputFieldList.clear();
        title.clear();
    }

    /**
     *
     * @return
     */
    private ChoiceBox createCategorybox() {
        ChoiceBox categoryBox = new ChoiceBox(FXCollections.observableArrayList(
                Arrays.asList(CategoryEnum.values())));
        return categoryBox;
    }

    /**
     *
     * @param id
     * @param name
     * @param desc
     */
    private void addInputAsRecipe(int id, String name, String desc) {
        System.out.println("Inside addInputAsRecipe");
        ex.getRecipeList().add(new Recipe(id, name, ingredientInputFieldList, desc));
        ex.getAllIngredients().addAll(ingredientInputFieldList);
        grid.getChildren().clear();
        addGridPane();
    }
}

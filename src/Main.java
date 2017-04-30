import Database.ExampleStorage;
import Database.Ingredient;
import Database.Recipe;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by jlh94 on 24/04/2017.
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private Recipe currentRecipe;
    private ExampleStorage ex;
    private ObservableList<Ingredient> ingredientInputFieldList;
    private ArrayList<Ingredient> currentRecipeIngredientsInput = new ArrayList<>();

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



    public VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Your Recipes");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        ListView recipeListView = new ListView();

        for (Recipe recipe : ex.getRecipeList()) {
            recipeListView.getItems().add(recipe.getName());
        }

        recipeListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                currentRecipe = (Recipe)recipeListView.getSelectionModel().getSelectedItem();
            }
        });

        vbox.getChildren().add(title);
        vbox.getChildren().add(recipeListView);

        return vbox;
    }

    public HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Button buttonCurrent = new Button("Current");
        buttonCurrent.setPrefSize(100, 20);

        Button buttonProjected = new Button("Projected");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);

        return hbox;
    }

    public GridPane addGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 0, 20));

        // Button for adding another Ingredient input section
        Button addMoreIngredientFieldsBtn = new Button ("+");
        addMoreIngredientFieldsBtn.setOnAction(actionEven -> {
            System.out.println("User wants to add more ingredients, creating new field: ");
            Label addedLabel = new Label("Ingredient Extra");
            TextField addedTextField = new TextField ();
            TextField addedCategoryField = new TextField ();
            grid.add(addedLabel, 0, 6);
            grid.add(addedTextField, 1, 6);
            grid.add(addedCategoryField, 2, 6);

        });
        grid.add(addMoreIngredientFieldsBtn, 0, 0);

        // Category in column 2, row 1
        Text category = new Text("Add Recipe:");
        category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(category, 1, 0);

        // Title in column 3, row 1
        Text chartTitle = new Text("Current Recipe:");
        chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(chartTitle, 4, 0);

        // Targeted recipe in column 3, row 3
        Text recipeTitle = new Text("Index: " + currentRecipe.getId() + ". " + currentRecipe.getName());
        Text recipeDescription = new Text(currentRecipe.getDescription());

        ListView<Ingredient> recipeList = new ListView<Ingredient>(currentRecipe.getIngredientList());
        for (Ingredient ing : currentRecipe.getIngredientList()) {

        }


        grid.add(recipeTitle, 4, 2);
        grid.add(recipeDescription, 4, 3);
        grid.add(recipeList, 4, 4);

        // Make an arraylist of ingredients in the MongoDB JSON
        for (int i = 0; i < 1; i++) {
            // TODO: This will currently only add one recipe as the names are not special ...
            Text recipeIngredient = new Text("ing");
            grid.add(recipeIngredient, 4, i);

        }

        // Subtitle in columns 2-3, row 2
        Text chartSubtitle = new Text("Give your recipe a name and add ingredients to it.\n");
        grid.add(chartSubtitle, 1, 1, 2, 1);

    //        // House icon in column 1, rows 1-2
    //        ImageView imageHouse = new ImageView(
    //                new Image(LayoutSample.class.getResourceAsStream("graphics/house.png")));
    //        grid.add(imageHouse, 0, 0, 1, 2);


        // Left label in column 1 (bottom), row 3
        Label recipeNameLabel = new Label("Recipe Name:");
        TextField recipeNameField = new TextField ();
        grid.add(recipeNameLabel, 0, 1);
        grid.add(recipeNameField, 1, 1);

        // Left label in column 1 (bottom), row 3
        Label label1 = new Label("Ingredient 1:");
        TextField textField1 = new TextField ();
        TextField categoryField1 = new TextField ();
        grid.add(label1, 0, 3);
        grid.add(textField1, 1, 3);
        grid.add(categoryField1, 2, 3);

        // Left label in column 1 (bottom), row 3
        Label label2 = new Label("Ingredient 1:");
        TextField textField2 = new TextField ();
        TextField categoryField2 = new TextField ();
        grid.add(label2, 0, 4);
        grid.add(textField2, 1, 4);
        grid.add(categoryField2, 2, 4);

        // Left label in column 1 (bottom), row 3
        Label label3 = new Label("Ingredient 2:");
        TextField textField3 = new TextField ();
        TextField categoryField3 = new TextField ();
        grid.add(label3, 0, 5);
        grid.add(textField3, 1, 5);
        grid.add(categoryField3, 2, 5);

        Button addRecipeBtn = new Button("Add Recipe!");


        // Create a new input list
        for (int i = 0; i < ingredientInputFieldList.size(); i++) {
            grid.add(new Label("Ingredient Name: "), i, 3+i);
            TextField ingredientNameTf = new TextField();
            grid.add(ingredientNameTf, i, 3+i);
            TextField categoryTf = new TextField();
            grid.add(categoryTf, i, 3+i);
            final int ID = i;

            currentRecipeIngredientsInput.add(new Ingredient(
                    ex.getAllIngredients().size() + ID,
                    ingredientNameTf.getText(),
                    categoryTf.getText(),
                    "desc here ...")
            );
            // Take the current ingredient inputs and add them (As ObservableList) to the currentRecipe
            currentRecipe.setIngredientList((ObservableList)currentRecipeIngredientsInput);
        }

        grid.add(addRecipeBtn, 1, 5);

    //        // Chart in columns 2-3, row 3
    //        ImageView imageChart = new ImageView(
    //                new Image(LayoutSample.class.getResourceAsStream("graphics/piechart.png")));
    //        grid.add(imageChart, 1, 2, 2, 1);

        // Right label in column 4 (top), row 3
        Text servicesPercent = new Text("Services\n20%");
        GridPane.setValignment(servicesPercent, VPos.TOP);
        grid.add(servicesPercent, 3, 2);


        return grid;
    }
}

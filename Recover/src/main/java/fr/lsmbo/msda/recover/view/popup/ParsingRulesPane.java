package fr.lsmbo.msda.recover.view.popup;

import fr.lsmbo.msda.recover.lists.ListOfSpectra;
import fr.lsmbo.msda.recover.lists.ParsingRules;
import fr.lsmbo.msda.recover.model.ParsingRule;
import fr.lsmbo.msda.recover.model.Spectrum;
import fr.lsmbo.msda.recover.util.IconResource;
import fr.lsmbo.msda.recover.util.WindowSize;
import fr.lsmbo.msda.recover.util.IconResource.ICON;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ParsingRulesPane extends Accordion {

	private ObservableList<Spectrum> titles = FXCollections.observableArrayList();
	private ComboBox<String> parsingRulesCBox = null;
	private TextField selectedPRuleTf = null;
	private TableView<Spectrum> parsingRuletable = null;

	public ParsingRulesPane() {
		Label pRulesLabel = new Label("Select a parsing rule");
		pRulesLabel.setPrefWidth(130);
		parsingRulesCBox = new ComboBox();
//		parsingRulesCBox.getItems().addAll("Data-Analysis", "Mascot-Distiller", "Mascot-dll", "MsConvert", "Proline");
//		parsingRulesCBox.getSelectionModel().selectFirst();
		for (ParsingRule pr : ParsingRules.get()) {
			parsingRulesCBox.getItems().add(pr.getName());
		}
		parsingRulesCBox.setPrefWidth(160);
		HBox parsingRulesHbox = new HBox(15);
		parsingRulesHbox.getChildren().addAll(pRulesLabel, parsingRulesCBox);

		Label selectedPRuleLabel = new Label("Selected parsing rule");
		selectedPRuleLabel.setPrefWidth(130);
		selectedPRuleTf = new TextField();
		selectedPRuleTf.setPrefWidth(170);

		HBox selectedPRHbox = new HBox(15);
		selectedPRHbox.getChildren().addAll(selectedPRuleLabel, selectedPRuleTf);
		Button testButton = new Button("  Test");
		testButton.setGraphic(new ImageView(IconResource.getImage(ICON.CHECK)));
		testButton.setPrefWidth(130);
		HBox insertDataHbox = new HBox(50);
		insertDataHbox.getChildren().addAll(parsingRulesHbox, selectedPRHbox, testButton);
		insertDataHbox.setAlignment(Pos.CENTER);

		//
		// fill table
		titles.clear();
		Integer nb = ListOfSpectra.getFirstSpectra().getSpectraAsObservable().size();
		if (nb > 5)
			nb = 5;
		for (int i = 0; i < nb; i++) {
			titles.add(ListOfSpectra.getFirstSpectra().getSpectraAsObservable().get(i));
		}
		// table
		parsingRuletable = new TableView<Spectrum>(titles);
		TableColumn<Spectrum, String> titleCol = new TableColumn<>("Title");
		TableColumn<Spectrum, Float> newRTCol = new TableColumn<>("Retention Time");
		titleCol.setCellValueFactory(new PropertyValueFactory<Spectrum, String>("title"));
		newRTCol.setCellValueFactory(new PropertyValueFactory<Spectrum, Float>("retentionTime"));
		parsingRuletable.getColumns().setAll(titleCol, newRTCol);
		parsingRuletable.setColumnResizePolicy(parsingRuletable.CONSTRAINED_RESIZE_POLICY);
		ParsingRule pr = ParsingRules.getCurrentParsingRule();
		if (pr != null) {
			parsingRulesCBox.setValue(pr.getName());
			selectedPRuleTf.setText(pr.getRegex());
		}
		// filter panel
		VBox filterPane1 = new VBox(50);
		filterPane1.getChildren().addAll(insertDataHbox, parsingRuletable);
		filterPane1.setPrefSize(WindowSize.popupPrefWidth, WindowSize.popupPrefHeight);
		TitledPane filetr1 = new TitledPane("Edit Parsing Rules", filterPane1);
		this.getPanes().addAll(filetr1);
		this.autosize();
		this.setExpandedPane(filetr1);
		//
		
	}
}

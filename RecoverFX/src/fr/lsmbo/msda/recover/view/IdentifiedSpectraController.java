package fr.lsmbo.msda.recover.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import fr.lsmbo.msda.recover.io.IdentifiedSpectraFromExcel;
import fr.lsmbo.msda.recover.lists.IdentifiedSpectra;
import fr.lsmbo.msda.recover.lists.ListOfSpectra;
import fr.lsmbo.msda.recover.model.ConvertorArrayToArrayList;
import fr.lsmbo.msda.recover.model.StatusBar;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class IdentifiedSpectraController {

	private Stage identifiedSpectraStage;
	private IdentifiedSpectra identifiedSpectra = new IdentifiedSpectra();
	private IdentifiedSpectraFromExcel identifiedSpectraFromExcel;

	private static Boolean excelFileImported = false;

	@FXML
	private TextArea titles;
	@FXML
	private Button btnApply;
	@FXML
	private Button btnImportTitlesFromExcel;
	@FXML
	private Button btnDeleteImport;
	@FXML
	private Label infoExcelFile;

	@FXML
	private void initialize() {

		if (excelFileImported) {
			btnDeleteImport.setVisible(true);
			infoExcelFile.setVisible(true);
			infoExcelFile.setText(IdentifiedSpectraFromExcel.getTitle());
		}

	}

	public void setDialogStage(Stage _identifiedSpectraStage) {
		this.identifiedSpectraStage = _identifiedSpectraStage;
	}

	@FXML
	private void handleClickBtnApply() {
		//Reset Spectra identified
		if (ListOfSpectra.getFirstSpectra().getNbIdentified() != 0) {
			ListOfSpectra.getFirstSpectra().resetIdentified();
		}

		//Recover text in the text box and convert him in arraylist
		String[] arrayTitles = titles.getText().split("\n");
		ArrayList<String> arrayListTitles = ConvertorArrayToArrayList.arrayToArrayListString(arrayTitles);

		
		if (!excelFileImported) {
			identifiedSpectra.setArrayTitles(arrayListTitles);
		} else {
			identifiedSpectra.addAllTitles(arrayListTitles);
		}

		// TODO move this loop in other class
		for (String t : identifiedSpectra.getArrayTitles()) {
			identifiedSpectra.setIdentified(t);
		}

		identifiedSpectraStage.close();
	}

	@FXML
	private void importExcelFile() {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Import your excel file");
		filechooser.getExtensionFilters().addAll(new ExtensionFilter("File XLS", "*.xlsx"));
		File excelFile = filechooser.showOpenDialog(this.identifiedSpectraStage);

		
		if (excelFile != null) {
			identifiedSpectraFromExcel = new IdentifiedSpectraFromExcel();
			//Use the same object identifiedSpectra to recover title
			identifiedSpectraFromExcel.setIdentifiedSpectra(identifiedSpectra);
			identifiedSpectraFromExcel.load(excelFile);

			if (identifiedSpectraFromExcel.getTitles().size() != 0) {
				excelFileImported = true;
				btnDeleteImport.setVisible(true);
				infoExcelFile.setVisible(true);
				infoExcelFile.setText(IdentifiedSpectraFromExcel.getTitle());
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("No titles found");
				alert.setContentText(
						"No titles imported from your excel file. Please select an other file or verify sheet or column selected");
				alert.showAndWait();
			}
		}
	}

	public IdentifiedSpectra getIdentifiedSpectra() {
		return identifiedSpectra;
	}

	@FXML
	private void handleClickDeleteImport() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Remove excel file");
		alert.setContentText("You are removing excel file. That will RESET ALL identified spectra."
				+ "\nAre you sure you want to do this ?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			btnDeleteImport.setVisible(false);
			infoExcelFile.setText(null);
			infoExcelFile.setVisible(false);
			identifiedSpectra.resetArrayTitles();
			excelFileImported = false;
			ListOfSpectra.getFirstSpectra().resetIdentified();
		}

	}
}

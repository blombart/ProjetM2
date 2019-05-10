
package fr.lsmbo.msda.recover.gui.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fr.lsmbo.msda.recover.gui.lists.IdentifiedSpectra;
import fr.lsmbo.msda.recover.gui.view.dialog.TitlesSelectorExcelDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Class to extract titles in excel file and put them in class
 * identifiedSpectra. Read an excel file, recover sheets, open a new window to
 * get back information about the sheet and the column to extract these titles
 * by iteration.
 * 
 * @author LOMBART.benjamin
 *
 */
public class IdentifiedSpectraFromExcel {
	private static final Logger logger = LogManager.getLogger(IdentifiedSpectraFromExcel.class);
	private static String title = "";

	private ObservableList<String> sheetList = FXCollections.observableArrayList();
	private int rowNumber = 0;
	private String column = "";
	private String currentSheetName = "";
	private ArrayList<String> titles = new ArrayList<>();

	private IdentifiedSpectra identifiedSpectra;
	private HashMap<String, Object> selectionProperties = new HashMap<String, Object>();

	public IdentifiedSpectraFromExcel() {
	}

	/**
	 * 
	 * @param file
	 *            Excel file which contains titles to make identification
	 */
	public void load(File file) {
		try {

			initialization();
			title = file.getName();
			FileInputStream fileExcel = new FileInputStream(new File(file.getAbsolutePath()));

			XSSFWorkbook workbook = new XSSFWorkbook(fileExcel);

			// recover number of sheet in the workbook and save all the
			// sheets(name) present in list.
			int nbSheet = workbook.getNumberOfSheets();
			for (int i = 0; i < nbSheet; i++) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				String sheetName = sheet.getSheetName();
				sheetList.add(sheetName);
			}

			TitlesSelectorExcelDialog.setSheets(sheetList);
			getTitlesSelection();

			// transform a string column ("A", "B" ...) in an index
			int columnIndex = CellReference.convertColStringToIndex(column);

			XSSFSheet currentSheet = workbook.getSheet(currentSheetName);

			Iterator<Row> rowIterator = currentSheet.iterator();

			// iterate through all row
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				// iterate through all cell for a row
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					// add value contains in the cell only for the cells from
					// specific row and in the good column
					if (row.getRowNum() >= rowNumber) {
						if (cell.getColumnIndex() == columnIndex) {
							titles.add(cell.getStringCellValue());
						}
					}
				}
			}

			// add titles in the object identified spectra, if no titles are
			// present in the object (list), just initialize it
			// or else add in the list.
			if (identifiedSpectra.getArrayTitles() != null) {
				identifiedSpectra.setArrayTitles(titles);
			} else {
				identifiedSpectra.addAllTitles(titles);
			}

			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Initialize value
	public void initialization() {

		if (sheetList != null) {
			sheetList.clear();
		}

		if (titles.size() != 0) {
			titles.clear();
		}

		title = "";
		rowNumber = 0;
		column = "";
		currentSheetName = "";
	}

	/** Return a title */
	public static String getTitle() {
		return title;
	}

	/** Return the title selection from an excel file */
	private void getTitlesSelection() {
		try {
			TitlesSelectorExcelDialog excelSelectorDialog = new TitlesSelectorExcelDialog();
			excelSelectorDialog.showAndWait().ifPresent(selectorProperties -> {
				selectionProperties = (HashMap<String, Object>) selectorProperties.clone();
			});
			logger.info("Titles selection properties from excel file: {}", selectionProperties);
			System.out.println("INFO - Titles selection properties from excel file: " + selectionProperties);
			rowNumber = (int) selectionProperties.get("rowNumber") - 1;
			column = (String) selectionProperties.get("column");
			currentSheetName = (String) selectionProperties.get("currentSheetName");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ObservableList<String> getListSheet() {
		return sheetList;
	}

	public void setIdentifiedSpectra(IdentifiedSpectra identifiedSpectra) {
		this.identifiedSpectra = identifiedSpectra;
	}

	public ArrayList<String> getTitles() {
		return titles;
	}

}

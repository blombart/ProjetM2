package fr.lsmbo.msda.recover.gui.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.google.jhsheets.filtered.operators.BooleanOperator;
import org.google.jhsheets.filtered.operators.NumberOperator;
import org.google.jhsheets.filtered.operators.StringOperator;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import fr.lsmbo.msda.recover.gui.filters.IdentifiedSpectraFilter;
import fr.lsmbo.msda.recover.gui.filters.IonReporterFilter;
import fr.lsmbo.msda.recover.gui.filters.LowIntensityThresholdFilter;
import fr.lsmbo.msda.recover.gui.lists.IonReporters;
import fr.lsmbo.msda.recover.gui.model.ComputationTypes;
import fr.lsmbo.msda.recover.gui.model.IonReporter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Parse and load filters parameters from JSON file.
 * 
 * @author Aromdhani
 * @author Burel
 *
 */
public class FilterReaderJson {
	private static final Logger logger = LogManager.getLogger(FilterReaderJson.class);

	/**
	 * Parse and load filters parameters from a JSON file.
	 * 
	 * @param file
	 *            File at format JSON which contains parameters of filters.
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public static HashMap<String, ObservableList<Object>> load(File file) throws JsonParseException, IOException {
		HashMap<String, ObservableList<Object>> filterListByNameMap = new HashMap<>();
		try {
			JsonFactory factory = new JsonFactory();
			@SuppressWarnings("deprecation")
			JsonParser parser = factory.createJsonParser(file);
			while (!parser.isClosed()) {
				/**
				 * Boolean operator
				 **/
				// Read Flag filter
				JsonToken token = parser.nextToken();
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Flag") {
					ObservableList<Object> filters = FXCollections.observableArrayList();
					BooleanOperator filter = null;
					while (!JsonToken.END_OBJECT.equals(token)) {
						token = parser.nextToken();
						if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "False") {
							token = parser.nextToken();
							filter = new BooleanOperator(BooleanOperator.Type.FALSE, parser.getValueAsBoolean());
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "True") {
							token = parser.nextToken();
							filter = new BooleanOperator(BooleanOperator.Type.TRUE, parser.getValueAsBoolean());
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "No Filter") {
							token = parser.nextToken();
							filter = new BooleanOperator(BooleanOperator.Type.NONE, null);
						}
					}
					filters.add((BooleanOperator) filter);
					filterListByNameMap.put("Flag", filters);
				}
				// Read Ion Reporter filter
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Ion Reporter") {
					ObservableList<Object> filters = FXCollections.observableArrayList();
					BooleanOperator filter = null;
					while (!JsonToken.END_OBJECT.equals(token)) {
						token = parser.nextToken();
						if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "False") {
							token = parser.nextToken();
							filter = new BooleanOperator(BooleanOperator.Type.FALSE, parser.getValueAsBoolean());
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "True") {
							token = parser.nextToken();
							filter = new BooleanOperator(BooleanOperator.Type.TRUE, parser.getValueAsBoolean());
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "No Filter") {
							token = parser.nextToken();
							filter = new BooleanOperator(BooleanOperator.Type.NONE, null);
						}
					}
					filters.add((BooleanOperator) filter);
					filterListByNameMap.put("Ion Reporter", filters);
				}
				// Read Wrong charge filter
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Wrong charge") {
					ObservableList<Object> filters = FXCollections.observableArrayList();
					BooleanOperator filter = null;
					while (!JsonToken.END_OBJECT.equals(token)) {
						token = parser.nextToken();
						if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "False") {
							token = parser.nextToken();
							filter = new BooleanOperator(BooleanOperator.Type.FALSE, parser.getValueAsBoolean());
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "True") {
							token = parser.nextToken();
							filter = new BooleanOperator(BooleanOperator.Type.TRUE, parser.getValueAsBoolean());
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "No Filter") {
							token = parser.nextToken();
							filter = new BooleanOperator(BooleanOperator.Type.NONE, null);
						}
					}
					filters.add((BooleanOperator) filter);
					filterListByNameMap.put("Wrong charge", filters);
				}
				// Read identified filter
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Identified") {
					ObservableList<Object> filters = FXCollections.observableArrayList();
					BooleanOperator filter = null;
					while (!JsonToken.END_OBJECT.equals(token)) {
						token = parser.nextToken();
						if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "False") {
							token = parser.nextToken();
							filter = new BooleanOperator(BooleanOperator.Type.FALSE, parser.getValueAsBoolean());
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "True") {
							token = parser.nextToken();
							filter = new BooleanOperator(BooleanOperator.Type.TRUE, parser.getValueAsBoolean());
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "No Filter") {
							token = parser.nextToken();
							filter = new BooleanOperator(BooleanOperator.Type.NONE, null);
						}
					}
					filters.add((BooleanOperator) filter);
					filterListByNameMap.put("Identified", filters);
				}
				/**
				 * String operator
				 */
				// Read Title filter
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Title") {
					ObservableList<Object> filters = FXCollections.observableArrayList();
					StringOperator filter = null;
					while (!JsonToken.END_OBJECT.equals(token)) {
						token = parser.nextToken();
						if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Equals") {
							token = parser.nextToken();
							filter = new StringOperator(StringOperator.Type.EQUALS, parser.getValueAsString());
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Not Equals") {
							token = parser.nextToken();
							filter = new StringOperator(StringOperator.Type.NOTEQUALS, parser.getValueAsString());
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Contains") {
							token = parser.nextToken();
							filter = new StringOperator(StringOperator.Type.CONTAINS, parser.getValueAsString());
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Starts With") {
							token = parser.nextToken();
							filter = new StringOperator(StringOperator.Type.STARTSWITH, parser.getValueAsString());
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Ends With") {
							token = parser.nextToken();
							filter = new StringOperator(StringOperator.Type.ENDSWITH, parser.getValueAsString());
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "No Filter") {
							token = parser.nextToken();
							filter = new StringOperator(StringOperator.Type.NONE, null);
						}
					}
					filters.add((StringOperator) filter);
					filterListByNameMap.put("Title", filters);
				}
				/**
				 * Number operator
				 */
				// Read Id filter
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Id") {
					ObservableList<Object> filters = FXCollections.observableArrayList();
					NumberOperator<Integer> filter = null;
					while (!JsonToken.END_OBJECT.equals(token)) {
						token = parser.nextToken();
						if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.EQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Not Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NOTEQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHAN,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHAN, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "No Filter") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NONE, null);
							filters.add((NumberOperator<Integer>) filter);
						}
					}

					filterListByNameMap.put("Id", filters);
				}
				// Read Mz filter
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Mz") {
					ObservableList<Object> filters = FXCollections.observableArrayList();
					NumberOperator<Integer> filter = null;
					while (!JsonToken.END_OBJECT.equals(token)) {
						token = parser.nextToken();
						if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.EQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Not Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NOTEQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHAN,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHAN, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "No Filter") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NONE, null);
							filters.add((NumberOperator<Integer>) filter);
						}
					}

					filterListByNameMap.put("Mz", filters);
				}
				// Read Intensity filter
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Intensity") {
					ObservableList<Object> filters = FXCollections.observableArrayList();
					NumberOperator<Integer> filter = null;
					while (!JsonToken.END_OBJECT.equals(token)) {
						token = parser.nextToken();
						if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.EQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Not Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NOTEQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHAN,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHAN, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "No Filter") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NONE, null);
							filters.add((NumberOperator<Integer>) filter);
						}
					}

					filterListByNameMap.put("Intensity", filters);
				}
				// Read Charge filter
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Charge") {
					ObservableList<Object> filters = FXCollections.observableArrayList();
					NumberOperator<Integer> filter = null;
					while (!JsonToken.END_OBJECT.equals(token)) {
						token = parser.nextToken();
						if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.EQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Not Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NOTEQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHAN,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHAN, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "No Filter") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NONE, null);
							filters.add((NumberOperator<Integer>) filter);
						}
					}

					filterListByNameMap.put("Charge", filters);
				}
				// Read Retention time filter
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Retention Time") {
					ObservableList<Object> filters = FXCollections.observableArrayList();
					NumberOperator<Integer> filter = null;
					while (!JsonToken.END_OBJECT.equals(token)) {
						token = parser.nextToken();
						if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.EQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Not Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NOTEQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHAN,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHAN, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "No Filter") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NONE, null);
							filters.add((NumberOperator<Integer>) filter);
						}
					}

					filterListByNameMap.put("Retention Time", filters);
				}
				// Read Fragment number filter
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Fragment number") {
					ObservableList<Object> filters = FXCollections.observableArrayList();
					NumberOperator<Integer> filter = null;
					while (!JsonToken.END_OBJECT.equals(token)) {
						token = parser.nextToken();
						if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.EQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Not Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NOTEQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHAN,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHAN, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "No Filter") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NONE, null);
							filters.add((NumberOperator<Integer>) filter);
						}
					}

					filterListByNameMap.put("Fragment number", filters);
				}
				// Read max fragment intensity filter
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Max fragment intensity") {
					ObservableList<Object> filters = FXCollections.observableArrayList();
					NumberOperator<Integer> filter = null;
					while (!JsonToken.END_OBJECT.equals(token)) {
						token = parser.nextToken();
						if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.EQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Not Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NOTEQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHAN,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHAN, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "No Filter") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NONE, null);
							filters.add((NumberOperator<Integer>) filter);
						}
					}

					filterListByNameMap.put("Max fragment intensity", filters);
				}
				// Read useful peaks number filter
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "UPN") {
					ObservableList<Object> filters = FXCollections.observableArrayList();
					NumberOperator<Integer> filter = null;
					while (!JsonToken.END_OBJECT.equals(token)) {
						token = parser.nextToken();
						if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.EQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Not Equals") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NOTEQUALS, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHAN,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Greater Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.GREATERTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHAN, parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "Equals/Less Than") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.LESSTHANEQUALS,
									parser.getValueAsInt());
							filters.add((NumberOperator<Integer>) filter);
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "No Filter") {
							token = parser.nextToken();
							filter = new NumberOperator<Integer>(NumberOperator.Type.NONE, null);
							filters.add((NumberOperator<Integer>) filter);
						}
					}

					filterListByNameMap.put("UPN", filters);
				}

				// Read low intensity threshold filter
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "LIT") {
					LowIntensityThresholdFilter filterLIT = new LowIntensityThresholdFilter();
					float emergence = 0;
					int minUPN = 0;
					int maxUPN = 0;
					ComputationTypes mode = null;
					// get values in the object filterLIT
					while (!JsonToken.END_OBJECT.equals(token)) {
						token = parser.nextToken();
						if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "emergence") {
							token = parser.nextToken();
							emergence = (float) parser.getValueAsDouble();
						} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "mode") {
							token = parser.nextToken();
							mode = ComputationTypes.valueOf(parser.getValueAsString());
						}
					}
					filterLIT.setParameters(emergence, minUPN, maxUPN, mode);
				}

				// Read is identified filter
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "filterIS") {
					IdentifiedSpectraFilter filterIS = new IdentifiedSpectraFilter();
					Boolean checkRecoverIdentified = null;
					Boolean checkRecoverNonIdentified = null;
					// get values in the object filterIS
					while (!JsonToken.END_OBJECT.equals(token)) {
						token = parser.nextToken();
						if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "checkRecoverIdentified") {
							token = parser.nextToken();
							checkRecoverIdentified = parser.getValueAsBoolean();
						} else if (JsonToken.FIELD_NAME.equals(token)
								&& parser.getCurrentName() == "checkRecoverNonIdentified") {
							token = parser.nextToken();
							checkRecoverNonIdentified = parser.getValueAsBoolean();
						}
					}
					filterIS.setParameters(checkRecoverIdentified, checkRecoverNonIdentified);

				}

				// Read ion reporter filter
				if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "IR") {
					IonReporterFilter filterIR = new IonReporterFilter();
					String name = "";
					float moz = 0;
					float tolerance = 0;
					// Just after the token FIELD_NAME is the token START_OBJECT
					// and after is again
					// a token FIELD_NAME.
					// need to go two step further to get the good token
					token = parser.nextToken();
					token = parser.nextToken();
					if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "ionReporter") {
						// loop until end of array. Format are always the same
						// for an ion reporter name,
						// moz, tolerance.
						while (!JsonToken.END_ARRAY.equals(token)) {
							token = parser.nextToken();

							if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "name") {
								token = parser.nextToken();
								name = parser.getValueAsString();
							} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "moz") {
								token = parser.nextToken();
								moz = (float) parser.getValueAsDouble();
							} else if (JsonToken.FIELD_NAME.equals(token) && parser.getCurrentName() == "tolerance") {
								token = parser.nextToken();
								tolerance = (float) parser.getValueAsDouble();
							}
							if (JsonToken.END_OBJECT.equals(token)) {
								IonReporters.addIonReporter(new IonReporter(name, moz, tolerance));
							}
						}
					}
				}
			}
			return filterListByNameMap;
		} catch (FileNotFoundException e) {
			logger.error("Error while trying to load filters parameters!", e);
			filterListByNameMap.clear();
			return filterListByNameMap;
		}

	}

}

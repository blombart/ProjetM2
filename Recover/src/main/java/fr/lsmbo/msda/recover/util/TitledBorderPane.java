package fr.lsmbo.msda.recover.util;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import fr.lsmbo.msda.recover.view.SpectrumChart;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author aromdhani
 *
 */
public class TitledBorderPane extends StackPane {
	public TitledBorderPane(String title, Image icon, Node node, String toolTip) {
		VBox mainPanel = new VBox(2);
		ImageView imageView = null;
		if (icon != null) {
			imageView = new ImageView(icon);
			mainPanel.getChildren().addAll(imageView, node);
		} else {
			mainPanel.getChildren().addAll(node);
		}
		this.setStyle(StyleUtils.TITLED_BORDER_PANE);
		this.setPadding(new Insets(5, 5, 5, 5));
		this.getChildren().addAll(mainPanel);
	}
}

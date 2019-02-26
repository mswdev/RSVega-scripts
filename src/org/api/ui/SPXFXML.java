package org.api.ui;

public enum SPXFXML {

    SPX_TEMPLATE("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "\n" +
            "<?import fxui.geometry.Insets?>\n" +
            "<?import fxui.scene.control.Button?>\n" +
            "<?import fxui.scene.control.Separator?>\n" +
            "<?import fxui.scene.image.ImageView?>\n" +
            "<?import fxui.scene.layout.HBox?>\n" +
            "<?import fxui.scene.layout.VBox?>\n" +
            "<?import fxui.scene.text.Font?>\n" +
            "\n" +
            "\n" +
            "<VBox fx:controller=\"org.script.testing.fxml_gui_test.TestFXGUIController\" maxHeight=\"-Infinity\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\" minWidth=\"-Infinity\" prefHeight=\"400.0\" prefWidth=\"300.0\" style=\"-fx-background-color: #1a1818;\" xmlns=\"http://fxui.com/fxui/9.0.1\" xmlns:fx=\"http://fxui.com/fxml/1\">\n" +
            "   <children>\n" +
            "      <HBox alignment=\"CENTER\">\n" +
            "         <children>\n" +
            "            <ImageView fx:id=\"logo\" fitHeight=\"62.0\" fitWidth=\"275.0\" pickOnBounds=\"true\" preserveRatio=\"true\" smooth=\"false\" />\n" +
            "         </children>\n" +
            "         <VBox.margin>\n" +
            "            <Insets top=\"10.0\" />\n" +
            "         </VBox.margin>\n" +
            "      </HBox>\n" +
            "      <Separator prefWidth=\"270.0\" style=\"-fx-border-radius: 5px;\">\n" +
            "         <VBox.margin>\n" +
            "            <Insets left=\"15.0\" right=\"15.0\" top=\"10.0\" />\n" +
            "         </VBox.margin>\n" +
            "      </Separator>\n" +
            "      <VBox alignment=\"CENTER\" spacing=\"15.0\" VBox.vgrow=\"ALWAYS\">\n" +
            "         <children>\n" +
            "            <HBox alignment=\"CENTER\" />\n" +
            "         </children>\n" +
            "         <VBox.margin>\n" +
            "            <Insets />\n" +
            "         </VBox.margin>\n" +
            "      </VBox>\n" +
            "      <VBox alignment=\"BOTTOM_CENTER\">\n" +
            "         <children>\n" +
            "            <HBox alignment=\"BOTTOM_CENTER\">\n" +
            "               <children>\n" +
            "                  <Button fx:id=\"start\" mnemonicParsing=\"false\" prefHeight=\"25.0\" prefWidth=\"100.0\" style=\"-fx-background-color: #a82219;\" text=\"START\" textFill=\"#fbf9fa\">\n" +
            "                     <font>\n" +
            "                        <Font name=\"System Bold\" size=\"12.0\" />\n" +
            "                     </font>\n" +
            "                  </Button>\n" +
            "               </children>\n" +
            "            </HBox>\n" +
            "         </children>\n" +
            "         <VBox.margin>\n" +
            "            <Insets bottom=\"15.0\" />\n" +
            "         </VBox.margin>\n" +
            "      </VBox>\n" +
            "   </children>\n" +
            "</VBox>\n"),
    SPX_AIO_WALKER("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "\n" +
            "<?import fxui.geometry.*?>\n" +
            "<?import fxui.scene.control.*?>\n" +
            "<?import fxui.scene.layout.*?>\n" +
            "<?import fxui.scene.text.*?>\n" +
            "<VBox xmlns:fx=\"http://fxui.com/fxml/1\" alignment=\"CENTER\" maxHeight=\"-Infinity\" maxWidth=\"-Infinity\" minHeight=\"-Infinity\"\n" +
            "      minWidth=\"-Infinity\" spacing=\"60.0\" style=\"-fx-background-color: #1a1818;\"\n" +
            "      xmlns=\"http://fxui.com/fxui/9\"\n" +
            "      fx:controller=\"spx_aio_walking.WalkingGUIController\">\n" +
            "    <children>\n" +
            "        <VBox alignment=\"TOP_CENTER\">\n" +
            "            <children>\n" +
            "                <HBox alignment=\"CENTER\" spacing=\"10.0\">\n" +
            "                    <children>\n" +
            "                        <Label fx:id=\"logo_first\" alignment=\"CENTER_RIGHT\" maxHeight=\"1.7976931348623157E308\"\n" +
            "                               maxWidth=\"1.7976931348623157E308\" minHeight=\"-Infinity\" minWidth=\"-Infinity\"\n" +
            "                               style=\"-fx-font-family: 'Fjalla One'; -fx-font-size: 35;\" text=\"SPX\" textFill=\"#fbf9fa\">\n" +
            "                            <font>\n" +
            "                                <Font size=\"35.0\"/>\n" +
            "                            </font>\n" +
            "                        </Label>\n" +
            "                        <Label fx:id=\"logo_second\" maxHeight=\"1.7976931348623157E308\" maxWidth=\"1.7976931348623157E308\"\n" +
            "                               minHeight=\"-Infinity\" minWidth=\"-Infinity\"\n" +
            "                               style=\"-fx-font-family: 'Fjalla One'; -fx-font-size: 35;\" text=\"AIO WALKING\"\n" +
            "                               textFill=\"#a82219\">\n" +
            "                            <font>\n" +
            "                                <Font size=\"35.0\"/>\n" +
            "                            </font>\n" +
            "                        </Label>\n" +
            "                    </children>\n" +
            "                    <VBox.margin>\n" +
            "                        <Insets/>\n" +
            "                    </VBox.margin>\n" +
            "                </HBox>\n" +
            "                <Separator prefWidth=\"270.0\" style=\"-fx-border-radius: 5px;\">\n" +
            "                    <VBox.margin>\n" +
            "                        <Insets left=\"15.0\" right=\"15.0\"/>\n" +
            "                    </VBox.margin>\n" +
            "                </Separator>\n" +
            "            </children>\n" +
            "        </VBox>\n" +
            "        <VBox alignment=\"CENTER\" spacing=\"15.0\" VBox.vgrow=\"ALWAYS\">\n" +
            "            <children>\n" +
            "                <ComboBox fx:id=\"locations\" maxWidth=\"200.0\" minWidth=\"200.0\" prefHeight=\"30.0\" prefWidth=\"200.0\"\n" +
            "                          promptText=\"Location\"\n" +
            "                          style=\"-fx-border-color: #fbf9fa; -fx-background-color: #fbf9fa; -fx-border-radius: 5px;\"/>\n" +
            "                <HBox alignment=\"CENTER\" spacing=\"10.0\">\n" +
            "                    <children>\n" +
            "                        <TextField fx:id=\"location_name\" prefHeight=\"30.0\" prefWidth=\"95.0\" promptText=\"Name\">\n" +
            "                            <font>\n" +
            "                                <Font size=\"12.0\"/>\n" +
            "                            </font>\n" +
            "                        </TextField>\n" +
            "                        <TextField fx:id=\"location_position\" prefHeight=\"30.0\" prefWidth=\"95.0\"\n" +
            "                                   promptText=\"0000,0000,0\">\n" +
            "                            <font>\n" +
            "                                <Font size=\"12.0\"/>\n" +
            "                            </font>\n" +
            "                        </TextField>\n" +
            "                    </children>\n" +
            "                </HBox>\n" +
            "                <HBox alignment=\"CENTER\" spacing=\"90.0\">\n" +
            "                    <children>\n" +
            "                        <Hyperlink fx:id=\"view_map\" focusTraversable=\"false\" text=\"View map\">\n" +
            "                            <HBox.margin>\n" +
            "                                <Insets/>\n" +
            "                            </HBox.margin>\n" +
            "                        </Hyperlink>\n" +
            "                        <Button fx:id=\"add_location\" mnemonicParsing=\"false\" prefHeight=\"30.0\"\n" +
            "                                style=\"-fx-background-color: #a82219;\" text=\"ADD\" textFill=\"#fbf9fa\">\n" +
            "                            <font>\n" +
            "                                <Font name=\"System Bold\" size=\"12.0\"/>\n" +
            "                            </font>\n" +
            "                        </Button>\n" +
            "                    </children>\n" +
            "                    <VBox.margin>\n" +
            "                        <Insets/>\n" +
            "                    </VBox.margin>\n" +
            "                </HBox>\n" +
            "            </children>\n" +
            "            <VBox.margin>\n" +
            "                <Insets/>\n" +
            "            </VBox.margin>\n" +
            "        </VBox>\n" +
            "        <VBox alignment=\"BOTTOM_CENTER\">\n" +
            "            <children>\n" +
            "                <HBox alignment=\"BOTTOM_CENTER\">\n" +
            "                    <children>\n" +
            "                        <Button fx:id=\"start\" mnemonicParsing=\"false\" prefHeight=\"30.0\" prefWidth=\"100.0\"\n" +
            "                                style=\"-fx-background-color: #a82219;\" text=\"START\" textFill=\"#fbf9fa\">\n" +
            "                            <font>\n" +
            "                                <Font name=\"System Bold\" size=\"12.0\"/>\n" +
            "                            </font>\n" +
            "                            <HBox.margin>\n" +
            "                                <Insets/>\n" +
            "                            </HBox.margin>\n" +
            "                        </Button>\n" +
            "                    </children>\n" +
            "                </HBox>\n" +
            "            </children>\n" +
            "            <VBox.margin>\n" +
            "                <Insets/>\n" +
            "            </VBox.margin>\n" +
            "        </VBox>\n" +
            "    </children>\n" +
            "    <padding>\n" +
            "        <Insets bottom=\"15.0\" left=\"10.0\" right=\"10.0\" top=\"5.0\"/>\n" +
            "    </padding>\n" +
            "</VBox>\n");

    private final String fxml;

    SPXFXML(String fxml) {
        this.fxml = fxml;
    }

    public String getFXML() {
        return fxml;
    }
}

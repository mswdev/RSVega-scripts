package sphiinx.api.framework.ui.swing.components;

import sphiinx.api.framework.SPXColor;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AutoCompleteComboBox extends JComboBox {

    private JTextField text_field = null;
    private int caret_position = 0;

    public AutoCompleteComboBox(Object[] elements, String place_holder) {
        this(elements, place_holder, 200, 25);
    }

    public AutoCompleteComboBox(Object[] elements, String place_holder, int width, int height) {
        super(elements);
        setPreferredSize(new Dimension(width, height));
        listenForMatch(new BasicComboBoxEditor());
        setUI(getComboBoxUI());
        setEditable(true);
        setSelectedItem(place_holder);
    }

    public BasicComboBoxUI getComboBoxUI() {
        return new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                final JButton BUTTON = new BasicArrowButton(BasicArrowButton.SOUTH, SPXColor.SPX_WHITE.getColor(), SPXColor.SPX_WHITE.getColor(), SPXColor.SPX_WHITE.getColor(), SPXColor.SPX_WHITE.getColor());
                BUTTON.setBackground(SPXColor.SPX_RED.getColor());
                BUTTON.setBorder(BorderFactory.createLineBorder(SPXColor.SPX_RED.getColor(), 3, true));
                return BUTTON;
            }
        };
    }

    public void setSelectedIndex(int index) {
        super.setSelectedIndex(index);
        text_field.setText(getItemAt(index).toString());
        text_field.setSelectionEnd(caret_position + text_field.getText().length());
        text_field.moveCaretPosition(caret_position);
    }

    private void listenForMatch(ComboBoxEditor editor) {
        super.setEditor(editor);
        text_field = (JTextField) editor.getEditorComponent();

        text_field.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                final char KEY = event.getKeyChar();
                if (!Character.isLetterOrDigit(KEY))
                    return;

                if (Character.isSpaceChar(KEY))
                    return;

                caret_position = text_field.getCaretPosition();
                String text = "";
                try {
                    text = text_field.getText(0, caret_position).toLowerCase();
                } catch (javax.swing.text.BadLocationException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < getItemCount(); i++) {
                    final String ELEMENT = getItemAt(i).toString().toLowerCase();
                    if (ELEMENT.startsWith(text)) {
                        setSelectedIndex(i);

                        return;
                    }
                }
            }
        });
    }

}

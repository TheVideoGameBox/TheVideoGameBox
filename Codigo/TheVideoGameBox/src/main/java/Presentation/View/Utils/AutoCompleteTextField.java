package Presentation.View.Utils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class AutoCompleteTextField extends TextField {

    /**
     *
     */
    private ArrayList<String> items;


    private static final long serialVersionUID = 1L;

    public AutoCompleteTextField(Dimension dimension, ArrayList<String> items) {
        super(dimension);
        this.items = items;
    }


    public AutoCompleteTextField(Dimension dimension, String prompt, ArrayList<String> items) {
        super(dimension, prompt);
        this.items = items;

    }

    private boolean isAdjusting(JComboBox<String> cbInput) {
        if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
            return (Boolean) cbInput.getClientProperty("is_adjusting");
        }
        return false;
    }

    private void setAdjusting(JComboBox<String> cbInput, boolean adjusting) {
        cbInput.putClientProperty("is_adjusting", adjusting);
    }


    public void setupAutoComplete() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        JComboBox<String> cbInput = new JComboBox<String>(model) {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, -20);
            }
        };
        setAdjusting(cbInput, false);
        for (String item : items) {
            model.addElement(item);
        }
        cbInput.setSelectedItem(null);
        cbInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAdjusting(cbInput)) {
                    if (cbInput.getSelectedItem() != null) {
                        setText(cbInput.getSelectedItem().toString());
                    }
                }
            }
        });

        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                setAdjusting(cbInput, true);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (cbInput.isPopupVisible()) {
                        e.setKeyCode(KeyEvent.VK_ENTER);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    e.setSource(cbInput);
                    cbInput.dispatchEvent(e);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        setText(cbInput.getSelectedItem().toString());
                        cbInput.setPopupVisible(false);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cbInput.setPopupVisible(false);
                }
                setAdjusting(cbInput, false);
            }
        });
        this.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            public void changedUpdate(DocumentEvent e) {
                updateList();
            }

            private void updateList() {
                setAdjusting(cbInput, true);
                model.removeAllElements();
                String input = getText();
                if (!input.isEmpty()) {
                    for (String item : items) {
                        if (item.toLowerCase().startsWith(input.toLowerCase())) {
                            model.addElement(item);
                        }
                    }
                }
                cbInput.hidePopup();
                cbInput.setPopupVisible(model.getSize() > 0);
                setAdjusting(cbInput, false);
            }
        });
        this.setLayout(new BorderLayout());
        this.add(cbInput, BorderLayout.SOUTH);
    }

}

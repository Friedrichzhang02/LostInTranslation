package translation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.Array;
import java.util.Arrays;


// DONETODO Task D: Update the GUI for the program to align with UI shown in the README example.
//            Currently, the program only uses the CanadaTranslator and the user has
//            to manually enter the language code they want to use for the translation.
//            See the examples package for some code snippets that may be useful when updating
//            the GUI.
public class GUI {

    static Translator translator = new JSONTranslator();
    static LanguageCodeConverter languageCodeConverter = new LanguageCodeConverter();
    static CountryCodeConverter countryCodeConverter = new CountryCodeConverter();


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // LANGUAGE
            JPanel languagePanel = new JPanel();
            languagePanel.add(new JLabel("Language:"));
            languagePanel.setPreferredSize(new Dimension(350, 55));
            JComboBox<String> languageComboBox = new JComboBox<>();
            languageComboBox.setPreferredSize(new Dimension(240, 20));

            for(String languageCode : translator.getLanguageCodes()) {
                languageComboBox.addItem(languageCodeConverter.fromLanguageCode(languageCode));
            }
            languagePanel.add(languageComboBox);
            languageComboBox.setSelectedItem("de");

            languageComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {

                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String country = languageComboBox.getSelectedItem().toString();
                        JOptionPane.showMessageDialog(null, "user selected " + country + "!");
                    }
                }
            });


            // COUNTRY
            JPanel spacePanel = new JPanel();
            JPanel countryPanel = new JPanel();
            spacePanel.add(new JLabel("Translation:"));
            countryPanel.setLayout(new GridLayout(1, 1));

            String[] items = new String[translator.getCountryCodes().size()];

            int i = 0;
            for(String countryCode : translator.getCountryCodes()) {
                items[i++] = countryCodeConverter.fromCountryCode(countryCode);
            }

            JList<String> list = new JList<>(items);
            list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

            JScrollPane scrollPane = new JScrollPane(list);
            countryPanel.add(scrollPane);

            list.addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e) {

                    int[] indices = list.getSelectedIndices();
                    String[] items = new String[indices.length];
                    for (int i = 0; i < indices.length; i++) {
                        items[i] = list.getModel().getElementAt(indices[i]);
                    }

                    String[] results = new String[items.length];
                    for (int i = 0; i < items.length; i++) {
                        String language = languageCodeConverter.fromLanguage(languageComboBox.getSelectedItem().toString());
                        String country = countryCodeConverter.fromCountry(items[i]);
                        String result = translator.translate(country, language);
                        if (result == null) {
                            result = "no translation found!";
                        }
                        results[i] = result;
                    }

                    JOptionPane.showMessageDialog(null, "Translation:" +
                            System.lineSeparator() + Arrays.toString(results));

                }
            });

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(languagePanel);
            mainPanel.add(spacePanel);
            mainPanel.add(countryPanel);

            JFrame frame = new JFrame("Country Name Translator");
            frame.setContentPane(mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

        });
    }
}

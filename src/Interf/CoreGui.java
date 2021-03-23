package Interf;
import Parser.*;
import javax.management.openmbean.ArrayType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
public class CoreGui implements ActionListener{
    private JFrame frame;
    private JButton central_button = new JButton("Ejecutar Análisis");

    private JTextArea textArea1 = new JTextArea(10, 20);
    private JTextArea textArea2 = new JTextArea(10, 20);
    private JPanel panel = new JPanel();
    private JPanel pane_1 = new JPanel();
    private String [] column_names = {"Token", "Id", "#", "-", "-", "-"};
    private DefaultTableModel register_contents = new DefaultTableModel(0, 0);;
    private JTable table = new JTable();
    public CoreGui() {
        central_button.addActionListener((ActionListener) this);
        frame = new JFrame();
        textArea1.setLineWrap(true);
        textArea2.setLineWrap(true);
        register_contents.setColumnIdentifiers(column_names);
        table.setModel(register_contents);
        JScrollPane scroller1 = new JScrollPane(textArea1);
        JScrollPane scroller2 = new JScrollPane(textArea2);
        JScrollPane scroller3 = new JScrollPane(table);
        scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroller3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        central_button.setPreferredSize(new Dimension(200, 100));
        pane_1.add(scroller3);
        panel.add(scroller1);
        panel.add(central_button);
        panel.add(scroller2);

        frame.setSize(1200,800);
        frame.getContentPane().add( BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, pane_1);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ev) {
        //textArea1.append("button clicked \n ");
        String contents = textArea1.getText().toString();
        Tokenizer tokens = new Tokenizer(contents);
        tokens.tokenizeString();
        for (Token token: tokens.getTokens()) {
            register_contents.addRow(new Object[]{token.getLexema(), token.getCategory(), token.getM_n(), ' ', ' ', ' '});
            System.out.println(token.getLexema());
        }
        textArea2.append(contents);

    }
    public Boolean isTokenInArray(String token, String[] words) {
        for(String word: words) {
            if (word.equals(token)) {
                return true;
            }
        }
        return false;
    }
    public Boolean isTokenNumber(String token) {
        if (token == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(token);
        }catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieInfo implements ActionListener {
    private JPanel rootPanel;
    private JTextField movieTitleText;
    private JLabel movieTitleLbl;
    private JLabel titleLbl;
    private JLabel yearLbl;
    private JTextField lastNameText;
    private JLabel directorLbl;
    private JTextField directorText;
    private JLabel genreLbl;
    private JTextField genreText;
    private JLabel ratingLbl;
    private JTextField ratingText;
    private JLabel countryLbl;
    private JTextField countryText;
    private JLabel watchLbl;
    private JPanel dataPanel;
    private JPanel tablePanel;
    private JScrollPane tableScrollPane;
    private JTable showTable;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;

    public MovieInfo(){

        createTable();
        buttonGroup();
    }

    public JPanel getRootPanel(){

        return rootPanel;
    }

    private void buttonGroup() {
        ButtonGroup group = new ButtonGroup();
        group.add(yesRadioButton);
        group.add(noRadioButton);
    }

    private void createTable(){

        Object[][] data = {
                {"The Dark Knight", 2008, "Bruce Wayne", "Dark", 9.0, "USA", "I"},
                {"Star Wars: Phantom Menace", 2005, "Wayne", "Sci-fi", 9.2, "USA", "I"},
                {"Inception", 2015, "Juan", "Thriller", 10, "USA", 0}
        };

        showTable.setModel(new DefaultTableModel(
                data,
                new String[]{"Title", "Year", "Director", "Genre", "Rating", "Country", "Status"} //columns in table
        ));

        //fixes the width of the column
        TableColumnModel columns = showTable.getColumnModel();
        columns.getColumn(0).setMinWidth(200);

        //centers the data
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(1).setCellRenderer(centerRender);
        columns.getColumn(4).setCellRenderer(centerRender);
        columns.getColumn(5).setCellRenderer(centerRender);
        columns.getColumn(6).setCellRenderer(centerRender);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

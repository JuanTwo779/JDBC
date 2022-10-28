package UI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MovieInfo  {
    private JPanel rootPanel;
    private JTextField txtMovie;
    private JLabel lblMovie;
    private JLabel lblTitle;
    private JLabel lblYear;
    private JTextField txtYear;
    private JLabel lblDirector;
    private JTextField txtDirector;
    private JLabel lblGenre;
    private JTextField txtGenre;
    private JLabel lblRating;
    private JTextField txtRating;
    private JLabel lblCountry;
    private JTextField txtCountry;
    private JLabel lblWatch;
    private JTextField txtWatch;
    private JPanel dataPanel;
    private JPanel tablePanel;
    private JScrollPane tableScrollPane;
    private JTable showTable;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;

    private Object[][] data;

    private Connection con;
    private Statement st;
    private PreparedStatement pst; //accepts input params

    public MovieInfo() {

        connect();
        createTable();
        addBtnAction();
        editBtnAction();
        deleteBtnAction();

        updateTable();
        selectTable();


    }

    public JPanel getRootPanel(){

        return rootPanel;
    }

    //connection to DB
    public void connect(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_data", "root", "password");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void createTable(){

        data = new Object[][]{};

//        {"The Dark Knight", 2008, "Bruce Wayne", "Dark", 9.0, "USA", "I"},

        showTable.setModel(new DefaultTableModel(
                data,
                new String[]{"ID", "Title", "Year", "Director",
                        "Genre", "Rating", "Country", "Status"} //columns in table
        ));

        //fixes the width of the column
        TableColumnModel columns = showTable.getColumnModel();
        columns.getColumn(0).setMaxWidth(40);
        columns.getColumn(0).setMinWidth(30);
        columns.getColumn(2).setMaxWidth(50);
        columns.getColumn(2).setMinWidth(30);
        columns.getColumn(5).setMaxWidth(50);
        columns.getColumn(5).setMinWidth(30);

        //centers the data
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(0).setCellRenderer(centerRender);
        columns.getColumn(2).setCellRenderer(centerRender);
        columns.getColumn(5).setCellRenderer(centerRender);
        columns.getColumn(6).setCellRenderer(centerRender);
    }

    private void updateTable(){
        int numColumn;
        try {
            pst = con.prepareStatement("select * from movies");
            ResultSet resultSet = pst.executeQuery();

            ResultSetMetaData rsmd = resultSet.getMetaData();
            numColumn = rsmd.getColumnCount();
            DefaultTableModel sDFT = (DefaultTableModel) showTable.getModel();
            sDFT.setRowCount(0);

            while (resultSet.next()){ //first loop to go through the tuples
                Vector v = new Vector();

                for (int i = 1; i < numColumn; i++) { //second loop to go through columns
                    v.add(resultSet.getString("movie_id"));
                    v.add(resultSet.getString("movie_title"));
                    v.add(resultSet.getString("movie_year"));
                    v.add(resultSet.getString("movie_dir"));
                    v.add(resultSet.getString("movie_gen"));
                    v.add(resultSet.getString("movie_rat"));
                    v.add(resultSet.getString("movie_country"));
                    v.add(resultSet.getString("movie_status"));
                }
                sDFT.addRow(v);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private void selectTable(){
        showTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                DefaultTableModel model = (DefaultTableModel) showTable.getModel();
                int selectedRow = showTable.getSelectedRow();

                int id = Integer.parseInt((model.getValueAt(selectedRow, 0).toString()));
                txtMovie.setText(model.getValueAt(selectedRow, 1).toString());
                txtYear.setText(model.getValueAt(selectedRow, 2).toString());
                txtDirector.setText(model.getValueAt(selectedRow, 3).toString());
                txtGenre.setText(model.getValueAt(selectedRow, 4).toString());
                txtRating.setText(model.getValueAt(selectedRow, 5).toString());
                txtCountry.setText(model.getValueAt(selectedRow, 6).toString());
                txtWatch.setText(model.getValueAt(selectedRow, 7).toString());
            }
        });
    }

    private void addBtnAction()
    {
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String title, year, director, genre, rating, country, status;

                title = txtMovie.getText();
                year = txtYear.getText();
                director = txtDirector.getText();
                genre = txtGenre.getText();
                rating = txtRating.getText();
                country = txtCountry.getText();
                status = txtWatch.getText();

                try {
                    pst = con.prepareStatement(
                            "insert into movies(movie_title, movie_year, movie_dir,movie_gen, " +
                                    "movie_rat, movie_country, movie_status) values (?,?,?,?,?,?,?)");
                    pst.setString(1,title);
                    pst.setString(2,year);
                    pst.setString(3,director);
                    pst.setString(4,genre);
                    pst.setString(5,rating);
                    pst.setString(6,country);
                    pst.setString(7,status);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog( null, "Movie added");
                    updateTable();

                    txtMovie.setText("");
                    txtYear.setText("");
                    txtDirector.setText("");
                    txtGenre.setText("");
                    txtRating.setText("");
                    txtCountry.setText("");
                    txtWatch.setText("");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog( null, "Text fields cannot be empty");
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    private void editBtnAction()
    {
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    DefaultTableModel model = (DefaultTableModel) showTable.getModel();
                    int selectedRow = showTable.getSelectedRow();
                    int id = Integer.parseInt((model.getValueAt(selectedRow, 0).toString()));

                    String title, year, director, genre, rating, country, status;
                    title = txtMovie.getText();
                    year = txtYear.getText();
                    director = txtDirector.getText();
                    genre = txtGenre.getText();
                    rating = txtRating.getText();
                    country = txtCountry.getText();
                    status = txtWatch.getText();

                    pst = con.prepareStatement(
                            "update movies set movie_title=?, movie_year=?, movie_dir=?, movie_gen=?, " +
                                    "movie_rat=?, movie_country=?, movie_status=? where movie_id=?");
                    pst.setString(1,title);
                    pst.setString(2,year);
                    pst.setString(3,director);
                    pst.setString(4,genre);
                    pst.setString(5,rating);
                    pst.setString(6,country);
                    pst.setString(7,status);

                    pst.setInt(8,id); //get the ID to change parameters

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog( null, "Movie edited");
                    updateTable();

                    txtMovie.setText("");
                    txtYear.setText("");
                    txtDirector.setText("");
                    txtGenre.setText("");
                    txtRating.setText("");
                    txtCountry.setText("");
                    txtWatch.setText("");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog( null, "Select a movie from the table to edit");
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void deleteBtnAction()
    {
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DefaultTableModel model = (DefaultTableModel) showTable.getModel();
                    int selectedRow = showTable.getSelectedRow();
                    int id = Integer.parseInt((model.getValueAt(selectedRow, 0).toString()));

                    pst = con.prepareStatement("delete from movies where movie_id=?");
                    pst.setInt(1,id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog( null, "Movie deleted from table");
                    updateTable();

                    txtMovie.setText("");
                    txtYear.setText("");
                    txtDirector.setText("");
                    txtGenre.setText("");
                    txtRating.setText("");
                    txtCountry.setText("");
                    txtWatch.setText("");
                }
                catch(Exception exe){
                    JOptionPane.showMessageDialog( null, "Select a movie from table to delete");
                    throw new RuntimeException(exe);
                }
            }
        });
    }


}

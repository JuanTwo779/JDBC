package UI;

import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }

    public JPanel getRootPanel(){

        return rootPanel;
    }

    //connection to DB
    public void connect(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_data", "root", "Hansosjsneia-90");

            //shows tuples in table on console
//            ResultSet resultSet = statement.executeQuery("select * from movies");
//            while(resultSet.next()){
//                System.out.println(resultSet.getString(2));
//            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void createTable(){

        data = new Object[][]{};

//        {"The Dark Knight", 2008, "Bruce Wayne", "Dark", 9.0, "USA", "I"},
//        {"Star Wars: Phantom Menace", 2005, "Wayne", "Sci-fi", 9.2, "USA", "I"},
//        {"Inception", 2015, "Juan", "Thriller", 10, "USA", 0}

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

                    txtMovie.setText("");
                    txtYear.setText("");
                    txtDirector.setText("");
                    txtGenre.setText("");
                    txtRating.setText("");
                    txtCountry.setText("");
                    txtWatch.setText("");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    private void editBtnAction()
    {
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello");

            }
        });
    }

    private void deleteBtnAction()
    {
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello");

            }
        });
    }


}

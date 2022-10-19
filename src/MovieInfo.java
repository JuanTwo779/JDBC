import javax.swing.*;

public class MovieInfo {
    private JTextField movieTitleText;
    private JLabel movieTitleLbl;
    private JLabel titleLbl;
    private JLabel yearLbl;
    private JTextField lastNameText;
    private JButton saveAndAddDataButton;
    private JLabel directorLbl;
    private JTextField directorText;
    private JLabel genreLbl;
    private JTextField genreText;
    private JLabel ratingLbl;
    private JTextField ratingText;
    private JLabel countryLbl;
    private JTextField countryText;
    private JRadioButton yesRadioBtn;
    private JLabel watchLbl;
    private JRadioButton noRadioBtn;
    private JPanel rootPanel;
    private JPanel dataPanel;
    private JPanel tablePanel;
    private JScrollPane tableScrollPane;
    private JTable showTable;
    private JPanel titlePanel;

    public MovieInfo(){
        createTable();
    }

    public JPanel getRootPanel(){
        return rootPanel;
    }

    private void createTable(){

    }
}

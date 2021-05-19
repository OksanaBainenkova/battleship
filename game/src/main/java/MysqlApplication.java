import java.sql.*;

public class MysqlApplication {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/battleship_score", "root", "root");
        PlayerMapper playerMapper = new PlayerMapper(connection);
        System.out.println(playerMapper.findAll());
        Player player = new Player();
        player.setName(Application.playerName1);
        playerMapper.insert(player);
    }
}

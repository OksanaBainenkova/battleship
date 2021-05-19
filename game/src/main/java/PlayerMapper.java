import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerMapper {

    Connection connection;

    public PlayerMapper(Connection connection) {
        this.connection = connection;
    };

    List<Player> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from player");
        List<Player> playerList = new ArrayList<>();
        while(rs.next()){
            Player player = new Player();
            player.setId(rs.getInt("id"));
            player.setName(rs.getString("name"));
            playerList.add(player);
        }
        return playerList;
    }

    void insert(Player player) throws SQLException {
        String sql = "insert into Player(name) values (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, player.getName());
        statement.execute();
    }
}

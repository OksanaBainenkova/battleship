import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerMapper {

    Connection connection;

    public PlayerMapper(Connection connection) {
        this.connection = connection;
    }

    ;

    List<Player> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from player");
        List<Player> playerList = new ArrayList<>();
        while (rs.next()) {
            Player player = new Player();
            player.setId(rs.getInt("id"));
            player.setName(rs.getString("name"));
            playerList.add(player);
        }
        return playerList;
    }

    Player insert(Player player) throws SQLException {
        String sql = "insert into Player(name) values (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, player.getName());
        statement.execute();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT last_insert_id()");
        rs.next();
        player.setId(rs.getInt(1));
        return player;
    }
}

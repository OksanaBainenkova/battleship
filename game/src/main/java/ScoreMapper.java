import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreMapper {

    Connection connection;

    public ScoreMapper(Connection connection) {
        this.connection = connection;
    }

    ;

    List<Battleship_match> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select player1.name, player2.name, player_1_score, player_2_score * from battleship_match inner join player as player1 on battleship_match.player_1_id = player1.id inner join player as player2 on battleship_match.player_2_id = player2.id");
        List<Battleship_match> BattleshipMatchList = new ArrayList<>();
        while (rs.next()) {
            Battleship_match battleship_match = new Battleship_match(
                    rs.getString("player1.name"),
                    rs.getString("player2.name"),
                    rs.getInt("player_1_score"),
                    rs.getInt("player_2_score")
            );
            BattleshipMatchList.add(battleship_match);
        }
        return BattleshipMatchList;
    }

    void insert(Player player1, Player player2, Integer scorePlayer1, Integer scorePlayer2) throws SQLException {
        String sql = "insert into battleship_match(player_1_id, player_2_id, player_1_score, player_2_score) values (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, player1.getId());
        statement.setInt(2, player1.getId());
        statement.setInt(3, scorePlayer1);
        statement.setInt(4, scorePlayer2);
        statement.execute();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT last_insert_id()");
        rs.next();
    }
}


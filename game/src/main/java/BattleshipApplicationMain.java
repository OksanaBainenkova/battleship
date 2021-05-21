import java.sql.SQLException;
import java.util.Scanner;

public class BattleshipApplicationMain {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);

        PlayerMapper playerMapper = new PlayerMapper(MysqlApplication.createConnection());
        ScoreMapper scoreMapper = new ScoreMapper(MysqlApplication.createConnection());

        System.out.println("Player#1, please enter your name:");
        BattleshipApplicationService.playerName1 = scanner.nextLine();
        Player player1 = playerMapper.insert(new Player(BattleshipApplicationService.playerName1));
        System.out.println("Player#2, please enter your name:");
        BattleshipApplicationService.playerName2 = scanner.nextLine();
        Player player2 = playerMapper.insert(new Player(BattleshipApplicationService.playerName2));

        BattleshipApplicationService.placeShips(BattleshipApplicationService.playerName1, BattleshipApplicationService.battlefield1);
        BattleshipApplicationService.placeShips(BattleshipApplicationService.playerName2, BattleshipApplicationService.battlefield2);

        while (true) {
            BattleshipApplicationService.makeTurn(BattleshipApplicationService.playerName1, BattleshipApplicationService.monitor1, BattleshipApplicationService.battlefield2, player1, player2);
            if (BattleshipApplicationService.isWinCondition(player1, player2)) {
                break;
            }
            BattleshipApplicationService.makeTurn(BattleshipApplicationService.playerName2,BattleshipApplicationService.monitor2, BattleshipApplicationService.battlefield1, player1, player2);
            if (BattleshipApplicationService.isWinCondition(player1, player2)) {
                break;
            }
        }
    }
}
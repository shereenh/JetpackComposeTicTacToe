package sheen.dev.jetpackcomposetictactoe

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class GameUiState (
    val status: String = "",
    val gameStatus: GameStatus = GameStatus.Active,
    val board: IntArray = intArrayOf()
)

class GameViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    lateinit var gameBoard: GameBoard

    private val getPlayerName : () -> String = {
        when (gameBoard.getCurrentPlayer()) {
            1 -> "Turn: Player 1"
            2 -> "Turn: Player 2"
            else -> "Invalid Player"
        }
    }

    init {
        gameBoard = GameBoard()
    }

    fun resetGame() {
        gameBoard.resetGame()
        _uiState.update { currentState ->
            currentState.copy(
                status = getPlayerName(),
                gameStatus = gameBoard.getGameStatus(),
                board = gameBoard.getGameBoard()
            )
        }
    }

    fun playTurn(move: Int) {
        val moveStatue = gameBoard.playTurn(move)

        _uiState.update { currentState ->
            when (moveStatue) {
                MoveStatus.Success -> {
                    currentState.copy(
                        status = getPlayerName(),
                        gameStatus = gameBoard.getGameStatus(),
                        board = gameBoard.getGameBoard()
                    )
                }
                MoveStatus.Failure_Game_Over -> {
                    currentState.copy(
                        gameStatus = gameBoard.getGameStatus(),
                        board = gameBoard.getGameBoard()
                    )
                }
                MoveStatus.Failure_Move_Exists -> {
                    currentState.copy(
                        gameStatus = gameBoard.getGameStatus(),
                        board = gameBoard.getGameBoard()
                    )
                }
            }
        }
    }
}
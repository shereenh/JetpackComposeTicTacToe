package sheen.dev.jetpackcomposetictactoe

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class GameBoardTest {
    @Test
    fun resetTest() {
        val gameBoard = GameBoard() // reset already called in init, no need to call explicitly
        val board = gameBoard.board
        assertArrayEquals(IntArray(9), board)
        val currentPlayer = gameBoard.currentPlayer
        assertEquals(1, currentPlayer)
        val moveCounter = gameBoard.moveCounter
        assertEquals(0, moveCounter)
        val status = gameBoard.status
        assertEquals( GameStatus.Active, status)
    }

    @Test
    fun startGameWithPlayer1Test() {
        val gameBoard = GameBoard()
        gameBoard.startGameWithPlayer1()
        assertEquals(1, gameBoard.currentPlayer)
    }

    @Test
    fun startGameWithPlayer2Test() {
        val gameBoard = GameBoard()
        gameBoard.startGameWithPlayer2()
        assertEquals(2, gameBoard.currentPlayer)
    }

    @Test
    fun playTurnTest() {
        val gameBoard = GameBoard()
        val status = gameBoard.playTurn(move = 0)
        assertEquals(GameStatus.Active, gameBoard.status)
        assertEquals(MoveStatus.Success, status)
        assertEquals(1, gameBoard.moveCounter)
        val expectedArray = intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0)
        assertArrayEquals(expectedArray, gameBoard.board)
        assertEquals(2, gameBoard.currentPlayer)
    }

    @Test
    fun moveStatus_FailureMoveExistsTest() {
        val gameBoard = GameBoard()
        gameBoard.playTurn(0)
        val status = gameBoard.playTurn(0)
        assertEquals(status, MoveStatus.Failure_Move_Exists)
    }

    @Test
    fun verticalWinTest() {
        val gameBoard = GameBoard()

        val checkGamePlay: (MoveStatus, GameStatus) -> Unit = { mStatus, gStatus ->
            assertEquals(mStatus, MoveStatus.Success)
            assertEquals(gStatus, GameStatus.Active)
        }

        var moveStatus = gameBoard.playTurn(0) // player 1
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(1) // player 2
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(3) // player 1
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(4) // player 2
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(6) // player 1
        assertEquals(moveStatus, MoveStatus.Success)
        assertEquals(gameBoard.status, GameStatus.Player_1)
    }

    @Test
    fun horizontalWinTest() {
        val gameBoard = GameBoard()

        val checkGamePlay: (MoveStatus, GameStatus) -> Unit = { mStatus, gStatus ->
            assertEquals(mStatus, MoveStatus.Success)
            assertEquals(gStatus, GameStatus.Active)
        }

        var moveStatus = gameBoard.playTurn(0) // player 1
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(4) // player 2
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(7) // player 1
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(3) // player 2
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(8) // player 1
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(5) // player 2
        assertEquals(moveStatus, MoveStatus.Success)
        assertEquals(gameBoard.status, GameStatus.Player_2)
    }

    @Test
    fun diagonalWinTest() {
        val gameBoard = GameBoard()

        val checkGamePlay: (MoveStatus, GameStatus) -> Unit = { mStatus, gStatus ->
            assertEquals(mStatus, MoveStatus.Success)
            assertEquals(gStatus, GameStatus.Active)
        }

        var moveStatus = gameBoard.playTurn(0) // player 1
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(2) // player 2
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(8) // player 1
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(5) // player 2
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(4) // player 1
        assertEquals(moveStatus, MoveStatus.Success)
        assertEquals(gameBoard.status, GameStatus.Player_1)
    }

    @Test
    fun noWinnerTest() {
        val gameBoard = GameBoard()

        val checkGamePlay: (MoveStatus, GameStatus) -> Unit = { mStatus, gStatus ->
            assertEquals(mStatus, MoveStatus.Success)
            assertEquals(gStatus, GameStatus.Active)
        }

        var moveStatus = gameBoard.playTurn(0) // player 1
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(2) // player 2
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(8) // player 1
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(4) // player 2
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(6) // player 1
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(3) // player 2
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(5) // player 1
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(7) // player 2
        checkGamePlay(moveStatus, gameBoard.status)

        moveStatus = gameBoard.playTurn(1) // player 1
        assertEquals(MoveStatus.Success, moveStatus)
        assertEquals(GameStatus.Game_Over, gameBoard.status)
    }

}




















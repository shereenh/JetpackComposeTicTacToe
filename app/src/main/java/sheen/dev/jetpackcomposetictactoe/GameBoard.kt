package sheen.dev.jetpackcomposetictactoe

import androidx.annotation.VisibleForTesting

enum class GameStatus {
    Player_1,
    Player_2,
    Game_Over,
    Active
}

enum class MoveStatus {
    Success,
    Failure_Move_Exists,
    Failure_Game_Over
}

class GameBoard {
    private val boardSize = 9

    @VisibleForTesting internal var board: IntArray = IntArray(boardSize)
    @VisibleForTesting internal var currentPlayer: Int = 1
    @VisibleForTesting internal var moveCounter: Int = 0
    @VisibleForTesting internal var status : GameStatus = GameStatus.Active


    init {
        resetGame()
    }

    fun getCurrentPlayer() = currentPlayer

    fun getGameStatus() = status

    fun getGameBoard() = board

    fun playTurn(move: Int): MoveStatus {
        if (status != GameStatus.Active) {
            return MoveStatus.Failure_Game_Over
        }
        if (board[move] != 0) {
            return MoveStatus.Failure_Move_Exists
        }
        moveCounter++
        playMove(move)
        return MoveStatus.Success
    }

    fun startGameWithPlayer1() {
        resetGame()
    }

    fun startGameWithPlayer2() {
        resetGame()
        currentPlayer = 2
    }

    fun resetGame() {
        board = IntArray(9)
        currentPlayer = 1
        moveCounter = 0
    }

    private fun isVerticalWin(move: Int): Boolean {
        var isWin = false
        val movePoint = 1
        val verticalCheck: (Int) -> Int = { index ->
            var result = 0
            if (index in 0..<boardSize) {
                if (board[index] == currentPlayer) {
                    result = 1
                }
            }
            result
        }

        val plus3 = verticalCheck(move+3)
        val plus6 = verticalCheck(move+6)
        val minus3 = verticalCheck(move-3)
        val minus6 = verticalCheck(move-6)
        isWin = movePoint + plus3 + plus6 == 3
                || movePoint + minus3 + minus6 == 3
                || movePoint + minus3 + plus3 == 3
        return isWin
    }

    private fun isHorizontalWin(move: Int): Boolean {
        var isWin = false

        val isLeftMostMove: (Int) -> Boolean = {
            it % 3 == 0
        }

        val isMiddleMove: (Int) -> Boolean = {
            it % 3 == 1
        }

        val isRightMostMove: (Int) -> Boolean = {
            it % 3 == 2
        }

        if (isLeftMostMove(move)) {
            if (board[move+1] == currentPlayer
                && board[move+2] == currentPlayer) {
                isWin = true
            }
        } else if (isMiddleMove(move)) {
            if (board[move-1] == currentPlayer
                && board[move+1] == currentPlayer) {
                isWin = true
            }
        } else if (isRightMostMove(move)) {
            if (board[move-1] == currentPlayer
                && board[move-2] == currentPlayer) {
                isWin = true
            }
        }
        return isWin
    }

    private fun isDiagonalWin(move: Int): Boolean {
        var isWin = false

        val isLeftDiagonalMove: (Int) -> Boolean = {
            it % 4 == 0
        }
        val isRightDiagonalMove: (Int) -> Boolean = {
            it % 2 == 0 && it != 8
        }

        val diagonalCheck: (Int) -> Int = { index ->
            var result = 0
            if (index in 0..<boardSize) {
                if (board[index] == currentPlayer) {
                    result = 1
                }
            }
            result
        }

        val movePlus4 = diagonalCheck(move + 4)
        val moveMinus4 = diagonalCheck(move - 4)

        if (isLeftDiagonalMove(move)) {
            val movePlus8 = diagonalCheck(move + 8)
            val moveMinus8 = diagonalCheck(move - 8)
            isWin = 1 + movePlus4 + movePlus8 == 3
                || 1 + moveMinus4 + moveMinus8 == 3
                || 1 + moveMinus4 + movePlus4 == 3

        } else if (isRightDiagonalMove(move)) {
            val movePlus2 = diagonalCheck(move + 2)
            val moveMinus2 = diagonalCheck(move - 2)
            isWin = 1 + movePlus2 + movePlus4 == 3
                    || 1 + movePlus2 + moveMinus2 == 3
                    || 1 + moveMinus2 + moveMinus4 == 3
        }
        return isWin
    }

    private fun playMove(move: Int) {
        board[move] = currentPlayer

        if (isVerticalWin(move)
            || isHorizontalWin(move)
            || isDiagonalWin(move)
            ){
                status = if (currentPlayer == 1) {
                    GameStatus.Player_1
                } else {
                    GameStatus.Player_2
                }
        }

        currentPlayer = if (currentPlayer == 1) {
            2
        } else {
            1
        }

        if (moveCounter == 9) {
            status = GameStatus.Game_Over
        }
    }
}
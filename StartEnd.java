public class StartEnd {
    private Cell cell;

    public StartEnd(Cell cell) {
        this.cell = cell;
    }

    public void startGame() {
        /*Score scoreNum = new Score();
        scoreNum.score = 0;
        Score highestScore = new Score();
        highestScore.highest = 0;*/ // todo.
        cell.createNewTile();
    }

    public void checkGameEnd() {
        if (cell.gameOver()) {
            System.out.println("WON");
            // todo
        } else if (cell.isBoardFull() && !cell.canTilesMove()) {
            System.out.println("LOST");
            // todo
        } else {
            cell.createNewTile();
        }
    }
}

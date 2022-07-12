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
        cell.createNewTile();
    }

    public boolean checkGameEnd() {
        boolean ret = false;
        if (cell.gameOver()) {
            System.out.println("WON");
            ret = true;
            // todo
        } else if (!cell.canTilesMove()) {
            System.out.println("LOST");
            ret = true;
            // todo
        }

        return ret;
    }
}

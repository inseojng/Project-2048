import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cell {

    private int N, M;
    private Block[][] grid;
    private Random rand;

    public Cell() {
        this(4, 4);
    }

    public Cell(int _N, int _M) {
        this.N = _N; this.M = _M;
        rand = new Random();
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public void fillGrid() {
        this.grid = new Block[this.N][this.M];
        for(int i=0; i<this.N; i++) {
            for(int j=0; j<this.M; j++) {
                this.grid[i][j] = new Block(i, j);
            }
        }
    }

    public void pushBlock(int y, int x, Block block) {
        block.setX(x);
        block.setY(y);
        this.grid[y][x]=block;
    }

    public boolean update(int dir) {
        boolean moved = false;
        if(dir==0) { // Left
            for(int i=0; i<this.N; i++) {
                List<Block> V = new ArrayList<>(6);
                for(int j=0; j<this.N; j++) {
                    if(this.grid[i][j].getVal()!=-1) V.add(this.grid[i][j]);
                }
                int cnt=0, size=V.size();
                for(int j=0; j<size; j++) {
                    if(j+1<size && V.get(j+1).getVal()==V.get(j).getVal()) {
                        V.get(j).addVal();
                        pushBlock(i, cnt++, V.get(j));
                        j++;
                    }
                    else {
                        pushBlock(i, cnt++, V.get(j));
                    }
                }
                for(; cnt<this.N; cnt++) pushBlock(i, cnt, new Block());
            }
        }
        else if(dir==1) { // Right
            for(int i=0; i<this.N; i++) {
                List<Block> V = new ArrayList<>(6);
                for(int j=this.N-1; j>=0; j--) {
                    if(this.grid[i][j].getVal()!=-1) V.add(this.grid[i][j]);
                }
                int cnt=0, size=V.size();
                for(int j=0; j<size; j++) {
                    if(j+1<size && V.get(j+1).getVal()==V.get(j).getVal()) {
                        V.get(j).addVal();
                        pushBlock(i, this.N-1-cnt++, V.get(j));
                        j++;
                    }
                    else {
                        pushBlock(i, this.N-1-cnt++, V.get(j));
                    }
                }
                for(; cnt<this.N; cnt++) pushBlock(i, this.N-1-cnt, new Block());
            }
        }
        else if(dir==2) { // Down
            for(int i=0; i<this.N; i++) {
                List<Block> V = new ArrayList<>(6);
                for(int j=0; j<this.N; j++) {
                    if(this.grid[j][i].getVal()!=-1) V.add(this.grid[j][i]);
                }
                int cnt=0, size = V.size();
                for(int j=0; j<size; j++) {
                    if(j+1<size && V.get(j+1).getVal()==V.get(j).getVal()) {
                        V.get(j).addVal();
                        pushBlock(cnt++, i, V.get(j));
                        j++;
                    }
                    else {
                        pushBlock(cnt++, i, V.get(j));
                    }
                }
                for(; cnt<this.N; cnt++) pushBlock(cnt, i, new Block());
            }
        }
        else if(dir==3) { // Up
            for(int i=0; i<this.N; i++) {
                List<Block> V = new ArrayList<>(6);
                for(int j=this.N-1; j>=0; j--) {
                    if(this.grid[j][i].getVal()!=-1) V.add(this.grid[j][i]);
                }
                int cnt=0, size=V.size();
                for(int j=0; j<size; j++) {
                    if(j+1<size && V.get(j+1).getVal()==V.get(j).getVal()) {
                        V.get(j).addVal();
                        pushBlock(this.N-1-cnt++, i, V.get(j));
                        j++;
                    }
                    else {
                        pushBlock(this.N-1-cnt++, i, V.get(j));
                    }
                }
                for(; cnt<this.N; cnt++) pushBlock(this.N-1-cnt, i, new Block());
            }
        }
        return moved;
    }

    public boolean gameOver() {
        for(int i=0; i<this.N; i++) {
            for(int j=0; j<this.M; j++) {
                if(this.grid[i][j].getVal()==10) return true;
            }
        }
        return false;
    }

    public boolean isBoardFull() {
        for(int i=0; i<this.N; i++) {
            for(int j=0; j<this.M; j++) {
                if(this.grid[i][j].getVal()==-1) return false;
            }
        }
        return true;
    }

    public boolean canTilesMove() {
        if(!isBoardFull()) return true;
        for(int i=0; i<this.N; i++) {
            for(int j=0; j+1<this.M; j++) {
                if(this.grid[i][j].getVal()==this.grid[i][j+1].getVal()) return true;
                if(this.grid[j][i].getVal()==this.grid[j+1][i].getVal()) return true;
            }
        }
        return false;
    }

    public void createNewTile() {
        ArrayList<Block> V = new ArrayList<>(22);
        for(int i=0; i<this.N; i++) {
            for(int j=0; j<this.M; j++) {
                if(this.grid[i][j].getVal()==-1) V.add(this.grid[i][j]);
            }
        }

        create(V.get(rand.nextInt(V.size())));
    }

    private void create(Block b){
        b.setVal(rand.nextInt(5) == 0 ? 1 : 0);
    }

    // tmp, remove after.
    public Block[][] getGrid() {
        return grid;
    }
}
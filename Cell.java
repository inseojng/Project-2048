import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cell implements RenderComposite{

    private Score score;
    private int N, M;
    private int uDir;
    private boolean updating;
    private long sTime, dTime;
    private Block[][] grid;
    private Block[][] buff;
    private Random rand;

    public Cell(Score score) {
        this(score, 4, 4);
    }

    public Cell(Score score, int _N, int _M) {
        this.score = score;
        this.N = _N; this.M = _M;
        this.updating = false;
        this.sTime = 0; this.dTime = 0;
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
        buff = new Block[N][M];
        for(int i=0; i<this.N; i++) {
            for(int j=0; j<this.M; j++) {
                this.grid[i][j] = new Block(i, j);
            }
        }
    }

    public void pushBlock(int y, int x, Block block, int dv) {
        if(dv!=0) score.addScore(4<<block.getVal());
        this.grid[y][x]=new Block(y, x, block.getVal()+dv);
    }

    private void writeBuff(){
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                buff[i][j] = new Block(grid[i][j].getY(), grid[i][j].getX(), grid[i][j].getVal());
            }
        }
    }

    private boolean differentWithBuff(){
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if(buff[i][j].getVal() != grid[i][j].getVal())
                    return true;
            }
        }
        return false;
    }

    public boolean update(int dir, long tick) {
        if(updating) return false;
        writeBuff();
        if(dir==0) { // Left
            for(int i=0; i<this.N; i++) {
                List<Block> V = new ArrayList<>(6);
                for(int j=0; j<this.N; j++) {
                    if(this.grid[i][j].getVal()!=-1) V.add(this.buff[i][j]);
                }
                int cnt=0, size=V.size();
                for(int j=0; j<size; j++) {
                    if(j+1<size && V.get(j+1).getVal()==V.get(j).getVal()) {
                        V.get(j).setDx(GameRenderer.blockDiff*(cnt-V.get(j).getX()));
                        V.get(j+1).setDx(GameRenderer.blockDiff*(cnt-V.get(j+1).getX()));
                        pushBlock(i, cnt++, V.get(j), 1);
                        j++;
                    }
                    else {
                        V.get(j).setDx(GameRenderer.blockDiff*(cnt-V.get(j).getX()));
                        pushBlock(i, cnt++, V.get(j), 0);
                    }
                }
                for(; cnt<this.N; cnt++) pushBlock(i, cnt, new Block(), 0);
            }
        }
        else if(dir==1) { // Right
            for(int i=0; i<this.N; i++) {
                List<Block> V = new ArrayList<>(6);
                for(int j=this.N-1; j>=0; j--) {
                    if(this.grid[i][j].getVal()!=-1) V.add(this.buff[i][j]);
                }
                int cnt=0, size=V.size();
                for(int j=0; j<size; j++) {
                    if(j+1<size && V.get(j+1).getVal()==V.get(j).getVal()) {
                        V.get(j).setDx(GameRenderer.blockDiff*(this.N-1-cnt-V.get(j).getX()));
                        V.get(j+1).setDx(GameRenderer.blockDiff*(this.N-1-cnt-V.get(j+1).getX()));
                        pushBlock(i, this.N-1-cnt++, V.get(j), 1);
                        j++;
                    }
                    else {
                        V.get(j).setDx(GameRenderer.blockDiff*(this.N-1-cnt-V.get(j).getX()));
                        pushBlock(i, this.N-1-cnt++, V.get(j), 0);
                    }
                }
                for(; cnt<this.N; cnt++) pushBlock(i, this.N-1-cnt, new Block(), 0);
            }
        }
        else if(dir==2) { // Down
            for(int i=0; i<this.N; i++) {
                List<Block> V = new ArrayList<>(6);
                for(int j=0; j<this.N; j++) {
                    if(this.grid[j][i].getVal()!=-1) V.add(this.buff[j][i]);
                }
                int cnt=0, size = V.size();
                for(int j=0; j<size; j++) {
                    if(j+1<size && V.get(j+1).getVal()==V.get(j).getVal()) {
                        V.get(j).setDy(GameRenderer.blockDiff*(cnt-V.get(j).getY()));
                        V.get(j+1).setDy(GameRenderer.blockDiff*(cnt-V.get(j+1).getY()));
                        pushBlock(cnt++, i, V.get(j), 1);
                        j++;
                    }
                    else {
                        V.get(j).setDy(GameRenderer.blockDiff*(cnt-V.get(j).getY()));
                        pushBlock(cnt++, i, V.get(j), 0);
                    }
                }
                for(; cnt<this.N; cnt++) pushBlock(cnt, i, new Block(), 0);
            }
        }
        else if(dir==3) { // Up
            for(int i=0; i<this.N; i++) {
                List<Block> V = new ArrayList<>(6);
                for(int j=this.N-1; j>=0; j--) {
                    if(this.grid[j][i].getVal()!=-1) V.add(this.buff[j][i]);
                }
                int cnt=0, size=V.size();
                for(int j=0; j<size; j++) {
                    if(j+1<size && V.get(j+1).getVal()==V.get(j).getVal()) {
                        V.get(j).setDy(GameRenderer.blockDiff*(this.N-1-cnt-V.get(j).getY()));
                        V.get(j+1).setDy(GameRenderer.blockDiff*(this.N-1-cnt-V.get(j+1).getY()));
                        pushBlock(this.N-1-cnt++, i, V.get(j), 1);
                        j++;
                    }
                    else {
                        V.get(j).setDy(GameRenderer.blockDiff*(this.N-1-cnt-V.get(j).getY()));
                        pushBlock(this.N-1-cnt++, i, V.get(j), 0);
                    }
                }
                for(; cnt<this.N; cnt++) pushBlock(this.N-1-cnt, i, new Block(), 0);
            }
        }
        if(differentWithBuff()) {
            updating = true;
            sTime = tick;
            dTime = tick+5;
            uDir = dir;
            return true;
        }
        return false;
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

    private static final Color[] colorMap = {
            Color.rgb(238, 228, 218),
            Color.rgb(239, 226, 207),
            Color.rgb(246, 179, 127),
            Color.rgb(247, 152, 106),
            Color.rgb(247, 124, 95),
            Color.rgb(247, 95, 59),
            Color.rgb(237, 208, 115),
            Color.rgb(237, 204, 98),
            Color.rgb(237, 210, 119),
            Color.rgb(239, 207, 108),
            Color.rgb(237, 205, 94),
    };

    public static final Color textColor = Color.rgb(249, 246, 242);
    private static final Font blockFont = new Font("HY견고딕", 36);
    private static final Color textColor1 = Color.rgb(120, 110, 101);
    private static final Color textColor2 = Color.rgb(248, 243, 237);

    @Override
    public void render(GraphicsContext gc) {
        this.render(gc, 0);
    }
    @Override
    public void render(GraphicsContext gc, long tick) {
        int nx, ny;
        int blockSize = GameRenderer.blockSize;
        int blockCenter = blockSize / 2;
        Block[][] now;
        gc.setFont(blockFont);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        if(updating) now = this.buff;
        else now = this.grid;

        for (int ii = 0; ii < N; ++ii) {
            for (int jj = 0; jj < M; ++jj) {
                int i=ii, j=jj;
                if(uDir==2) i=N-i-1;
                if(uDir==0) j=N-j-1;

                int y=GameRenderer.startY+i*GameRenderer.blockDiff;
                int x=GameRenderer.startX+j*GameRenderer.blockDiff;
                //todo set Color;
                int val = now[i][j].getVal();
                if(val != -1) {
                    nx = x; ny = y;
                    if(updating) {
                        nx += now[i][j].getDx()*(tick-sTime)/(dTime-sTime);
                        ny += now[i][j].getDy()*(tick-sTime)/(dTime-sTime);
                    }
                    gc.setFill(colorMap[val]);
                    gc.fillRoundRect(nx, ny, blockSize, blockSize, 20, 20);
                    gc.setFill(val <= 1 ? textColor1 : textColor2);
                    gc.fillText("" + (2 << val), nx + blockCenter, ny + blockCenter);
                }
            }
        }
        if(tick>=dTime) {
            writeBuff();
            updating = false;
        }
    }
}
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.io.*;

public class Main {

    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

   public static void main(String[] args) throws IOException {
        GameManager manager = new Game2048Manager(() -> null, new Cell(4, 4));

        Thread t = new Thread(manager);
        t.start();
        InputHolder holder = manager.keyHolder;
        while (true){
            if(br.ready()){
                char c = br.readLine().charAt(0);

                switch (c){
                    case 'a':
                    case 'A':
                        holder.setKeyDown(KeyCode.LEFT);
                        break;
                    case 'd':
                    case 'D':
                        holder.setKeyDown(KeyCode.RIGHT);
                        break;
                    case 's':
                    case 'S':
                        holder.setKeyDown(KeyCode.DOWN);
                        break;
                    case 'w':
                    case 'W':
                        holder.setKeyDown(KeyCode.UP);
                        break;
                }
            }
        }
    }

    public static void drawConsole(Cell cell){
        try {
            Block[][] blocks = cell.getGrid();
            int n = cell.getN(), m = cell.getM();
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    int val = blocks[i][j].getVal();
                    if(val == -1)
                        bw.write("[____] ");
                    else {
                        bw.write('[');
                        bw.write(String.format("%4d", 2 << val));
                        bw.write("] ");
                    }
                }
                bw.newLine();
            }
            bw.flush();
        }catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }
}

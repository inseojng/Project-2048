public class Block {

    private int y, x;
    private int dy, dx;
    private int val;

    public Block() {
        this.y = 0; this.x = 0;
        this.dy = 0; this.dx = 0;
        this.val = -1;
    }

    public Block(int _y, int _x) {
        this.y = _y; this.x = _x;
        this.dy = 0; this.dx = 0;
        this.val = -1;
    }

    public Block(int _y, int _x, int _val) {
        this.y = _y; this.x = _x; this.val = _val;
        this.dy = 0; this.dx = 0;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getDx() {
        return this.dx;
    }
    public int getDy() {
        return this.dy;
    }
    public int getVal() {
        return this.val;
    }
    public int addVal() {
        return ++this.val;
    }

    public void setX(int _x) {
        this.x = _x;
    }
    public void setY(int _y) {
        this.y = _y;
    }
    public void setDx(int _dx) {
        this.dx = _dx;
    }
    public void setDy(int _dy) {
        this.dy = _dy;
    }
    public void setVal(int _val) {
        this.val = _val;
    }


}
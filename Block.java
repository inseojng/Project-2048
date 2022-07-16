public class Block {

    private int y, x;
    private int val;

    public Block() {
        this.y = 0; this.x = 0;
        this.val = -1;
    }

    public Block(int _y, int _x) {
        this.y = _y; this.x = _x;
        this.val = -1;
    }

    public Block(int _y, int _x, int _val) {
        this.y = _y; this.x = _x; this.val = _val;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
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
    public void setVal(int _val) {
        this.val = _val;
    }

    public void draw() {

    }

}
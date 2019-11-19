package sk.tuke.kpi.oop.game;

public enum Direction {
    NORTH(0, 1), EAST(1, 0), SOUTH(-1, -1), WEST(-1, 0), NONE(0, 0);

    private final int dx;
    private final int dy;
    Direction(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return this.dx;
    }
     public int getDy() {
        return  this.dy;
     }

    float getAngle(){
        return (float) Math.toDegrees(Math.atan2(dx, dy))-90;
    }

}




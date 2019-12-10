package sk.tuke.kpi.oop.game;

public enum Direction {
    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0), NONE(0, 0), NORTHEAST(1, 1), NORTHWEST(-1, 1), SOUTHEAST(1, -1), SOUTHWEST(-1, -1);

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

    public float getAngle(){
        float angle = (float) Math.toDegrees(Math.atan2(dx, dy));
        return (this == EAST || this == WEST) ? angle * (-1) : angle;
    }

    public Direction combine(Direction other) {
        return Direction.NONE;
    }

    public static Direction getRandomDirection() {
        int rand = (int)(Math.random() * 4);
        return Direction.values()[rand];
    }

    public static Direction fromAngle(float angle) {
        for (Direction direction : Direction.values()) {
            if(direction.getAngle() == angle){

                return direction;
            }

        }
        return Direction.NONE;
    }

}




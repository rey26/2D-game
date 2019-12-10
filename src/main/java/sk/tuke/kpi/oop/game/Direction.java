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
        return (this == EAST || this == WEST || this == NORTHEAST || this == NORTHWEST || this == SOUTHEAST || this == SOUTHWEST) ? angle * (-1) : angle;
    }

    public Direction combine(Direction other) {
        if ((other == NORTH && this == EAST) || (other == EAST && this == NORTH))
            return Direction.NORTHEAST;
        else if (other == NORTH && this == WEST )
            return Direction.NORTHWEST;
        return Direction.NONE;
    }

    public static Direction getRandomDirection() {
        int rand = (int)(Math.random() * 4);
        return Direction.values()[rand];
    }

    public static Direction fromAngle(float angle) {
//        angle = 270;
        float ang = angle;
        if (ang > 180)
            ang -= 360;
        for (Direction direction : Direction.values()) {
            if(direction.getAngle() == ang){
                return direction;
            }

        }
        return Direction.NONE;
    }

}




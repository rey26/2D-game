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
//        if ((other == NORTH && this == EAST) || (other == EAST && this == NORTH))
//            return Direction.NORTHEAST;
//        else if ((other == NORTH && this == WEST) || (other == WEST && this == NORTH))
//            return Direction.NORTHWEST;
//        else if ((other == SOUTH && this == EAST) || (other == EAST && this == SOUTH))
//            return Direction.SOUTHEAST;
//        else if ((other == SOUTH && this == WEST) || (other == WEST && this == SOUTH))
//            return Direction.SOUTHWEST;
//        else if ((other == SOUTHWEST && this == NORTH) || (other == NORTH && this == SOUTHWEST) || (other == NORTHWEST && this == SOUTH) || (other == SOUTH && this == NORTHWEST))
//            return Direction.WEST;
//        else if ((other == SOUTHEAST && this == NORTH) || (other == NORTH && this == SOUTHEAST) || (other == NORTHEAST && this == SOUTH) || (other == SOUTH && this == NORTHEAST))
//            return Direction.EAST;
//        else if ((other == NORTHWEST && this == EAST) || (other == EAST && this == NORTHWEST) || (other == NORTHEAST && this == WEST) || (other == WEST && this == NORTHEAST))
//            return Direction.NORTH;
//        else if ((other == SOUTHWEST && this == EAST) || (other == EAST && this == SOUTHWEST) || (other == SOUTHEAST && this == WEST) || (other == WEST && this == SOUTHEAST))
//            return Direction.SOUTH;
//
//        return Direction.NONE;
        if (other.getDx() == this.getDx() && other.getDy() == this.getDy())
            return  this;
        int newX = this.dx + other.dx,
            newY = this.dy + other.dy;
        if (newX > 1 || newX < -1 || newY > 1 || newY < -1)
            return Direction.NONE;
        for (Direction direction : Direction.values()) {
            if(direction.getDx() == newX && direction.getDy() == newY)
                return direction;
        }
        return Direction.NONE;
    }

    public static Direction getRandomDirection() {
        int rand = (int)(Math.random() * 4);
        return Direction.values()[rand];
    }

    public static Direction fromAngle(float angle) {
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




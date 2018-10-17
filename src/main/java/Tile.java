
public class Tile {
    private static enum colour{
        RED,GREEN,BLUE,ORANGE;
    }
    private static enum value{
        ONE(1),TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7),EIGHT(8),NINE(9),TEN(10),ELEVEN(11),TWELVE(12),THIRTEEN(13);
        private final int val;

        value(int val){
            this.val = val;
        }

        public int getVal(){
            return this.val;
        }

        public int getCol(){
            return this.val;
        }
    }
    private colour col;
    private value val;

    public Tile(colour c, value v){
        col = c;
        val = v;
    }

    public Tile(){
        col = null;
        val = null;
    }
}

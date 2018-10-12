public class Tile {
    private static enum colours{
        RED,GREEN,BLUE,ORANGE;
    }
    private static enum values{
        ONE(1),TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7),EIGHT(8),NINE(9),TEN(10),ELEVEN(11),TWELVE(12),THIRTEEN(13);
        private final int val;

        values(int val){
            this.val = val;
        }

        public int getVal(){
            return this.val;
        }
    }
    private String col;
    private String val;

}

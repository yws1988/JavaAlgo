package codinggame;

public class ComputeFinalPosition {
    public static int[] computeFinalPosition(int width, int height, int[] position, int[] portalA, int[] portalB, String moves) {

        for (int i = 0; i < moves.length(); i++) {

            Character c = moves.charAt(i);

            if(position[0] == portalA[0] && position[1]==portalA[1]){
                position[0] = portalB[0];
                position[1] = portalB[1];
            }else if(position[0] == portalB[0] && position[1]==portalB[1]){
                position[0] = portalA[0];
                position[1] = portalA[1];
            }

            if(c.equals('U') && position[1]>=1){
                position[1]--;
            }else if(c.equals('D') && position[1]<height-1){
                position[1]++;
            }else if(c.equals('R') && position[0]<width-1){
                position[0]++;
            }else if(c.equals('L') && position[0]>0){
                position[0]--;
            }
        }

        return position;
    }

}

package online.divyesh.baseproject;

class Pattern {
    public static void main(String[] args) {

        int raw = 5;

        for (int i = raw; i >= 1; i--) {

            for (int j = 1; j <= i; j++) {
                System.out.print(i+" ");
            }

            System.out.println();

            for (int k = 1; k <= raw-i+1; k++) {
                System.out.print(" ");
            }
            
            
        }
    }

}
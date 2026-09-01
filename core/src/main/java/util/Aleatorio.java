package util;

import java.util.Random;

public class Aleatorio {

    private Aleatorio(){}

    public static int generarEntero(int min,int max){
        return (int)(Math.random()*(max-min+1)+min);
    }

    public static int generarEntero(int max){
        return generarEntero(0,max-1);
    }
}

package com.partyA.bean;

public class MoveConverter {
    public MoveConverter(){
    }
    public String toBackend(int col, int row){
        String backendCall ="";
        col = col/50;
        col += 96;
        char first = (char) col;
        backendCall += first;
        char second;
        switch(row){
            case 50:
                second = 'k';
                break;
            case 100:
                second = 'j';
                break;
            case 150:
                second = 'i';
                break;
            case 200:
                second = 'h';
                break;
            case 250:
                second = 'g';
                break;
            case 300:
                second = 'f';
                break;
            case 350:
                second = 'e';
                break;
            case 400:
                second = 'd';
                break;
            case 450:
                second = 'c';
                break;
            case 500:
                second = 'b';
                break;
            case 550:
                second = 'a';
                break;
            default:
                second ='z';
                break;
        }
        backendCall += second;
        return backendCall;
    }

}

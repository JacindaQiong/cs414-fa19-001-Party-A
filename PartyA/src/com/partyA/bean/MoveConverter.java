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

    public String toFrontEndCol(char col){
        int temp;
        switch(col){
            case 'a':
                temp = 50;
                break;
            case 'b':
                temp = 100;
                break;
            case 'c':
                temp = 150;
                break;
            case 'd':
                temp = 200;
                break;
            case 'e':
                temp = 250;
                break;
            case 'f':
                temp = 300;
                break;
            case 'g':
                temp = 350;
                break;
            case 'h':
                temp = 400;
                break;
            case 'i':
                temp = 450;
                break;
            case 'j':
                temp = 500;
                break;
            case 'k':
                temp = 550;
                break;
            default:
                temp = -1;
                break;
        }
        String result = "" +temp;
        return result;
    }
    public String toFrontEndRow(char row){
        int temp;
        switch(row){
            case 'a':
                temp = 550;
                break;
            case 'b':
                temp = 500;
                break;
            case 'c':
                temp = 450;
                break;
            case 'd':
                temp = 400;
                break;
            case 'e':
                temp = 350;
                break;
            case 'f':
                temp = 300;
                break;
            case 'g':
                temp = 250;
                break;
            case 'h':
                temp = 200;
                break;
            case 'i':
                temp = 150;
                break;
            case 'j':
                temp = 100;
                break;
            case 'k':
                temp = 50;
                break;
            default:
                temp = -1;
                break;
        }
        String result = "" +temp;
        return result;

    }

}

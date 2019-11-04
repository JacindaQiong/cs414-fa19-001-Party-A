package partya;
/**
 * User: Nana Yin
 * Date: 10/11/19
 */
public class IllegalMoveException extends Exception {
    public IllegalMoveException(){
        super();
    }
    public IllegalMoveException(String message){
        super(message);
    }
}

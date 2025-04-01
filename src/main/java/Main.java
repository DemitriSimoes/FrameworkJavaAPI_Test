interface Conta{
    public default int soma(int a, int b){
        return a;
    }
    //public abstract void multip();
    //public abstract void sub();
    //public abstract void divis();
}

class Calc implements Conta{
    @Override
    public int soma(int a, int b){
        return a + b;
    }
}

public class Main {
    public static void main (String[] args){
        System.out.println("Hello World!!!");
        Calc conta = new Calc();
        int result = conta.soma(5,3);
        System.out.println(result);
    }
}

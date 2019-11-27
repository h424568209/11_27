    class X{
        Y y=new Y(); //3
        public X(){ //2
            System.out.print("X");
        }
}
    class Y{
    public Y(){
        System.out.print("Y");
    }
}
    public class Z extends X {
        Y y = new Y(); //1
        public Z() {//4
            System.out.print("Z");
        }
        public static void main(String[] args) {
            new Z();
        }
}
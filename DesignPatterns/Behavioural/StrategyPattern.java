interface Strategy{
    void pay(double amount);
}

class UPIpayment implements Strategy{
    @Override
    public void pay(double amount){
        System.out.println(amount+" :Payment done via UPI");
    }
}
class Cashpayment implements Strategy{
    @Override
    public void pay(double amount){
        System.out.println(amount+" :Payment done via Cash");
    }
}

class Payment{
    Strategy paymentMethod;
    Payment(){
        this.paymentMethod = new UPIpayment();
    }
    public void changePaymentMethod(Strategy paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    public void makePayment(double amount){
        paymentMethod.pay(amount);
    }
}
public class StrategyPattern{
    public static void main(String[] args) {
        Payment payment = new Payment();
        payment.makePayment(22.5);
        payment.changePaymentMethod(new Cashpayment());
        payment.makePayment(2902.5);
    }
}
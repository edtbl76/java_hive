import javax.naming.InsufficientResourcesException;
import java.rmi.RemoteException;

@SuppressWarnings("ALL") // Suppressing just to demonstrate 'throws' multiple exceptions.
public class multipleThrows {

    public void withdraw(double amount) throws RemoteException, InsufficientResourcesException {
        System.out.println("I don't actually do anything.");
        System.out.println(amount);
    }

}

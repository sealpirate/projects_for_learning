import java.util.Scanner;

public class WorkingGate {
    private static SQL sql;
    private static Gate gate;
    private static CheckWorkGate checkWorkGate;
    public static void main(String[] args) {
        while (checkWorkGate.isRunning()) {
            int id = gate.getIDEmployee();
            Employee employee = sql.getEmployee(id);
            if (employee == null) {
                gate.switchLight(false);
            } else {
                gate.switchLight(true);
                gate.workGate(employee);
            }
        }
    }
}

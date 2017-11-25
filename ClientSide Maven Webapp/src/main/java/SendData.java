import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

// ������˷�����Ϣ
public class SendData extends Thread {
	private DataOutputStream dOutput;
Scanner scanner = new Scanner(System.in);
	public SendData(DataOutputStream _dOutput) {
		dOutput = _dOutput;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("�����������ţ�");
				String msg = scanner.next();
				if (msg != null) {
					dOutput.writeUTF(msg);
				}
			} catch (IOException e) {
				break;
			}
		}
	}
}

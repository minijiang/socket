import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

// 给服务端发送信息
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
				System.out.println("给服务器发信：");
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

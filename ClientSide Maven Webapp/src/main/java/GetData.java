import java.io.DataInputStream;
import java.io.IOException;

// 收取服务端发来的信息
public class GetData extends Thread {
	private DataInputStream dInput;

	public GetData(DataInputStream _dInput) {
		dInput = _dInput;
	}

	@Override
	public void run() {
		while (true) {
			try {
				String msg = dInput.readUTF();
				if (msg != null) {
					System.out.println("服务器来信：" + msg);
				}
			} catch (IOException e) {
				try {
					dInput.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			}
		}
	}
}

import javax.jws.soap.SOAPMessageHandlers;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    //加一个计数器
    private static int count =0;

    public static void main(String[] args) {
        ServerSocket ss=null;
        Socket socket=null;
        try {
            ss=new ServerSocket(9999);
            System.out.println("waterTomcat 服务器已经初始化");
            while (true){
                socket=ss.accept();
                count++;
                System.out.println("第"+count+"请求");
                //==========开始处理接收信息===============

                InputStream is=socket.getInputStream();
                byte[] buff=new byte[1024];
                //每次读取的信息
                int len=is.read(buff);
                if(len>0){
                    String msg=new String(buff,0,len);
                    System.out.println("读取数据---"+msg+"----");
                }

                //==============发送服务器返回信息================
                OutputStream os=socket.getOutputStream();
                String html="<html><head><title>hello water</title></head><body>你好我是water</body></html>";
                os.write(html.getBytes());
                os.flush();
                os.close();
                is.close();
                socket.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

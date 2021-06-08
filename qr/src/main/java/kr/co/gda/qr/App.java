package kr.co.gda.qr;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class App extends JFrame{ // App라는 클래스는 JFrame을 상속 받음.
	// Logger라는 클래스를 사용하기 위해 log라는 객체를 스태틱변수로 선언하여 log객체를 사용
	static Logger log = LoggerFactory.getLogger(App.class); 
    // void를 사용해 maim매소드를 선언하고, try catch절을 대신해 throws WriterException, IOExceptiont 사용
	public static void main( String[] args ) throws WriterException, IOException {
        // 제대로 값이 나오는지 출력
		System.out.println( "Hello World!" );
    
    // 1. QR에 어떤 컨텐츠(문자열)를 추가할 것인가? -> 다른 API를 통해서 획득
    // QRService qrService = new QRService()
    // String name = qrService.getUserName()    
    QRService qrService = new QRService(); // QRService를 불러서 이름을 바꿈.
    String userName = qrService.getUserName(); // QRService에 있는 getUserName을 새로운 userName객체로 생성
    String userBirth = qrService.getUserBirth(); // QRService에 있는 getUserBirth을 새로운 userBirth객체로 생성
    String systemInfo = qrService.getSystemInfo(); // QRService에 있는 getSystemInfo을 새로운 systemInfo객체로 생성
    String networkInfo = qrService.getNetworkInfo(); // QRService에 있는 getNetworkInfo을 새로운 networkInfo객체로 생성
    
    
    // StringBuffer는 내부적으로 String 객체가 아닌 char[] 를 사용하기 때문에 내용의 추가 및 삭제, 변환이 자유롭기 때문에 사용
    StringBuffer contents = new StringBuffer(); // contents라는 객체를 생성
    
    
    // append는 선택된 요소의 마지막에 새로운 요소나 컨텐츠를 추가.
    contents.append(userName); // userName컨텐츠를 추가
    contents.append(","); // userName, 
    log.info(userName); // 잘 출력되는지 확인
    
    contents.append(userBirth); // userBirth컨텐츠를 추가
    contents.append(","); // userBirth,
    log.info(userBirth); // 잘 출력되는지 확인

    contents.append(systemInfo); // systemInfo컨텐츠를 추가
    contents.append(","); // systemInfo,
    log.info(systemInfo); // 잘 출력되는지 확인
    
    contents.append(networkInfo); // networkInfo컨텐츠를 추가
    contents.append(","); // networkInfo,
    log.info(networkInfo); // 잘 출력되는지 확인
  
    
	// 2. QR 생성 -> 생성 시 QR에 컨텐츠 추가
    QRCodeWriter qrWriter = new QRCodeWriter(); // 새로운 qrWriter 객체 생성
    BitMatrix martix = qrWriter.encode(contents.toString(), BarcodeFormat.QR_CODE, 350, 350); // qr이미지 관련(모양, 데이터:컨텐츠)
    //MatrixToImageConfig config = new MatrixToImageConfig(0xFFFFFFFF, 0xFF000000); // qr설정 (색상 : QR의 색상, QR의 백그라운드 색상) , 0x=> 16진수
    // 색상은 0x:16진수 , FF:투명도, FF:R, FF:G, FF:B
    
    // 두개의 설정 매개변수를 이용하여 이미지 생성
    BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(martix);
    
       
    // 3. QR 저장 
    String imageFileName = "myqr.png"; // 파일 이름을 myqr.png로 한다.
    // 첫번째 매개변수: 메모리안의 이미지, 두번째 매개변수 : 확장자, 세번째 매개변수 : 파일 생성
    ImageIO.write(qrImage, "png", new File(imageFileName)); 
    
   
    // 4. QR 출력 -> web이면 jsp view -> pc앱이면 swing frame -> 안드로이드(android앱)앱 activity
    
    // swing http://cafe.naver.com/jjdev/10660
    App app = new App(); // app 생성
    app.setTitle("QR"); // 제목은 QR
    app.setLayout(new FlowLayout()); // FlowLayout은 가운데를 중심으로 배치함
    Button btn = new Button("test"); // 버튼 이름은 test
    app.add(btn);
    
    ImageIcon icon = new ImageIcon(imageFileName); // icon을 생성해 이미지를 넣어줌
    JLabel imgeLabel = new JLabel(icon); // 라벨 위치
    app.add(imgeLabel);
     
    app.setSize(450, 450); //사이즈
    app.setVisible(true); 
    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    }
}

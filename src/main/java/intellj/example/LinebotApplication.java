package intellj.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import java.util.Random;

@SpringBootApplication
@LineMessageHandler
public class LinebotApplication {
	public static void main(String[] args) {
		SpringApplication.run(LinebotApplication.class, args);
	}
	private static  String[] notMatchReply = {"ไม่ตลก","อะไรหยอออ","เพื่อนเล่นหรอ","งงอะเด้ งงอะเด้" ,"ไม่ว่าง ยุ่งอยู่" , "มาเล่นกันเถอะ มาเล่นกันเถอะ"};
	@EventMapping
	public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		System.out.println("event: " + event);
		System.out.println("message: " + event.getMessage());
		System.out.println("replyToken: " + event.getReplyToken());
		String replymessage = "";
		if (event.getMessage() != null){
			String message = event.getMessage().getText();
			if (message.length() == 0) {
				replymessage = "บอกว่าอย่าส่งสติกเกอร์ เด๋วตบคว่ำ";
			} else {
				if (message.indexOf("กุ่ย") > -1) {
					replymessage = "ฮ่องเต้เสด็จแล้ว";
				} else if (message.indexOf("เป้") > -1) {
					replymessage = "ไอ้ไข่หมุนอะนะ";
				} else {
					Random rand = new Random();
					int n = rand.nextInt(5);
					replymessage = notMatchReply[n];
				}
			}
		}else {
			replymessage = "บอกว่าอย่าส่งสติกเกอร์ เด๋วตบคว่ำ";
		}
		return new TextMessage(replymessage);
	}

	@EventMapping
	public void handleDefaultMessageEvent(Event event) {
		System.out.println("event: " + event);
	}
}

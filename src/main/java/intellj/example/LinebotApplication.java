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
	private static  String[] autoReply = {"ไม่ตลก","อะไรหยอออ","เพื่อนเล่นหรอ","งงอะเด้ งงอะเด้" ,"ไม่ว่าง ยุ่งอยู่"};
	@EventMapping
	public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		System.out.println("event: " + event);
		System.out.println("message: " + event.getMessage());
		System.out.println("replyToken: " + event.getReplyToken());
		String message = event.getMessage().getText();
		String userId = event.getSource().getUserId();

		String replymessage = "";
		if ("U1e32ce96ac0156063a0b1626a4299c40".equals(userId)){
			replymessage = "ฮ่องเต้เสด็จแล้ว";
		}else{
			Random rand = new Random();
			int n = rand.nextInt(5) + 1;
			replymessage = autoReply[n];
		}
		return new TextMessage(replymessage);
	}

	@EventMapping
	public void handleDefaultMessageEvent(Event event) {
		System.out.println("event: " + event);
	}
}

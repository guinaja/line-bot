package intellj.example;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
@LineMessageHandler
public class LinebotApplication {
    public static void main(String[] args) {
        SpringApplication.run(LinebotApplication.class, args);
    }

    private static String[] notMatchReply = {"ไม่ตลก", "อะไรหยอออ", "เพื่อนเล่นหรอ", "งงอะเด้ งงอะเด้", "ไม่ว่าง ยุ่งอยู่", "มาเล่นกันเถอะ มาเล่นกันเถอะ"};

    @EventMapping
    public TextMessage handleStickerMessageEvent(MessageEvent<StickerMessageContent> event) {
        String replymessage = "บอกว่าอย่าส่งสติกเกอร์ เด๋วตบคว่ำ";
        return new TextMessage(replymessage);
    }

    @EventMapping
    public TextMessage handleImageMessageEvent(MessageEvent<ImageMessageContent> event) {
        String replymessage = "รูปไรเนี่ย โป๊ป่าว";
        return new TextMessage(replymessage);
    }
    @EventMapping
    public TextMessage handleLocationMessageEvent(MessageEvent<LocationMessageContent> event) {
        String replymessage = "ไปไหนกันหราาา ไปด้วยจิ";
        return new TextMessage(replymessage);
    }
    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        String replymessage = "";
        String message = event.getMessage().getText();
        if (message.length() == 0) {
            replymessage = "บอกว่าอย่าส่งสติกเกอร์ เด๋วตบคว่ำ";
        } else {
            if (message.indexOf("กุ่ย") > -1) {
                replymessage = "ฮ่องเต้เสด็จแล้ว";
            } else if (message.indexOf("เป้") > -1) {
                replymessage = "ไอ้ไข่หมุนอะนะ";
            } else if (message.indexOf("แต่") > -1 && message.indexOf("กู") > -1) {
                replymessage = "เรื่องของเมิงงงงงง";
            } else {
                Random rand = new Random();
                int n = rand.nextInt(5);
                replymessage = notMatchReply[n];
            }
        }
        return new TextMessage(replymessage);
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
        System.out.println("message: " + ((MessageEvent)event).getMessage());
        System.out.println("replyToken: " + ((MessageEvent)event).getReplyToken());
    }
}

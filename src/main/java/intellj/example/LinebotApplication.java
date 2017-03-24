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
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Random;

@SpringBootApplication
@LineMessageHandler
@EnableScheduling
public class LinebotApplication {


    public static void main(String[] args) {
        SpringApplication.run(LinebotApplication.class, args);
    }


    private static String[] notMatchReply = {"ไม่ตลก", "อะไรหยอออ", "เพื่อนเล่นหรอ", "งงอะเด้ งงอะเด้", "ไม่ว่าง ยุ่งอยู่", "มาเล่นกันเถอะ มาเล่นกันเถอะ" , "กินไรยัง" ,"แล้วไงอะ" ,"ไปนอนไป๊"};
    private static String[] hiReply = {"สวัสดี", "ดีไร", "เพื่อนเล่นหรอ", "ไม่ว่างงเว้ยยย", "ไม่ว่าง ยุ่งอยู่", "ใครอะ"};
    private static String[] botReply = {"กุไม่ใช่ Siri", "Siri หรอ กากมาก", "เราก้อมีจิตใจนะ", "ไม่ใช่หุ่นยนต์นะ" };

    private static String[] loveReply = {"การแอบรักใครข้างเดียว..ก็เหมือน \"ผ้าอนามัย\"!.. เพราะทุ่มเทเท่าไหร่ก็ไม่มีวันไหลย้อนกลับ!!,,อั๊ยย่ะ!!??!...555" , "เจ้าชู้ เป็นการโปรโมท... แต่โสด กูอยู่ในกระแส!"};
    private static String[] liverpoolReply = {"เป็ดแดง","กาก","ก๊าบๆเลย"};

    private static String[] GReply = {"เตะรวมขาแม่มม", "เรื่องของกุ"};
    private static String[] NattReply = {"สวยฝุดๆเบย", "เรื่องของกุ", "เดวขึ้นโต๊ะแม่งเลย", "เสือก"};

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        printMessageInfo(event);
        String replymessage = "";
        String message = event.getMessage().getText();
        if (message.indexOf("กุ่ย") > -1) {
            replymessage = "ฮ่องเต้เสด็จแล้ว";
        } else if (message.indexOf("เป้") > -1) {
            replymessage = "ไอ้ไข่หมุนอะนะ";
        } else if (message.indexOf("แต่") > -1 && message.indexOf("กู") > -1) {
            replymessage = "เรื่องของเมิงงงงงง";
        } else if (message.indexOf("ิbot") > -1 || message.indexOf("บอท") > -1 || message.indexOf("chat") > -1 || message.indexOf("แซท") > -1) {
            replymessage = getRandomMessage(botReply);
        } else if (message.indexOf("สวัสดี") > -1 || message.indexOf("Hi") > -1 || message.indexOf("hello") > -1) {
            replymessage = getRandomMessage(hiReply);
        } else if (message.indexOf("จี") > -1 || message.indexOf("G") > -1) {
            replymessage = getRandomMessage(GReply);
        } else if (message.indexOf("แนท") > -1 || message.indexOf("nat") > -1) {
            replymessage = getRandomMessage(NattReply);
        } else if (message.indexOf("รัก") > -1 || message.indexOf("love") > -1) {
            replymessage = getRandomMessage(loveReply);
        } else if (message.indexOf("ลิเวอร์พูล") > -1 || message.indexOf("ลิเวอ") > -1 || message.indexOf("liver") > -1 || message.indexOf("หงส์") > -1 || message.indexOf("พี่พล") > -1) {
            replymessage = getRandomMessage(liverpoolReply);
        } else {
            replymessage = getRandomMessage(notMatchReply);
        }
        return new TextMessage(replymessage);
    }

    private String getRandomMessage(String[] listReply) {

        Random rand = new Random();
        int n = rand.nextInt(listReply.length - 1);
        return listReply[n];
    }

    public void printMessageInfo(Event event) {
        System.out.println("event: " + event);
        System.out.println("message: " + ((MessageEvent) event).getMessage());
        System.out.println("replyToken: " + ((MessageEvent) event).getReplyToken());
    }

    @EventMapping
    public TextMessage handleStickerMessageEvent(MessageEvent<StickerMessageContent> event) {
        printMessageInfo(event);
        String replymessage = "บอกว่าอย่าส่งสติกเกอร์ เด๋วตบคว่ำ";
        return new TextMessage(replymessage);
    }

    @EventMapping
    public TextMessage handleImageMessageEvent(MessageEvent<ImageMessageContent> event) {
        printMessageInfo(event);
        String replymessage = "รูปไรเนี่ย โป๊ป่าว";
        return new TextMessage(replymessage);
    }

    @EventMapping
    public TextMessage handleLocationMessageEvent(MessageEvent<LocationMessageContent> event) {
        printMessageInfo(event);
        String replymessage = "ไปไหนกันหราาา ไปด้วยจิ";
        return new TextMessage(replymessage);
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
        System.out.println("message: " + ((MessageEvent) event).getMessage());
        System.out.println("replyToken: " + ((MessageEvent) event).getReplyToken());
    }
}

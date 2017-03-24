package intellj.example;

/**
 * Created by prayoon-pc on 3/24/2017.
 */

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    @Autowired
    private LineMessagingClient lineMessagingClient;
    private static String lineGroupMOL ="C455aca1ae0e0e8234956f9bffde7e37c";

    @Scheduled(fixedRate = 5000)
    public void autoMessage() {
        try {
            TextMessage textMessage = new TextMessage("สวัสดี เรามาแล้วววววว เย้ เย้");
            PushMessage pushMessage = new PushMessage(lineGroupMOL, textMessage);
            lineMessagingClient.pushMessage(pushMessage).get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
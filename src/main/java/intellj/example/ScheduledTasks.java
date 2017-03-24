package intellj.example;

/**
 * Created by prayoon-pc on 3/24/2017.
 */

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.Multicast;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    @Autowired
    private LineMessagingClient lineMessagingClient;
    private static String lineGroupMOL = "C455aca1ae0e0e8234956f9bffde7e37c";

    private static boolean needToRunStartupMethod = false;

    @Scheduled(fixedDelay = 50000)
    public void autoMessage() {
        try {
            if (needToRunStartupMethod) {
                TextMessage textMessage = new TextMessage("สวัสดี");

                PushMessage pushMessage = new PushMessage(lineGroupMOL, textMessage);
                lineMessagingClient.pushMessage(pushMessage).get();
                runOnceOnlyOnStartup();
                needToRunStartupMethod = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void runOnceOnlyOnStartup() {
        System.out.print("running startup job");
    }
}
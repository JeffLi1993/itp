package sedion.jeffli.wmuitp.util.baidu;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;

public class AndroidPushByBaiDuHelper
{
	private static String apiKey	 = "xxx";
	private static String secretKey  = "xxx";
	
	/**
	 * 初始化
	 * @return
	 */
	private static BaiduChannelClient initPushClient()
	{
		// 1. 设置developer平台的ApiKey/SecretKey
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler()
        {
        	
            @Override
            public void onHandle(YunLogEvent event) 
            {
                System.out.println(event.getMessage());
            }
        });
		return channelClient;
	}
	
	
	/**
	 * 推送广播消息(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
	 * @param Content 推送内容
	 */
	public static void pushBroadcastMessage(String Content)
	{
		
        BaiduChannelClient channelClient = initPushClient();

        try 
        {

            // 4. 创建请求类对象
            PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
            request.setDeviceType(3); 
            // device_type => 1: web 2: pc 3:android
            // 4:ios 5:wp

            request.setMessage(Content);

            // 5. 调用pushMessage接口
            PushBroadcastMessageResponse response = channelClient
                    .pushBroadcastMessage(request);

            // 6. 认证推送成功
            System.out.println("push amount : " + response.getSuccessAmount());

        } 
        catch (ChannelClientException e) 
        {
            // 处理客户端错误异常
            e.printStackTrace();
        } 
        catch (ChannelServerException e) 
        {
            // 处理服务端错误异常
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

	}

	
	/**
	 * 推送单播消息(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
	 * @param ChannelId	手机端
	 * @param content	推送内容
	 * @param UserId	手机端
	 */
	public static void pushMessageSample(String content, long ChannelId,String UserId)
	{

		BaiduChannelClient channelClient = initPushClient();

        try 
        {

            //创建请求类对象
            // 手机端的ChannelId， 手机端的UserId， 先用1111111111111代替，用户需替换为自己的
            PushUnicastMessageRequest request = new PushUnicastMessageRequest();
            request.setDeviceType(3); // device_type => 1: web 2: pc 3:android
                                      // 4:ios 5:wp
            request.setChannelId(ChannelId);
            request.setUserId(UserId);

            request.setMessage(content);

            // 5. 调用pushMessage接口
            PushUnicastMessageResponse response = channelClient
                    .pushUnicastMessage(request);

            // 6. 认证推送成功
            System.out.println("push amount : " + response.getSuccessAmount());

        } 
        catch (ChannelClientException e) 
        {
            // 处理客户端错误异常
            e.printStackTrace();
        }
        catch (ChannelServerException e)
        {
            // 处理服务端错误异常
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }
	}
	
}

package io.sunny.modules.sunny.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.impl.client.BasicResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import io.sunny.common.exception.RRException;
import io.sunny.common.utils.DateTimeUtil;
import io.sunny.common.utils.http.HttpParam;
import io.sunny.common.utils.http.HttpUtil;

@Service
public class WxAcodeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(WxAcodeService.class);
	@Autowired
	private RedisTemplate<String, String> redis;
	@Value("${wechat.acode-path}")
	private String wxAcodePath;
	@Value("${wechat.appid}")
	private String wxAppId;
	@Value("${wechat.sercret}")
	private String wxSercret;
	@Value("${wechat.access-token-url}")
	private String wxAccessTokenUrl;
	@Value("${wechat.acode-url}")
	private String wxAcodeUrl;
	private static final String WX_ACCESS_TOKEN = "WX_ACCESS_TOKEN";

	public String getAccessToken() {
		return redis.opsForValue().get(WX_ACCESS_TOKEN);
	}

	public void setAccessToken(String access_token, long expires_in) {
		redis.opsForValue().set(WX_ACCESS_TOKEN, access_token, expires_in, TimeUnit.SECONDS);
	}

	public String getWxAccessToken() {
		String access_token = getAccessToken();
		if (StringUtils.isBlank(access_token))
			try {
				HttpParam httpParam = new HttpParam();
				Map<String, String> reqMap = new HashMap<>();
				reqMap.put("appid", wxAppId);
				reqMap.put("secret", wxSercret);
				reqMap.put("grant_type", "client_credential");
				httpParam.setKeyValueData(reqMap);
				httpParam.setMethod(HttpParam.Method.GET);
				httpParam.setUrl(wxAccessTokenUrl);
				httpParam.setBodyType(HttpParam.BodyType.KEYVALUE);
				String result = HttpUtil.sync(httpParam);
				JSONObject jsonObject = JSONObject.parseObject(result);
				LOGGER.debug("result:{}", result);
				int returnNum = jsonObject.get("errcode") != null ? jsonObject.getIntValue("errcode") : 0;
				if (returnNum != 0)
					return "";
				long expires_in = jsonObject.getLong("expires_in") - (60 * 5);
				access_token = jsonObject.getString("access_token");
				if (expires_in > 0 && StringUtils.isNotBlank(access_token)) {
					LOGGER.debug("access_token:{},expires_in:{}", access_token, expires_in);
					setAccessToken(access_token, expires_in);
				}
			} catch (Exception e) {
				access_token = "";
				LOGGER.error("getWxAccessToken{}:", e);
			}
		return access_token;
	}

	public String createWxAcode(String mercId, String type) {
		String access_token = getWxAccessToken();
		if (StringUtils.isBlank(access_token)) {
			return null;
		}
		String wxaresponse = "";
		HttpParam httpParam = new HttpParam();
		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("path", "pages/index/index?mercId=" + mercId + "&type=" + type);// 你二维码中跳向的地址
		reqMap.put("width", 430);// 图片大小
		httpParam.setStringData(JSONObject.toJSONString(reqMap).toString());
		httpParam.setContentType("application/json");
		httpParam.setMethod(HttpParam.Method.POST);
		httpParam.setUrl(wxAcodeUrl + access_token);
		httpParam.setBodyType(HttpParam.BodyType.STRING);
		try {
			wxaresponse = HttpUtil.sync(httpParam, new BasicResponseHandler() {
				@Override
				public String handleResponse(final HttpResponse response) throws HttpResponseException, IOException {
					boolean saveflg = false;
					LOGGER.debug("createWxAcode:" + response);
					String curdt = DateTimeUtil.currentDate();
					String uploadSysUrl = wxAcodePath + "/" + curdt + "/";
					String fileName = mercId + "-" + type + ".png";
					if (response != null) {
						final HttpEntity resEntity = response.getEntity();
						if (resEntity != null) {
							InputStream instreams = resEntity.getContent();
							File saveFile = new File(uploadSysUrl + fileName);
							// 判断这个文件（saveFile）是否存在
							if (!saveFile.getParentFile().exists()) {
								// 如果不存在就创建这个文件夹
								saveFile.getParentFile().mkdirs();
							}
							saveflg = saveToImgByInputStream(instreams, uploadSysUrl, fileName);
						}
					}
					return saveflg ? uploadSysUrl + fileName : "";
				}

			});
		} catch (Exception e) {
			throw new RRException("生成微信二维码失败！", e);
		}

		return wxaresponse;
	}

	/*
	 * @param instreams 二进制流
	 * 
	 * @param imgPath 图片的保存路径
	 * 
	 * @param imgName 图片的名称
	 * 
	 * @return 1：保存正常 0：保存失败
	 */
	public static boolean saveToImgByInputStream(InputStream instreams, String imgPath, String imgName) {
		if (instreams != null) {
			try {
				File file = new File(imgPath + imgName);// 可以是任何图片格式.jpg,.png等
				FileOutputStream fos = new FileOutputStream(file);

				byte[] b = new byte[1024];
				int nRead = 0;
				while ((nRead = instreams.read(b)) != -1) {
					fos.write(b, 0, nRead);
				}
				fos.flush();
				fos.close();
				return true;
			} catch (Exception e) {
				LOGGER.error("saveToImgByInputStream{}:", e);
				return false;
			} finally {
				try {
					instreams.close();
				} catch (IOException e) {
					LOGGER.error("saveToImgByInputStream{}:", e);
					return false;
				}
			}
		} else {
			return false;
		}

	}
}
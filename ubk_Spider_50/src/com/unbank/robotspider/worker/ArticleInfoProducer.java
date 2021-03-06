package com.unbank.robotspider.worker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.unbank.robotspider.entity.NewsInfoMiddleWare;
import com.unbank.robotspider.filter.coding.CodingBaseFilter;
import com.unbank.robotspider.filter.title.TitleBaseFilter;

public class ArticleInfoProducer {
	public static Log logger = LogFactory.getLog(ArticleInfoProducer.class);

	public NewsInfoMiddleWare checkNewsInfo(
			NewsInfoMiddleWare newsInfoMiddleWare) {

		boolean success = checkTitle(newsInfoMiddleWare.getCrawlTitle());
		if (!success) {
			logger.info("监测文章标题不合格" + newsInfoMiddleWare.getWebsiteId()
					+ "=========" + newsInfoMiddleWare.getUrl());
			return null;
		}
		success = checkContent(newsInfoMiddleWare.getText());
		if (success) {
			logger.info("监测文章内容不合格" + newsInfoMiddleWare.getWebsiteId()
					+ "=========" + newsInfoMiddleWare.getUrl());
			return null;
		}
		/*
		 * 银行高管，此功能去除
		 */
		// else {
		// new BankManangerFilter().findManager(newsInfoMiddleWare);
		// }

		return newsInfoMiddleWare;
	}

	private boolean checkContent(String text) {
		return new CodingBaseFilter().checkMessyCode(text);
	}

	private boolean checkTitle(String crawlTitle) {
		return new TitleBaseFilter().checkTitle(crawlTitle);
	}

}

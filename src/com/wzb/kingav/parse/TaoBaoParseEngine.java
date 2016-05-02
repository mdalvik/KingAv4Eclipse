package com.wzb.kingav.parse;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wzb.kingav.global.GlobalVariable;
import com.wzn.kingav.bean.MenuBean;
import com.wzn.kingav.bean.VideoBean;
import com.wzn.kingav.bean.VideoPlayBean;

public class TaoBaoParseEngine implements IParseEngine {

	@Override
	public List<MenuBean> ParseMenu(String html) {
		Document document = Jsoup.parse(html);
		// ѡ�в˵�ul
		Elements ul = document.select(".nav.nav-stacked.navigation").select("li");
		List<MenuBean> list = new ArrayList<>();
		for (int i = 0; i < ul.size(); i++) {
			Element li = ul.get(i);
			Elements elementA = li.select("a");
			MenuBean menuBean = new MenuBean();
			menuBean.setName(elementA.first().ownText());
			menuBean.setUrl(elementA.attr("href"));
			if (elementA.first().childNodeSize() > 1) {
				menuBean.setCount(elementA.first().child(0).text());
			}
			list.add(menuBean);
		}
		return list;

	}

	@Override
	public List<VideoBean> ParseVedio(String html) {

		// TODO Auto-generated method stub
		Document document = Jsoup.parse(html);
		Elements ul = document.select(".videos").select(".video");

		List<VideoBean> list = new ArrayList<>();
		for (int i = 0; i < ul.size(); i++) {
			Element element = ul.get(i);
			VideoBean videoBean = new VideoBean();
			// ����
			videoBean.setLink(element.select("a").attr("href"));

			// ����

			videoBean.setTitle(element.select("a").attr("title"));
			// src

			videoBean.setImg(element.select("img").attr("src"));
			// ʱ��

			videoBean.setPlayTime(element.select(".video-overlay.badge.transparent").text());
			// ��������

			videoBean.setPullTime(element.select(".pull-left").text());
			// ���Ŵ���
			videoBean.setPlayCount(element.select(".pull-right.text-right").text());
			list.add(videoBean);

		}
		return list;
	}

	@Override
	public VideoPlayBean ParseVedioPlayAddress(String html) {
		Document document = Jsoup.parse(html);
		Elements select = document.select("video#player");
		Element first = select.first();
		VideoPlayBean videoPlayBean = new VideoPlayBean();
		List<VideoPlayBean.Source> list = new ArrayList<>();
		videoPlayBean.setPoster(first.attr("poster"));
		Elements sources = first.select("source");
		for (int i = 0; i < sources.size(); i++) {
			VideoPlayBean.Source source = new VideoPlayBean.Source();
			source.setLabel(sources.get(i).attr("label"));
			source.setRes(sources.get(i).attr("res"));
			source.setType(sources.get(i).attr("type"));
			source.setSrc(sources.get(i).attr("src"));
			list.add(source);
		}
		videoPlayBean.setSrcList(list);
		return videoPlayBean;
	}

}

package com.marlap.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.marlap.bean.SecurityBean;
import com.marlap.util.Constant;

@Service
public class SecurityServiceImpl implements SecurityService {

	private volatile static Map<String, SecurityBean> referenceMap = new HashMap<String, SecurityBean>();

	@Override
	public String requestQuestion() {

		List<Integer> numbers = new ArrayList<Integer>();
		int loop = (getRandomNumber() % 3) + 1;
		loop = loop > 1 ? loop : (getRandomNumber() % 3) + 1;
		for (int i = 0; i < loop; i++) {
			numbers.add((getRandomNumber() % 20) + 1);
		}
		String numString = numbers.stream().map(e -> String.valueOf(e)).collect(Collectors.joining(","));
		String qns = String.format(Constant.QUS, numString);

		if (!referenceMap.containsKey(numString)) {
			referenceMap.put(numString, new SecurityBean(qns, numbers.stream().reduce(0, Integer::sum)));
		}
		return qns;
	}

	private static int getRandomNumber() {
		return (int) (Math.random() * 10000);
	}

	@Override
	public String validateAns(String ansString) {
		String response = Constant.INVALID_QUS;
		if (!StringUtils.isEmpty(ansString)) {
			Optional<Entry<String, SecurityBean>> s = referenceMap.entrySet().stream()
					.filter(k -> ansString.contains(k.getKey())).findFirst();
			String ans = "";
			if (s.isPresent()) {
				ans = ansString.substring(ansString.indexOf("answer")).replaceAll("[^\\d]", "");
				ans = StringUtils.isEmpty(ans) ? "0" : ans;
				response = Integer.parseInt(ans) == s.get().getValue().getAns() ? Constant.CORRECT_ANS
						: Constant.WRONG_ANS;
			}
		}
		return response;
	}

	@Async
	public void cleanUp() {
		long now = System.currentTimeMillis();
		List<String> keys = referenceMap.entrySet().stream()
				.filter(entry -> (now - entry.getValue().getCreatedOn().getTime()) / (24 * 60 * 60 * 1000) > 2)
				.map(Entry::getKey).collect(Collectors.toList());
		keys.forEach(key -> referenceMap.remove(key));
	}

}

package com.hdh.redpacket.game.service;

import com.hdh.redpacket.user.model.UserProperty;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CaculateService {

    /**
     * 获取每个玩家合在一起后的概率
     * @param userProperties
     * @return
     */
    public List<UserProperty> getUserWinProbability(List<UserProperty> userProperties){
        if(userProperties == null || userProperties.size() < 1){
            return null;
        }

        BigDecimal winProb = BigDecimal.ZERO;
        List<UserProperty> winProbList = new ArrayList<>();
        // 每个玩家胜出的概率的计算
        for(int i=0;i<userProperties.size();i++){
            BigDecimal oneTime = BigDecimal.ONE;
            for(int j=0;j<userProperties.size();j++){
                if(i == j){
                    oneTime = oneTime.multiply(userProperties.get(i).getWinProbability()==null?BigDecimal.ZERO:userProperties.get(i).getWinProbability());
                }else{
                    oneTime = oneTime.multiply(new BigDecimal(100).subtract(userProperties.get(j).getWinProbability()==null?BigDecimal.ZERO:userProperties.get(j).getWinProbability()));
                }
            }
            winProb = winProb.add(oneTime);
            UserProperty userProperty = userProperties.get(i);
            UserProperty userProperty1 = new UserProperty();
            userProperty1.setUserId(userProperty.getUserId());
            userProperty1.setWinProbability(oneTime);
            winProbList.add(userProperty1);
        }

        return winProbList;
    }

    /**
     * 抽出胜出者
     * @param userProperties
     * @return
     */
    public String getWinner(List<UserProperty> userProperties){
        if(userProperties == null || userProperties.size() < 1){
            return null;
        }

        BigDecimal winProb = BigDecimal.ZERO;
        Random random = new Random();
        int randNum = random.nextInt((int)Math.pow(100,userProperties.size()));
        // 获取玩家中最大的概率值
        BigDecimal max = userProperties.stream().max(Comparator.comparing(userProperty -> userProperty.getWinProbability())).get().getWinProbability();
        if(randNum > max.intValue()){
            // 若随机数没有落在中奖区间，直接返回null，表示没人中奖
            return null;
        }else{
            // 对概率进行排序(从大到小)
            BigDecimal[] winProbs = userProperties.stream().sorted((t1,t2) -> t1.getWinProbability().compareTo(t2.getWinProbability())>0?-1:1).toArray(BigDecimal[]::new);
            for(int i=0;i<winProbs.length;i++){
                if(new BigDecimal(randNum).compareTo(winProbs[i]) < 0 &&
                        new BigDecimal(randNum).compareTo(winProbs[i+1]) > 0){
                    winProb = winProbs[i];
                    break;
                }else{
                    winProb = winProbs[i+1];
                }
            }
        }

        // 根据胜率筛选出玩家的信息
        final BigDecimal winnerProb = winProb;
        List<UserProperty> winnerList = userProperties.stream().filter(userProperty -> winnerProb.compareTo(userProperty.getWinProbability())==0).collect(Collectors.toList());
        if(winnerList == null){
            return null;
        }
        // 若存在相同概率的玩家，使用随机数抽取其中一位
        if(winnerList.size() > 1){
            int index = random.nextInt(winnerList.size());
            return winnerList.get(index).getUserId();
        }
        return winnerList.get(0).getUserId();
    }
}

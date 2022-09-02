package com.golden.raspberry.awards.service;

import com.golden.raspberry.awards.dao.AwardDao;
import com.golden.raspberry.awards.dto.AwardMaxDto;
import com.golden.raspberry.awards.dto.AwardMinDto;
import com.golden.raspberry.awards.dto.AwardMinMaxDto;
import com.golden.raspberry.awards.model.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AwardService {

    private final AwardDao awardDao;

    List<AwardComparable> comparablesList = new ArrayList<>();
    List<AwardMinDto> awardMinDtoList = new ArrayList<>();
    List<AwardMaxDto> awardMaxDtoList = new ArrayList<>();
    AwardMinDto awardMinDto =null;
    AwardMaxDto awardMaxDto = null;
    int j;
    int i;

    public AwardService(AwardDao awardDao) {
        this.awardDao = awardDao;
    }

    public List<Award> findAllWinners() throws SQLException {
        return awardDao.findAllWinners();
    }

    public AwardMinMaxDto findMinMaxInvervalWinner(List<Award> awards) {
        for (int i=0; i<awards.size(); i++) {
            String[] previousWinProducer = getAllTheProducersEachRow(i, awards);
            for (int j=0; j<awards.size(); j++){
                String previousWinYear = awards.get(i).getYear();
                String  followingWinYear = awards.get(j).getYear();
                if(previousWinYear !=  followingWinYear ) {
                    String[]  followingWinProducer = getAllTheProducersEachRow(j, awards);
                    findMinMaxAward(previousWinYear, previousWinProducer,  followingWinYear,  followingWinProducer);
                }
            }
        }
        Collections.sort(comparablesList);
        for (i = 0; i < comparablesList.size(); i++) {
            AwardComparable a = comparablesList.get(i);
            includePreviousWinProducer(i);
            if(awardMaxDto != null){
                awardMaxDtoList.add(awardMaxDto);
            }
            for (j = i+1; j < comparablesList.size(); j++) {
                AwardComparable b = comparablesList.get(j);
                if (a.getProducer().equals(b.getProducer())) {
                    includefollowingWinProducerTemp(j);
                    comparablesList.remove(j);
                    j--;
                    }
            }
        }
        awardMaxDtoList.add(awardMaxDto);

        AwardMinMaxDto awardMinMaxesListDto = new AwardMinMaxDto();
        awardMinMaxesListDto.setAwardMinList(awardMinDtoList);
        awardMinMaxesListDto.setAwardMaxList(awardMaxDtoList);
        return awardMinMaxesListDto;
    }

    private void includePreviousWinProducer(int i) {
        awardMinDto = new AwardMinDto();
        awardMinDto.setProducer(comparablesList.get(i).getProducer());
        awardMinDto.setInterval(comparablesList.get(i).getInterval());
        awardMinDto.setPreviousWin(comparablesList.get(i).getPreviousWin());
        awardMinDto.setFollowingWin(comparablesList.get(i).getFollowingWin());
        awardMinDtoList.add(awardMinDto);
    }

    private void includefollowingWinProducerTemp(int j) {
        awardMaxDto = new AwardMaxDto();
        awardMaxDto.setProducer(comparablesList.get(j).getProducer());
        awardMaxDto.setInterval(comparablesList.get(j).getInterval());
        awardMaxDto.setPreviousWin(comparablesList.get(j).getPreviousWin());
        awardMaxDto.setFollowingWin(comparablesList.get(j).getFollowingWin());

    }

    private void findMinMaxAward(String previousWinYear, String[] previousWinProducer,  String  followingWinYear,  String[]  followingWinProducer) {

        for(int p=0; p<previousWinProducer.length; p++) {
            for (int x = 0; x <  followingWinProducer.length; x++) {
                String  preWinProducer = previousWinProducer[p];
                String  folWinProducer =  followingWinProducer[x];
                if( preWinProducer.trim().equals( folWinProducer.trim())) {
                    Integer interval = Integer.parseInt(previousWinYear) - Integer.parseInt(followingWinYear);
                    if(Math.signum(interval)!=-1){
                        AwardComparable awardCompare = new AwardComparable( preWinProducer.trim(), interval, followingWinYear, previousWinYear);
                        comparablesList.add(awardCompare);
                    }
                }
            }
        }
    }

    private String[] getAllTheProducersEachRow(int index, List<Award> awards) {
        String producers = awards.get(index).getProducers();
        String[] splitProducers = producers.split("\\,| and ");
        return splitProducers;
    }
}

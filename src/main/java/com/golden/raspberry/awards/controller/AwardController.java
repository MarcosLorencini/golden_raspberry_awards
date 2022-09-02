package com.golden.raspberry.awards.controller;


import com.golden.raspberry.awards.exceptions.AwardsException;
import com.golden.raspberry.awards.model.Award;
import com.golden.raspberry.awards.dto.AwardMinMaxDto;
import com.golden.raspberry.awards.service.AwardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@Api(value = "Award")
@RequestMapping("award")
public class AwardController {

    private final AwardService service;

    @Autowired
    public AwardController(AwardService service) {
        this.service = service;
    }

    @ApiOperation("Return the producer with the longest and shorter gap between two consecutive awards.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return an object with producer information with the shortest and longest prize interval."),
            @ApiResponse(code = 404, message = "Returns an error when there are no producers to calculate the range."),
    })
    @RequestMapping(value="/getMinMaxInvervalWinner", method= RequestMethod.GET)
    public ResponseEntity<AwardMinMaxDto> findAllWinners() throws SQLException, AwardsException {
            List<Award> awards = service.findAllWinners();
            AwardMinMaxDto awardMinMaxDtoList = service.findMinMaxInvervalWinner(awards);
            return ResponseEntity.ok().body(awardMinMaxDtoList);
    }

}


package com.golden.raspberry.awards.config;

import com.golden.raspberry.awards.model.Award;
import org.springframework.batch.item.ItemProcessor;

public class DBLogProcessor implements ItemProcessor<Award, Award>
{
    public Award process(Award award) throws Exception
    {
        System.out.println("Inserting awards : " + award);
        return award;
    }
}

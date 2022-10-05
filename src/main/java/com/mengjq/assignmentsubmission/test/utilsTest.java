package com.mengjq.assignmentsubmission.test;

import com.mengjq.assignmentsubmission.conf.LanguageSet;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import org.junit.Test;

public class utilsTest {
    EchoCLI echoCLI = new EchoCLI();
    LanguageSet languageSet = new LanguageSet("cn");
    @Test
    public void test(){
        echoCLI.timeWait(languageSet, 20);
    }
}

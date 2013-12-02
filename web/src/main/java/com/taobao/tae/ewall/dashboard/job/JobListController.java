package com.taobao.tae.ewall.dashboard.job;


import com.taobao.tae.ewall.job.JobDO;
import com.taobao.tae.ewall.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午7:57
 */
@Controller
@RequestMapping("/dashboard/")
public class JobListController{

    @Resource
    JobService jobService;

    @RequestMapping("joblist")
    public String getAllJobs(ModelMap model) {
        List<JobDO> jobList = jobService.getAllJobs();
        model.addAttribute("joblist", jobList);
        return "dashboard/job/joblist";
    }

}

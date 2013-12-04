package com.taobao.tae.ewall.dashboard.job;


import com.taobao.tae.ewall.job.JobDO;
import com.taobao.tae.ewall.service.JobService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午7:57
 */
@Controller
@RequestMapping("/dashboard/job/")
public class JobController {

    @Resource
    JobService jobService;

    @RequestMapping("joblist")
    public String getAllJobs(ModelMap model) {
        List<JobDO> jobList = jobService.getAllJobs();
        model.addAttribute("joblist", jobList);
        return "dashboard/job/joblist";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addJob(String name, String jenkinsURL, Integer jobType, Integer status, String description, ModelMap model) {
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(jenkinsURL)) {
            JobDO jobDO = new JobDO();
            jobDO.setName(name);
            jobDO.setJenkinsURL(jenkinsURL);
            jobDO.setJobType(jobType);
            jobDO.setStatus(status);
            jobDO.setDescription(description);
            Integer result = jobService.addJob(jobDO);
            model.addAttribute("addJobResult", result);
        }
        return "redirect:joblist";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteJob(Integer id, ModelMap model) {
        Boolean result = jobService.deleteById(id);
        model.addAttribute("deleteJobResult", result);
        if (result) {
            return "redirect:joblist";
        } else {
            return "redirect:joblist";
        }
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editJob(Integer id, String name, String jenkinsURL, Integer jobType, Integer status, String description, ModelMap model) {
        if (id != null && StringUtils.isNotBlank(name) && StringUtils.isNotBlank(jenkinsURL)) {
            JobDO jobDO = new JobDO();
            jobDO.setId(id);
            jobDO.setName(name);
            jobDO.setJenkinsURL(jenkinsURL);
            jobDO.setJobType(jobType);
            jobDO.setStatus(status);
            jobDO.setDescription(description);
            Boolean result = jobService.updateJob(jobDO);
            model.addAttribute("editJobResult", result);
        }
        return "redirect:joblist";
    }


}

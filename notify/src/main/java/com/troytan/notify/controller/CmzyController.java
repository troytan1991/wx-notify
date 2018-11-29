package com.troytan.notify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.troytan.notify.aspect.NoAuth;
import com.troytan.notify.domain.Customer;
import com.troytan.notify.domain.News;
import com.troytan.notify.domain.Worker;
import com.troytan.notify.dto.CustomerDto;
import com.troytan.notify.dto.DesignerDto;
import com.troytan.notify.dto.FitmentDto;
import com.troytan.notify.manager.CmzyManager;
import com.troytan.notify.service.CmzyService;

@NoAuth
@RestController
@RequestMapping(path = "/cmzy", produces = "application/json;charset=UTF-8")
public class CmzyController {

    @Autowired
    private CmzyService cmzyService;
    @Autowired
    private CmzyManager cmzyManager;

    @NoAuth
    @GetMapping("/news")
    public List<News> searchNews(@RequestParam("searchStr") String searchStr,
                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<?> page = new Page<>(pageNum, pageSize);
        return cmzyService.searchNews(searchStr, page);
    }

    @NoAuth
    @GetMapping("/news/{mediaId}")
    public News getNews(@PathVariable("mediaId") String mediaId) {
        return cmzyService.getNews(mediaId);
    }

    @NoAuth
    @GetMapping("/designer")
    public List<DesignerDto> getDesigners() {
        return cmzyService.getDesigners();
    }

    @NoAuth
    @GetMapping("/worker")
    public List<Worker> getWorkers() {
        return cmzyService.getWorkers();
    }

    @NoAuth
    @GetMapping("/fitment")
    public List<FitmentDto> getFitments() {
        return cmzyService.getFitments();
    }

    @NoAuth
    @PutMapping("/customer")
    public Customer createCustomers(@RequestBody CustomerDto customerDto) {
        return cmzyService.createCustomers(customerDto);
    }

    @NoAuth
    @PutMapping("/updateNews")
    public void updateNews() {
        cmzyManager.updateNews();
    }

}

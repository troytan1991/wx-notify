package com.troytan.notify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.troytan.notify.domain.Customer;
import com.troytan.notify.domain.News;
import com.troytan.notify.domain.Worker;
import com.troytan.notify.dto.CustomerDto;
import com.troytan.notify.dto.DesignerDto;
import com.troytan.notify.dto.FitmentDto;
import com.troytan.notify.repository.CustomerMapper;
import com.troytan.notify.repository.DesignerMapper;
import com.troytan.notify.repository.FitmentMapper;
import com.troytan.notify.repository.NewsMapper;
import com.troytan.notify.repository.WorkerMapper;

@Service
public class CmzyServiceImpl implements CmzyService {

    @Autowired
    private NewsMapper     newsMapper;

    @Autowired
    private DesignerMapper designerMapper;

    @Autowired
    private WorkerMapper   workerMapper;

    @Autowired
    private FitmentMapper  fitmentMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<News> searchNews(String searchStr, Page<?> page) {
        return newsMapper.listByTitle(searchStr, page);
    }

    @Override
    public List<DesignerDto> getDesigners() {
        List<DesignerDto> list = designerMapper.listByWork();
        return list;
    }

    @Override
    public List<Worker> getWorkers() {
        return workerMapper.selectAll();
    }

    @Override
    public List<FitmentDto> getFitments() {
        List<FitmentDto> list = fitmentMapper.listByDetail();
        return list;
    }

    @Override
    public Customer createCustomers(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setName(customerDto.getName());
        customer.setPhone(customerDto.getPhone());
        customer.setArea(customerDto.getArea());
        customer.setLevel(customerDto.getLevel());
        customerMapper.insert(customer);
        return customer;
    }

    @Override
    public News getNews(String mediaId) {
        return newsMapper.selectByPrimaryKey(mediaId);
    }

}
